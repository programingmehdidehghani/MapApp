package com.example.trackapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackapp.db.Run
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

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}