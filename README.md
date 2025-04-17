# 2D World Game Backend

Live Demo: [https://alenas.netlify.app/]

This is the Spring Boot backend for the 2D World Generation Game. It handles game logic, procedural world generation, player movement, enemy AI, and per-session game state management.

## Features

- Procedural map generation (rooms + hallways)
- Per-session game state (tracked in memory)
- Player & enemy movement
- Win/loss detection
- REST API for game interaction
- Coin collection and world regeneration

## Built With

- Java 17+
- Spring Boot
- Maven

## Getting Started

### 1. Build and run:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Server will start at: `http://localhost:8080`

## REST API Endpoints

| Method | Endpoint             | Description                          |
|--------|----------------------|--------------------------------------|
| GET    | `/game`              | Create a new game session            |
| GET    | `/game/{id}`         | Get game state for session `{id}`    |
| POST   | `/game/{id}/move`    | Move player (`W`, `A`, `S`, `D`)      |

**Sample request body:**
```json
{
  "direction": "D"
}
```

## Architecture Overview

### `GameService`
- Manages multiple game sessions
- Acts as the API-facing service
- Routes input to the correct session's game state

### `WorldState` (**core of the game**)
- Maintains the state of a single game session:
  - Player/enemy positions
  - Coin positions
  - Win/loss state
- Handles movement, coin collection, enemy AI
- Automatically regenerates the world when all coins are collected

### `World`, `RoomGenerator`, `HallwaysGenerator`
- Generates the procedural tile-based world
- Adds random rooms and connects them with hallways
- Connects rooms using **Kruskal's algorithm** to ensure full connectivity via hallways (Minimum Spanning Tree)

### `EnemyPathGenerator`
- Uses BFS to generate paths from enemy to player

## Project Structure

```
src/
  └── main/java/sejevic/com/_DWorldGenerationGame/
      ├── core/
      │   ├── game/         # Game session and controller
      │   ├── world/        # WorldState and map
      │   ├── room/         # Room generator
      │   ├── hallway/      # Hallway generator
      │   ├── enemy/        # Enemy logic
      │   ├── player/       # Player model
      │   └── utils/        # Coordinate, pathfinding, etc.
```

##  License

MIT
