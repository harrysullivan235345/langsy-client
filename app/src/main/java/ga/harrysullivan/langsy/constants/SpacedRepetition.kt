package ga.harrysullivan.langsy.constants

object SpacedRepetition {
    //                      25 sec    2 min    10 min     1 hr     5 hr       1 day       25 days       4 months
    val STAGES = intArrayOf(
        25,
        2 * 60,
        10 * 60,
        60 * 60,
        5 * 60 * 60,
        24 * 60 * 60,
        25 * 24 * 60 * 60,
        4 * 30 * 24 * 60 * 60
    )
    const val WORKING_MEMORY_CAPACITY = 5
    const val THRESHOLD_OF_PROBABILISTIC_MASTERY = 4
    val THRESHOLD_OF_MASTERY = STAGES.size
    const val MAX_GRAMMAR = 1
}