## Use power up System Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant System
User->> System: usePowerup(type)
System->>User : activatePowerup(type)
System->>User: updatePUCount(type)
```
