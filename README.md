# PB162 seminar project

## Objectives
* Maps
* Sorted containers
* Anonymous classes

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

1. Make the `Board` cloneable, i.e., implementing the `Prototype` interface. The returned clone 
   represents a deep copy. This means that any change on the original board will not affect the clone.
   > Use `Arrays.copyOf(originalArray, length); to copy arrays`
2. Add the `Tournament` class. This class aims to store the history (states of boards) of multiple games 
   played simultaneously. Use a single map attribute to implement the following functions:
   - The `void storeGameState(Game game)` method records the current state of the board of the given game.
     Adds the game into the tournament if the game does not exist yet.
     Make the method as efficient as possible(constant time).
   - The `Collection<Board> getGameHistory(Game game)` method returns history of the game board. 
     Returns an empty collection if the game does not exist in the tournament.
     Make the method as efficient as possible (constant time).
     > Be aware of encapsulation!
   - The `Collection<Game> findGamesOfPlayer(String name)` method returns all games in which the player with the given 
     name is involved. Returns an empty collection if there is no such game.
     Use the for-each cycle without stream. The efficiency can be suboptimal (linear time).
3. Define **natural ordering** for `Position` so that positions are sorted from left to right
   and bottom to top (when looking at the board): a1 < a2 < a3 < ... < b1 < b2 < b3 ...  
   > Natural ordering has to correspond to equality. But the `Position` record has 
   > no `equals` and `hashCode` defined. Nevertheless, the record satisfies this condition. Why?
4. Define inverse ordering to the natural ordering of board positions. Name the class `PositionInverseComparator`.
   Reuse the code of natural ordering in the implementation.

In what follows, the goal is to practice various syntax constructions when using ordering.
Therefore, don't look for any deeper meaning behind the functional requirements.

5. Add the following methods to the `Board` class. They aim to return a sorted collection of pieces on the board.
   Exceptionally, don't reuse the methods (don't call each other). Copy-and-past the same code instead so that
   you can see the differences explicitly.
   - Add the helper (not public) method and use it for the implementation of the remaining methods:
     ```java
     protected void addOccupiedPositions(SortedSet<Position> positions) {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Position pos = new Position(i, j);
                if (getPiece(pos) != null) {
                    positions.add(pos);
                }
            }
        }
     }
     ``` 
   - Add the `SortedSet<Position> getOccupiedPositionsA()` method, which returns positions with a piece
     sorted according to step 4 (i.e., the inverse ordering to the natural ordering). 
     Use `PositionInverseComparator` for the implementation.
   - Add the `SortedSet<Position> getOccupiedPositionsB()` method that does the same as the previous one
     but defines the inverse ordering as **anonymous class** inside this method (without using lambda expressions).
   - Add the `SortedSet<Position> getOccupiedPositionsC()` method that does the same as the previous one
     but defines the inverse ordering as **lambda expression**.
     > Discuss when it is possible to replace the anonymous class with a lambda expression and when not.
   - Add the `SortedSet<Position> getOccupiedPositionsD()` method that does the same as the previous one,
     but the inverse ordering is obtained from the natural ordering without any additional line of code.
     > Study the `Collections` utility class
   
## Takeaways
* When working with maps, the definition of equality (`equals` and `hashCode`) is crucial
  because `equals` is used in keys (what does it mean that two games are the same?)
  and `hashCode` is used for saving values (poorly implemented `Gema.hashCode()` can degrade performance).
* The natural ordering is only one. On the contrary, you can define multiple comparators.
* Define a comparator as a regular class only if it is used in multiple places in the code.
  Otherwise, use lambda expressions.
* Do not define inverted ordering to an existing ordering. There is always some way to directly invert
  an existing order.

## Features to be manually checked by tutors 
*  
