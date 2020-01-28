package ga.harrysullivan.langsy.utils

import android.app.Application
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.GrammarPartOfSpeech
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.constants.VocabPartOfSpeech
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.view_models.ContentViewModel
import java.util.*

class Corpora(application: Application) {
    private val mApplication: Application

    init {
        mApplication = application
    }

    private fun readFile(filename: String): String {
        val insults: String

        mApplication.assets.open(filename).apply {
            insults = this.readBytes().toString(Charsets.UTF_8)
        }.close()

        return insults
    }

    fun getLineOfPrereq(searchPrereq: String): Int {
        val filename = "corpora/language-content/grammar/prereqs.csv"
        val prereqs = readFile(filename).split("\n")
        return prereqs.indexOf("$searchPrereq,")
    }

    fun getTrainer(content: Content): Trainer {
        if (content.type == ContentType.VOCAB) {

            val vocabFilename = "corpora/language-content/${content.partOfSpeech}/words.txt"
            val vocab = readFile(vocabFilename).split('\n')

            val translationFilename = "corpora/language-content/${content.partOfSpeech}/translations/${content.language}.txt"
            val translation = readFile(translationFilename).split('\n')

            return Trainer(vocab[content.line], translation[content.line])

        } else {

            val grammarFilename = "corpora/language-content/grammar/${content.partOfSpeech}.csv"
            val grammar = readFile(grammarFilename).split("\n")

            val translationFilename = "corpora/language-content/grammar/${content.partOfSpeech}/${content.language}.txt"
            val translation = readFile(translationFilename).split('\n')

            return Trainer(grammar[content.line].split(",")[0], translation[content.line])

        }

    }

    fun getVocab(langCode: String): Content {
        val pos = Random.choose(VocabPartOfSpeech.ALL)

        val vocabFilename = "corpora/language-content/$pos/words.txt"
        val vocab = readFile(vocabFilename).split('\n')

        val lineNumber = Random.int(vocab.size - 1)

        return Content(0, 0, ContentType.VOCAB, lineNumber, 0, langCode, pos)
    }

    fun getGrammar(langCode: String, selectedContent: List<Content>): Content {
        val pos = Random.choose(GrammarPartOfSpeech.ALL)

        val grammarFilename = "corpora/language-content/grammar/$pos.csv"
        val grammar = readFile(grammarFilename).split('\n')

        val lineNumber = Random.int(grammar.size)
        val prereq = grammar[lineNumber].split(",")[1]
        val prereqLineNumber = getLineOfPrereq(prereq)
        val prereqDone = selectedContent.find { content ->
            content.partOfSpeech == GrammarPartOfSpeech.PREREQS &&
                    content.line == prereqLineNumber &&
                    content.stage >= SpacedRepetition.THRESHOLD_OF_PROBABALISTIC_MASTERY
        }

        if (prereqDone == null) {
            return Content(0, 0, ContentType.GRAMMAR, prereqLineNumber, 0, langCode, GrammarPartOfSpeech.PREREQS)
        } else {
            return Content(0, 0, ContentType.GRAMMAR, lineNumber, 0, langCode, pos)
        }
    }
}