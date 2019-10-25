## resume the game System Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant System
User->> System: requestResumeGame()
System-->>User: removePauseScreen
System-->>User: resumeGame
```
