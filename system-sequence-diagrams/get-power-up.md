## Get Power-Up Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
Game-->>User: ReleasePowerup(pt)
User->> Game: movePaddle
alt IsManualPU(pt)
	Game-->>User: addPUtoInventory(pt)
else else
	Game-->>User: activatePU(pt)
end
```

## Glossary 

* pt: power-up type : the type of power-up
