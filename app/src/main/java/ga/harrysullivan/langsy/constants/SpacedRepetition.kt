package ga.harrysullivan.langsy.constants

object SpacedRepetition {
    val STAGES = intArrayOf(
        5, // 5 sec
        25, // 25 sec
        2 * 60, // 2 min
        10 * 60, // 10 min
        60 * 60, // 1 hr
        5 * 60 * 60, // 5 hr
        24 * 60 * 60, // 1 day
        25 * 24 * 60 * 60, // 25 days
        4 * 30 * 24 * 60 * 60 // 4 months
    )
    const val WORKING_MEMORY_CAPACITY = 5
    const val THRESHOLD_OF_PROBABILISTIC_MASTERY = 4
    val THRESHOLD_OF_MASTERY = STAGES.size
    const val MAX_GRAMMAR = 1
}