package com.example.sportsdunia.data.source

import com.example.sportsdunia.data.model.Event
import com.example.sportsdunia.data.model.Player
import javax.inject.Inject

class MockDataSource @Inject constructor() {

    fun getPlayers(): List<Player> {
        // Mock data from players.json
        return listOf(
            Player(id = 101, name = "Virat K."),
            Player(id = 102, name = "Pat C."),
            Player(id = 103, name = "Kane W."),
            Player(id = 104, name = "Ben S."),
            Player(id = 105, name = "Ravi A.")
        )
    }

    fun getEvents(): List<Event> {
        // Mock data from events.json
        return listOf(
            Event(playerId = 101, action = "50_RUNS_MILESTONE"), // +15
            Event(playerId = 102, action = "TAKE_WICKET"),       // +20
            Event(playerId = 101, action = "HIT_FOUR"),          // +1
            Event(playerId = 103, action = "HIT_SIX"),           // +2
            Event(playerId = 102, action = "TAKE_WICKET"),       // +20 (Total 40)
            Event(playerId = 104, action = "HIT_FOUR"),          // +1
            Event(playerId = 101, action = "HIT_SIX"),           // +2 (Total 18)
            Event(playerId = 103, action = "50_RUNS_MILESTONE"), // +15 (Total 17)
            Event(playerId = 105, action = "HIT_SIX"),           // +2  (Total 2)
            Event(playerId = 104, action = "TAKE_WICKET"),       // +20 (Total 21)
        )
    }
}