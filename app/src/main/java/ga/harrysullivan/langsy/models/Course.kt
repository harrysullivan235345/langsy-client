package ga.harrysullivan.langsy.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var language: String,
    var cash: Double
) {
    override fun toString(): String {
        return "AppIdea(uid=$uid, cash='$cash', language='$language')"
    }
}