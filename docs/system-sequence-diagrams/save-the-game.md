## Save the game Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
Game-->> User: showPauseMenu
User->> Game: clickSave
Game-->>User: getSaveNamePrompt
User->> Game: submitSaveName
Game-->>User: showSaveSuccessful

```
