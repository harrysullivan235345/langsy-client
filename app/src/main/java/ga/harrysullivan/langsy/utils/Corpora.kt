package ga.harrysullivan.langsy.utils

import android.app.Application

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

    private fun selectFromContents(contents: String): String {
        val split: List<String> = contents.split("\n")
        val size: Int = split.size
        val position: Int = (Math.random() * size).toInt()
        return split[position].toLowerCase()
    }

    fun getVocab(): String {
        val filename = "corpora/language-content/.txt"
        val contents = readFile(filename)
        return selectFromContents(contents)
    }

    fun getGrammar(): String {
        val filename = "corpora/language-content/.txt"
        val contents = readFile(filename)
        return selectFromContents(contents)
    }
}