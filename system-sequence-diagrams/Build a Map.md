## Build a Map System Sequence Diagram

```mermaid
sequenceDiagram
participant User
participant System
User->> System: activateBuildingMode()
System-->>User: showBuildingScreen
User->> System: setBrickNumbers(s, hm, m, w)
System-->>User: randomMapWithBricks
loop more brick
	User->> System: moveBrickToPosition(b, p)
	System-->>User: updatedMap
end
User->> System: saveMap(mapName)
System-->>User: mapSaveSuccess

```
## Glossary 

* s: simple : number of simple brick
* hm: halfMetal : number of half-metal brick
* m: mine : number of mine brick
* w: wrapper : number of wrapper brick
* b: brick
* p: position