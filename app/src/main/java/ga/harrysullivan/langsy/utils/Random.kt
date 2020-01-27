package ga.harrysullivan.langsy.utils

object Random {
    fun choose(list: List<String>): String {
        val size: Int = list.size
        val position: Int = (Math.random() * size).toInt()
        return list[position].toLowerCase()
    }

    fun int(max: Int): Int {
        return (Math.random() * max).toInt()
    }
}