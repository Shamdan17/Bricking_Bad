## Save the game System Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant System
System->> User: showPauseMenu
User->> System: clickSave
System-->>User: enterSaveName
User->> System: submitSaveName
System-->>User: printSaveSuccessful

```
