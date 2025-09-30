package com.example.sportsdunia.domain.repository

import com.example.sportsdunia.domain.model.PlayerStats
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun getLeaderboardStats(): Flow<List<PlayerStats>>
}