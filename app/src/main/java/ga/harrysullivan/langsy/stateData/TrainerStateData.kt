package ga.harrysullivan.langsy.stateData

import ga.harrysullivan.langsy.models.Content

data class TrainerStateData(
    val content: String,
    val translation: String,
    val contentRef: Content
)