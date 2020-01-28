package ga.harrysullivan.langsy.constants

object GrammarPartOfSpeech {
    const val PREREQS = "prereqs"
    const val POSITIVE = "positive"
    const val NEGATIVE = "negative"
    const val QUESTION = "question"

    val ALL = listOf<String>(POSITIVE, NEGATIVE, QUESTION)
}