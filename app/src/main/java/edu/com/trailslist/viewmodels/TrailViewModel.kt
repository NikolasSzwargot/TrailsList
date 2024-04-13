package edu.com.trailslist.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import edu.com.trailslist.database.dao.insertTrails
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.database.provider.TrailDaoProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TrailViewModel(private val trailDaoProvider: TrailDaoProvider): ViewModel() {

    private val _trails: MutableStateFlow<List<Trail>> = MutableStateFlow(emptyList())
    val trails: StateFlow<List<Trail>> = _trails
    var selectedTrailId by mutableStateOf(-1)
        private set
    var isTracking by mutableStateOf(false)
        private set
    var elapsedTime by mutableStateOf(0L)
        private set
    var choosenTrail by mutableStateOf<Trail?>(null)
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

    fun recordMeasuredTime(id: Int, time: Long) {
        viewModelScope.launch {
            val dao = trailDaoProvider.provideTrailDao()
            dao.updateMeasuredTime(id, time)
        }
    }

    fun insertTrailsIfEmpty(callback: () -> Unit) {
        viewModelScope.launch {
            val dao = trailDaoProvider.provideTrailDao()
            val tempTrails = dao.getTrailsOrderedByName().first()
            if (tempTrails.isEmpty()) {
                insertTrails(dao)
                callback()
            } else {
                _trails.value = tempTrails
            }
        }
    }

    fun observeMeasuredTime(trailId: Int): Flow<Long?> {
        val dao = trailDaoProvider.provideTrailDao()
        return dao.getMeasuredTime(trailId).asFlow()
    }
}