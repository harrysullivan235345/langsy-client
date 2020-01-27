package ga.harrysullivan.langsy.data

data class Trainer(val content: String, val translation: String) {
    override fun toString(): String {
        return "$content, $translation"
    }
}