package com.example.trackapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackapp.db.Run
import com.example.trackapp.other.SortType
import com.example.trackapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunSortedByDate()
    private val runsSortedByDistance = mainRepository.getAllRunSortedByDistance()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunSortedByCaloriesBurned()
    private val runsSortedByTimeInMillis = mainRepository.getAllRunSortedByTimeInMillis()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunSortedByAvgSpeed()


    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATA

    init {
        runs.addSource(runsSortedByDate){ result ->
            if (sortType == SortType.DATA){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed){ result ->
            if (sortType == SortType.AVG_SPEED){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned){ result ->
            if (sortType == SortType.CALORIES_BURNED){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance){ result ->
            if (sortType == SortType.DISTANCE){
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByTimeInMillis){ result ->
            if (sortType == SortType.RUNNING_TIME){
                result?.let { runs.value = it }
            }
        }
    }

    fun sortsRun(sortType: SortType) = when(sortType){
        SortType.DATA -> runsSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }


    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}