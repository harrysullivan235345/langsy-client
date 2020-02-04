package ga.harrysullivan.langsy.utils

object CourseList {
    val languages = listOf<List<String>>(
        listOf<String>("sv", "svenska"),
        listOf<String>("ar", "العربية"),
        listOf<String>("de", "deutsch"),
        listOf<String>("fr", "français"),
        listOf<String>("ru", "русский"),
        listOf<String>("it", "Italiano"),
        listOf<String>("es", "Español"),
        listOf<String>("pl", "Polski"),
        listOf<String>("ja", "日本語"),
        listOf<String>("pt", "Português"),
        listOf<String>("uk", "українська"),
        listOf<String>("fa", "فارسی"),
        listOf<String>("hi", "हिन्दी"),
        listOf<String>("tr", "türkçe"),
        listOf<String>("th", "ไทย"),
        listOf<String>("sw", "kiswahili")
    )

    fun localFromCode(code: String): String {
        return languages.find { language -> language[0] == code }!![1]
    }
}