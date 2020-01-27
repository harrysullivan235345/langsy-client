package ga.harrysullivan.langsy.utils

import android.app.Application
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.GrammarPartOfSpeech
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

    fun getTrainer(content: Content): Trainer {
        if (content.type == ContentType.GRAMMAR) {

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

    fun getGrammar(langCode: String): Content {
        val pos = Random.choose(GrammarPartOfSpeech.ALL)

        val vocabFilename = "corpora/language-content/grammar/$pos.csv"
        val vocab = readFile(vocabFilename).split('\n')

        val lineNumber = Random.int(vocab.size)

        return Content(0, 0, ContentType.GRAMMAR, lineNumber, 0, langCode, pos)
    }
}