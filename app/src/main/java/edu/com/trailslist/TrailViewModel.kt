package edu.com.trailslist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TrailViewModel: ViewModel() {
    var selectedTrailId by mutableStateOf(-1)
        private set
    var isTracking by mutableStateOf(false)
        private set
    var elapsedTime by mutableStateOf(0L)
        private set

    fun updateSelectedTrailId(newId: Int) {
        selectedTrailId = newId
    }

    fun updateIsTracking(newVal: Boolean) {
        isTracking = newVal
    }

    fun updateElapsedTime(newElapsedTime: Long) {
        elapsedTime = newElapsedTime
    }
}