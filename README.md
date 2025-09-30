# 🏏 Player Stats Leaderboard 🏆

## 📌 Project Overview
This project builds a **Player Stats Leaderboard** that calculates a "Most Valuable Player (MVP)" score for each player based on match events.  
Instead of just displaying raw data, the app **computes scores using defined rules** and presents a dynamic leaderboard.  

---

## 🎯 Objectives
- Fetch data from **two mock JSON sources**:
  - `players.json` → List of players
  - `events.json` → Log of match events
- Compute each player's **MVP Score** based on defined rules.
- Display a **leaderboard** sorted by MVP Score.
- Provide a **"Toggle Top Performers"** filter button.

---

## 🧮 MVP Scoring Rules
- `TAKE_WICKET` → **+20 points**  
- `50_RUNS_MILESTONE` → **+15 points**  
- `HIT_SIX` → **+2 points**  
- `HIT_FOUR` → **+1 point**

---

## 🖼️ Features
- ✅ Fetch and merge data from players & events  
- ✅ Compute MVP scores dynamically  
- ✅ Leaderboard sorted from **highest → lowest score**  
- ✅ **Toggle Top Performers** button to filter players with score ≥ 20  
- ✅ Simple, clean UI for cricket fans and developers to extend  

---

## 📊 Example Data

### players.json
```json
[
  { "id": 101, "name": "Virat K." },
  { "id": 102, "name": "Pat C." }
]
```

### event.json
```json
[
  { "playerId": 101, "action": "50_RUNS_MILESTONE" },
  { "playerId": 102, "action": "TAKE_WICKET" },
  { "playerId": 101, "action": "HIT_FOUR" }
]
```

🚀 Tech Stack

Language/Framework: (Kotlin / Jetpack Compose)

Data: Mock JSON files

State Management: (Kotlin Flow / ViewModel)

## 📸 Screenshots

### 🏆 Leaderboard Screen
![Sposts_Dunia](screenshots/leaderboard.png)

![image alt](https://github.com/Nav563/Sports_Dunia/blob/21abbc0f23a4c57c2638e56866c58a3d72ab7f22/WhatsApp%20Image%202025-09-30%20at%2012.43.43.jpeg)

![Sports_Dunia]()
