package ga.harrysullivan.langsy.utils

object Validation {
    fun validate(s1: String, s2: String): Boolean {
        val notEmpty = normalize(s1).isNotEmpty()
        val hasTargetWord = has(s1, s2)
        return notEmpty && hasTargetWord
    }

    private val normalize = { s: String -> s.toLowerCase().trim() }

    private fun has(s1: String, s2: String): Boolean {
        return normalize(s1).indexOf(normalize(s2)) != -1
    }
}