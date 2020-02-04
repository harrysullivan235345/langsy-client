package ga.harrysullivan.langsy.data

import ga.harrysullivan.langsy.models.Content

data class Trainer(val content: String, val translation: String, val contentObj: Content) {
    override fun toString(): String {
        return "$content, $translation, $contentObj"
    }
}