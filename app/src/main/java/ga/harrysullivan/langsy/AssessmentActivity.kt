package ga.harrysullivan.langsy

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.CorrectAnswerAdapter
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.adapters.SpontaneousRecallAdapter
import ga.harrysullivan.langsy.constants.ReinforcementSchedule
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.controllers.Engine
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.data.Spontaneous
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.state.AssessmentDirtyState
import ga.harrysullivan.langsy.stateData.AssessmentDirtyStateData
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.utils.observeSingle
import ga.harrysullivan.langsy.view_models.*
import kotlinx.android.synthetic.main.activity_assessment.*
import net.gcardone.junidecode.Junidecode.unidecode


class AssessmentActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mCourseViewModel: CourseViewModel
    private lateinit var mCorrectAnswerAdapter: CorrectAnswerAdapter
    private lateinit var mTrainerViewModel: TrainerViewModel
    private lateinit var mCurrentCourseViewModel: CurrentCourseViewModel
    private lateinit var mDirtyState: AssessmentDirtyState
    private lateinit var mRevealPanelAdapter: RevealPanelAdapter
    private lateinit var mSpontaneousViewModel: SpontaneousViewModel
    private lateinit var mSpontaneousRecallAdapter: SpontaneousRecallAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ContentViewModel::class.java)

        mCourseViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(CourseViewModel::class.java)

        mCorrectAnswerAdapter = CorrectAnswerAdapter(this.layoutInflater, assessment_root)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        val currentCourseFactory = InjectorUtils.provideCurrentCourseViewModelFactory()
        mCurrentCourseViewModel = ViewModelProviders.of(this, currentCourseFactory)
            .get(CurrentCourseViewModel::class.java)

        val spontaneousFactory = InjectorUtils.provideSpontaneousViewModelFactory()
        mSpontaneousViewModel = ViewModelProviders.of(this, spontaneousFactory)
            .get(SpontaneousViewModel::class.java)

        mDirtyState = ViewModelProviders.of(this).get(AssessmentDirtyState::class.java)

        mRevealPanelAdapter = RevealPanelAdapter(this.layoutInflater, assessment_root)
        mSpontaneousRecallAdapter = SpontaneousRecallAdapter(this.layoutInflater, assessment_root)

        init()
    }

    private fun init() {
        mCurrentCourseViewModel.getCurrentCourse().observe(this, Observer {
            assessment_cash.text = "$${it.course.cash.toInt()}"
        })

        mDirtyState.data.value = AssessmentDirtyStateData(false)

        mDirtyState.data.observe(this, Observer {
            if (it.dirty) {
                assessment_stage.setTextColor(Color.RED)
            }
        })

        mTrainerViewModel.getTrainer().observeOnce(this, Observer { trainer ->
            assessment_content.text = trainer.content

            val stage = trainer.contentObj.stage
            setMastery(stage)

            mRevealPanelAdapter.setContent(trainer.translation, unidecode(trainer.translation))
            mCorrectAnswerAdapter.setContent(trainer.translation)


            assessment_next_button.setOnClickListener {
                processAttemptedNext(trainer, stage)
            }
        })

        var observedSpontaneous = false
        mSpontaneousViewModel.getSpontaneous().observeOnce(this, Observer {
            if (it.active && !observedSpontaneous) {
                mSpontaneousRecallAdapter.setContent(it.content)

                Handler().postDelayed(::showSpontaneous, 5000)
            }
            observedSpontaneous = true
        })

        setListeners()
    }

    private fun showSpontaneous() {
        mSpontaneousRecallAdapter.show()
        mSpontaneousViewModel.setSpontaneous(Spontaneous("", false))
    }

    private fun setListeners() {
        assessment_next_button.setOnClickListener {
            mCorrectAnswerAdapter.show()
        }

        assessment_reveal.setOnClickListener {
            mDirtyState.data.value = AssessmentDirtyStateData(true)
            mRevealPanelAdapter.show()
        }

        assessment_home_button.setOnClickListener {
            val intent =
                Intent(this@AssessmentActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun processAttemptedNext(trainer: Trainer, stage: Int) {
        val userInput = assessment_edit_text.text.toString().toLowerCase().trim()
        if (userInput == trainer.translation.toLowerCase()) {

            mContentViewModel.setLastReviewed(
                trainer.contentObj.uid,
                System.currentTimeMillis() / 1000
            )

            mDirtyState.data.observeOnce(this, Observer { dirtyData ->
                if (dirtyData.dirty) {
                    mCorrectAnswerAdapter.show()
                    setCallbackWrong(trainer)
                } else {
                    gotRightFirstTry(trainer, stage)
                }
            })

        } else {
            mRevealPanelAdapter.show()
            mDirtyState.data.value = AssessmentDirtyStateData(true)
        }
    }

    private fun gotRightFirstTry(trainer: Trainer, stage: Int) {
        mContentViewModel.addToStage(trainer.contentObj.uid, 1)
        setMastery(stage + 1)
        setCallbackRight(trainer)

        mCurrentCourseViewModel.getCurrentCourse()
            .observeOnce(this, Observer { currentCourse ->
                val newCash =
                    currentCourse.course.cash + ReinforcementSchedule.makeRightReward()
                val depositedCourse = currentCourse.course.copy(cash = newCash)
                mCourseViewModel.update(depositedCourse)
                mCurrentCourseViewModel.setCurrentCourse(CurrentCourse(depositedCourse))
            })

        mCorrectAnswerAdapter.show()
    }

    private fun finish(selectedContent: List<Content>) {
        val shouldGetNew = Engine.shouldDoNew(selectedContent)
        if (shouldGetNew) {
            mCurrentCourseViewModel.getCurrentCourse()
                .observeOnce(this, Observer { currentCourse ->
                    val (content, engineTrainer) = Engine.newContent(
                        selectedContent,
                        this.application,
                        currentCourse.course
                    )
                    mContentViewModel.insert(content)
                    mTrainerViewModel.editTrainer(engineTrainer)

                    clearSpontaneous()

                    val intent =
                        Intent(this@AssessmentActivity, VisualLearningActivity::class.java)
                    startActivity(intent)
                })
        } else {
            val engineTrainer = Engine.practice(selectedContent, this.application)

            mTrainerViewModel.editTrainer(engineTrainer)
            clearSpontaneous()
            val intent =
                Intent(this@AssessmentActivity, AssessmentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setCallbackWrong(trainer: Trainer) {
        if (trainer.contentObj.stage == 0) {
            mSpontaneousViewModel.setSpontaneous(Spontaneous(trainer.translation, true))
        }
        mCorrectAnswerAdapter.setCallback(View.OnClickListener {
            clearSpontaneous()
            val intent =
                Intent(this@AssessmentActivity, VisualLearningActivity::class.java)
            startActivity(intent)
        })
    }

    private fun setCallbackRight(trainer: Trainer) {
        mCorrectAnswerAdapter.setCallback(View.OnClickListener {
            mContentViewModel.fetchByLanguageAndStage(
                trainer.contentObj.language,
                SpacedRepetition.THRESHOLD_OF_PROBABALISTIC_MASTERY
            ).observeOnce(this, Observer { selectedContent ->
                finish(selectedContent)

            })
        })
    }

    private fun clearSpontaneous() {
        Handler().removeCallbacks(::showSpontaneous)
    }

    private fun setMastery(stage: Int) {
        assessment_stage.text =
            toPct(stage.toDouble() / SpacedRepetition.THRESHOLD_OF_MASTERY.toDouble())
    }

    private fun toPct(n: Double): String {
        return "${(n * 100).toInt()}%"
    }
}
