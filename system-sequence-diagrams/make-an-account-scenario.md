## Make an Account Scenario Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
User->> Game: runGame
Game-->>User: showLoginScreen
User->> Game: createNewAcc
Game-->>User: showAccCreationScreen
User->> Game: createAcc(User, Password)
Game-->>User: showAccountCreationSuccess

```