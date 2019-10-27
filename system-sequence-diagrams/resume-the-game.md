## resume the game Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
User->> Game: requestResumeGame()
Game-->>User: removePauseScreen
Game-->>User: resumeGame
```
