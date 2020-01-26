package ga.harrysullivan.langsy.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Content(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var stage: Int,
    var type: String,
    val line: Int,
    val lastReviewed: Long,
    val language: String
) {
    override fun toString(): String {
        return "AppIdea(uid=$uid, stage='$stage', type='$type', line='$line', lastReviewed='$lastReviewed', language='$language')"
    }
}