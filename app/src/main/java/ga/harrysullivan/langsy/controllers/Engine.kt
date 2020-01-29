package ga.harrysullivan.langsy.controllers

import android.app.Application
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.utils.Corpora

object Engine {
    fun practice(
        selectedContent: List<Content>,
        application: Application
    ): Trainer {

        val sorted =
            selectedContent.sortedBy { it.lastReviewed + SpacedRepetition.STAGES[it.stage - 1] }
        val content = sorted.first()
        val trainer = Corpora(application).getTrainer(content)
        return trainer
    }
}