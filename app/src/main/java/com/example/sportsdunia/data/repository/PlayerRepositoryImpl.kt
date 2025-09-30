package com.example.sportsdunia.data.repository

import com.example.sportsdunia.data.source.MockDataSource
import com.example.sportsdunia.domain.model.PlayerStats
import com.example.sportsdunia.domain.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// MVP Score Rules
private const val TAKE_WICKET_POINTS = 20
private const val FIFTY_RUNS_POINTS = 15
private const val SIX_POINTS = 2
private const val FOUR_POINTS = 1

class PlayerRepositoryImpl @Inject constructor(
    private val dataSource: MockDataSource
) : PlayerRepository {

    override fun getLeaderboardStats(): Flow<List<PlayerStats>> = flow {
        //  Fetch raw data
        val players = dataSource.getPlayers()
        val events = dataSource.getEvents()

        //  Initialize a map for calculating scores
        val scoreMap = players.associate { it.id to 0 }.toMutableMap()

        //  Process events to calculate scores
        for (event in events) {
            val points = when (event.action) {
                "TAKE_WICKET" -> TAKE_WICKET_POINTS
                "50_RUNS_MILESTONE" -> FIFTY_RUNS_POINTS
                "HIT_SIX" -> SIX_POINTS
                "HIT_FOUR" -> FOUR_POINTS
                else -> 0 // Ignore unknown actions
            }
            // Add points to the player's current score
            val currentScore = scoreMap[event.playerId] ?: 0
            scoreMap[event.playerId] = currentScore + points
        }

        //  Map raw players to PlayerStats with calculated scores
        val playerStatsList = players.map { player ->
            PlayerStats(
                id = player.id,
                name = player.name,
                mvpScore = scoreMap[player.id] ?: 0
            )
        }

        //  Sort the list (Highest to Lowest score)
        val sortedList = playerStatsList.sortedByDescending { it.mvpScore }

        //  Emit the final calculated and sorted leaderboard
        emit(sortedList)

    }.flowOn(Dispatchers.Default) // Use Dispatchers.Default for heavy calculation
}