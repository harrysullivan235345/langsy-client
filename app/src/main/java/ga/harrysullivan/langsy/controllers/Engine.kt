package ga.harrysullivan.langsy.controllers

import android.app.Application
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.Corpora

object Engine {

    fun shouldDoNew(selectedContent: List<Content>): Boolean {
        val now = System.currentTimeMillis() / 1000
        val overdue = selectedContent.filter { content ->
            val stageIndex = stateToIndex(content.stage)
            val nextTimeToReview = content.lastReviewed + SpacedRepetition.STAGES[stageIndex]
            nextTimeToReview < now
        }

        val overdueEmpty = overdue.isEmpty()
        val availableMemory = selectedContent.count { it.stage < SpacedRepetition.THRESHOLD_OF_PROBABILISTIC_MASTERY } < SpacedRepetition.WORKING_MEMORY_CAPACITY

        return availableMemory || overdueEmpty
    }

    fun newContent(
        selectedContent: List<Content>,
        application: Application,
        course: Course
    ): Pair<Content, Trainer> {
        val totalGrammar = selectedContent.count { it.type == ContentType.GRAMMAR && it.stage < SpacedRepetition.THRESHOLD_OF_PROBABILISTIC_MASTERY }

        val corpora = Corpora(application)

        return when(totalGrammar > SpacedRepetition.MAX_GRAMMAR) {
            true -> {
                val content = corpora.getVocab(course.language, selectedContent)
                val trainer = corpora.getTrainer(content)

                Pair(content, trainer)
            }

            false -> {
                val content = corpora.getGrammar(course.language, selectedContent)
                val trainer = corpora.getTrainer(content)

                Pair(content, trainer)
            }
        }
    }

    private fun stateToIndex(stage: Int): Int {
        return if (stage == 0) {
            0
        } else {
            stage - 1
        }
    }

    fun practice(
        selectedContent: List<Content>,
        application: Application
    ): Trainer {

        val sorted =
            selectedContent.sortedBy { it.lastReviewed + SpacedRepetition.STAGES[stateToIndex(it.stage)] }
        val content = sorted.first()
        val trainer = Corpora(application).getTrainer(content)
        return trainer
    }
}