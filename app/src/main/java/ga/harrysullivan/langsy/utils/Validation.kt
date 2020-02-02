package ga.harrysullivan.langsy.utils

object Validation {
    fun validate(s1: String, s2: String): Boolean {
        val notEmpty = s1.isNotEmpty()
        val hasTargetWord = has(s1, s2)
        return notEmpty && hasTargetWord
    }

    private fun has(s1: String, s2: String): Boolean {
        return s1.indexOf(s2) != -1
    }
}