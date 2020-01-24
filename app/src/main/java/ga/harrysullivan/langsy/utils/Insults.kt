package ga.harrysullivan.langsy.utils

import android.app.Application

class Insults(application: Application) {
    private val mApplication: Application

    init {
        mApplication = application
    }

    private fun getFileNumber(): Int {
        return (1 + Math.random() * 24).toInt()
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

    fun getInsult(): String {
        val filename = "corpora/insults/insults_${this.getFileNumber()}.txt"
        val contents = readFile(filename)
        return selectFromContents(contents)
    }
}