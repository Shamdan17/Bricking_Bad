## Save the game Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
Game-->> User: showPauseMenu
User->> Game: clickSave
Game-->>User: enterSaveName
User->> Game: submitSaveName
Game-->>User: printSaveSuccessful

```
