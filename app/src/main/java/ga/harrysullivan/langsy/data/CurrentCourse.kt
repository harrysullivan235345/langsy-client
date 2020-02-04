package ga.harrysullivan.langsy.data

import ga.harrysullivan.langsy.models.Course

data class CurrentCourse(val course: Course) {
    override fun toString(): String {
        return "$course"
    }
}