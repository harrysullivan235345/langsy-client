package ga.harrysullivan.langsy.utils

import android.app.Application
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.GrammarPartOfSpeech
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.constants.VocabPartOfSpeech
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Content

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

    fun getFile(type: String, pos: String): List<String> {
        return when (type) {
            ContentType.VOCAB -> {
                val vocabFilename = "corpora/language-content/${pos}/words.txt"
                return readFile(vocabFilename).split('\n')
            }

            ContentType.GRAMMAR -> {
                val grammarFilename = "corpora/language-content/grammar/${pos}.csv"
                return readFile(grammarFilename).split("\n")
            }

            else -> emptyList<String>()
        }
    }

    fun getLineOfPrereq(searchPrereq: String): Int {
        val filename = "corpora/language-content/grammar/prereqs.csv"
        val prereqs = readFile(filename).split("\n").map { p -> p.replace("\r", "") }
        return prereqs.indexOf("$searchPrereq,")
    }

    fun getTrainer(content: Content): Trainer {
        if (content.type == ContentType.VOCAB) {

            var found = false
            var trainer: Trainer

            do {
                val vocabFilename = "corpora/language-content/${content.partOfSpeech}/words.txt"
                val vocab = readFile(vocabFilename).split('\n')

                val translationFilename =
                    "corpora/language-content/${content.partOfSpeech}/translations/${content.language}.txt"
                val translation = readFile(translationFilename).split('\n')

                val vocabWord = vocab[content.line]
                val translationWord = translation[content.line]

                trainer = Trainer(
                    vocabWord,
                    translationWord,
                    content
                )

                if (similar(vocabWord, translationWord)) {
                    found = true
                }
            } while (!found)

            return trainer
        } else {

            val grammarFilename = "corpora/language-content/grammar/${content.partOfSpeech}.csv"
            val grammar = readFile(grammarFilename).split("\n")

            val translationFilename =
                "corpora/language-content/grammar/${content.partOfSpeech}/${content.language}.txt"
            val translation = readFile(translationFilename).split('\n')

            return Trainer(
                grammar[content.line].split(",")[0],
                translation[content.line],
                content
            )

        }

    }

    private fun similar(vocabWord: String, translationWord: String): Boolean {
        val normVocab = vocabWord.toLowerCase().trim()
        val normTranslation = translationWord.toLowerCase().trim()

        val same = normVocab != normTranslation
        val vocabInTranslation = normTranslation.indexOf(normVocab) != -1
        val translationInVocab = normVocab.indexOf(normTranslation) != -1
        return same || vocabInTranslation || translationInVocab
    }

    fun getVocab(langCode: String, selectedContent: List<Content>): Content {
        val pos = Random.choose(VocabPartOfSpeech.ALL)

        val vocabFilename = "corpora/language-content/$pos/words.txt"
        val vocab = readFile(vocabFilename).split('\n')

        var lineNumber = 0
        var found = false
        do {
            lineNumber = Random.int(vocab.size - 1)
            val doneLineNumbers =
                selectedContent.filter { it.partOfSpeech == pos && it.line == lineNumber }
                    .map { it.line }
            if (doneLineNumbers.indexOf(lineNumber) == -1) {
                found = true
            }
        } while (!found)

        return Content(0, 0, ContentType.VOCAB, lineNumber, 0, langCode, pos)
    }

    fun getGrammar(langCode: String, selectedContent: List<Content>): Content {
        val pos = Random.choose(GrammarPartOfSpeech.ALL)

        val grammarFilename = "corpora/language-content/grammar/$pos.csv"
        val grammar = readFile(grammarFilename).split('\n')

        var lineNumber = 0
        var found = false
        do {
            lineNumber = Random.int(grammar.size)
            val doneLineNumbers =
                selectedContent.filter { it.partOfSpeech == pos && it.line == lineNumber }
                    .map { it.line }
            if (doneLineNumbers.indexOf(lineNumber) == -1) {
                found = true
            }
        } while (!found)

        val prereq = grammar[lineNumber].split(",")[1].replace("\r", "")
        val prereqLineNumber = getLineOfPrereq(prereq)
        val prereqDone = selectedContent.find { content ->
            content.partOfSpeech == GrammarPartOfSpeech.PREREQS &&
                    content.line == prereqLineNumber &&
                    content.stage >= SpacedRepetition.THRESHOLD_OF_PROBABILISTIC_MASTERY
        }

        if (prereqDone == null) {
            return Content(
                0,
                0,
                ContentType.GRAMMAR,
                prereqLineNumber,
                0,
                langCode,
                GrammarPartOfSpeech.PREREQS
            )
        } else {
            return Content(0, 0, ContentType.GRAMMAR, lineNumber, 0, langCode, pos)
        }
    }
}