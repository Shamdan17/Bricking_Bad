## Build a Map Game Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant Game
User->> Game: activateBuildingMode()
Game-->>User: showBuildingScreen
User->> Game: setBrickNumbers(s, hm, m, w)
Game-->>User: randomMapWithBricks
loop more brick
	User->> Game: moveBrickToPosition(b, p)
	Game-->>User: updatedMap
end
User->> Game: saveMap(mapName)
Game-->>User: mapSaveSuccess

```
## Glossary 

* s: simple : number of simple brick
* hm: halfMetal : number of half-metal brick
* m: mine : number of mine brick
* w: wrapper : number of wrapper brick
* b: brick
* p: position