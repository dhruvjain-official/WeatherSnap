package com.yourname.weathersnap.presentation.report

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourname.weathersnap.data.ReportRepository
import com.yourname.weathersnap.data.local.WeatherReportEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repository: ReportRepository
) : ViewModel() {

    var reports by mutableStateOf<List<WeatherReportEntity>>(
        emptyList()
    )
        private set

    init {
        loadReports()
    }

    private fun loadReports() {

        viewModelScope.launch {

            reports = repository.getAllReports()
        }
    }

    fun saveReport(
        report: WeatherReportEntity
    ) {

        viewModelScope.launch {

            repository.insertReport(report)

            loadReports()
        }
    }
}