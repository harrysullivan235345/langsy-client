package ga.harrysullivan.langsy.constants

object ReinforcementSchedule {
    fun makeRightReward(): Double {
        return Math.random() * 13
    }
}