## Use power up Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
User->> Game: usePowerup(pt)
Game-->>User : activatePowerup(pt)
Game-->>User: updatePUCount(pt)
```

## Glossary 

* pt: power-up type : the type of power-up  Get Power-Up Game Sequence Diagram

 