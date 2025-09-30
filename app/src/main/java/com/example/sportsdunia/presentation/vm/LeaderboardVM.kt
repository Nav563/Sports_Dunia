package com.example.sportsdunia.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsdunia.domain.model.PlayerStats
import com.example.sportsdunia.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LeaderboardVM @Inject constructor(
    repository: PlayerRepository
) : ViewModel() {

    // Internal full list of stats (fetched from repository)
    private val _fullStats = MutableStateFlow<List<PlayerStats>>(emptyList())

    // Internal state for the filter toggle
    private val _isTopPerformersMode = MutableStateFlow(false)
    val isTopPerformersMode: StateFlow<Boolean> = _isTopPerformersMode

    // Public state for the UI (filtered list)
    private val _leaderboardState = MutableStateFlow<List<PlayerStats>>(emptyList())
    val leaderboardState: StateFlow<List<PlayerStats>> = _leaderboardState

    init {
        // 1. Collect full stats from the repository and update _fullStats.
        // Use onEach and launchIn for collecting a Flow in ViewModelScope.
        repository.getLeaderboardStats()
            .onEach { stats ->
                _fullStats.value = stats
            }
            .launchIn(viewModelScope)

        // 2. Combine full stats and filter mode to calculate the displayed list.
        _fullStats.combine(_isTopPerformersMode) { stats, isFiltered ->
            if (isFiltered) {
                // Filter logic: score of 20 or more
                stats.filter { it.mvpScore >= 20 }
            } else {
                // Show all players
                stats
            }
        }
            // Collect the result of the combination and update _leaderboardState.
            .onEach { filteredStats ->
                _leaderboardState.value = filteredStats
            }
            .launchIn(viewModelScope)
    }

    fun toggleTopPerformers() {
        _isTopPerformersMode.value = !_isTopPerformersMode.value
    }
}