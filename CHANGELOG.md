 - Updated Coord constructor to take in JSon properties x and y.
- Created private field in AutomatedPlayer class that gets mutated when successfulHits is called.
- Changed method listOfShots in AutoAttacks class to take in list of successful hits.
- Modified listOfShots method to take shots adjacent to successful shots.

- Changed the Board interface methods to modify a board field insteam of returning a new one each time
- Fixed the BattleSalvo controller to work along with this
- Changes to ship placement to include height and width
- Changed autoAttacks to a field in AutoPlayer 
- improved the AI