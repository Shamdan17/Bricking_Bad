## Make an Account Scenario Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant System
User->> System: runGame
System-->>User: showLoginScreen
User->> System: createNewAcc
System-->>User: showAccCreationScreen
User->> System: createAcc(User, Password)
System-->>User: showAccountCreationSuccess

```