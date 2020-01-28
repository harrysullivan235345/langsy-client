package ga.harrysullivan.langsy.constants

object SpacedRepetition {
    val STAGES = intArrayOf(25, 2*60, 10*60, 60*60, 5*60*60, 25*60*60, 4*30*60*60) // seconds
    const val WORKING_MEMORY_CAPACITY = 5
    const val THRESHOLD_OF_PROBABALISTIC_MASTERY = 5
    val THRESHOLD_OF_MASTERY = STAGES.size
    const val MAX_GRAMMAR = 1
}