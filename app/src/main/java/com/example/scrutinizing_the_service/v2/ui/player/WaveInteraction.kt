package com.example.scrutinizing_the_service.v2.ui.player

fun interface WaveInteraction {
  /**
   * [horizontalProgressPercentage] is a value that shows where in the horizontal spectrum the wave was interacted
   * with.
   * Ranges from 0.0f when interacted on the far left to 1.0f on the far right.
   */
  fun onInteraction(horizontalProgressPercentage: ProgressPercentage)
}