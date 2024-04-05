# PB162 seminar project

## Objectives
* Basic collections

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

To play a game, we need to know the possible movements of different types of pieces. We could encapsulate this 
knowledge into the `Piece` class directly and use inheritance to variate this behavior (i.e., define subclasses for 
knight, queen, etc.). Our approach will be different. Movements, e.g., in a diagonal direction, straight direction, 
or jumping over an opponent's pieces, can be considered _moving strategies_. Therefore, the idea lies in having only 
a single generic `Piece` class whose behavior is adapted by dynamically setting the moving strategies. 
This kind of decomposition is known as the [Strategy](https://refactoring.guru/design-patterns/strategy) design pattern.

1. Study the `Move` interface method in the `moves` package. Focus primarily on the `getAllowedMoves`
   method and implementing classes that define several movement strategies for pieces of chess and draughts.
2. Adapt the `Piece` class
   - A piece stores a list of movement strategies. The list is initiated by 
     the constructor using the following code:
     ```
     switch (pieceType) {
		case KING -> List.of(new Straight(1), new Diagonal(1));
		case QUEEN -> List.of(new Straight(), new Diagonal());
		case BISHOP -> List.of(new Diagonal());
		case ROOK -> List.of(new Straight());
		case KNIGHT -> List.of(new Knight());
		case PAWN -> List.of(new Pawn());
		case DRAUGHTS_KING -> List.of(new Diagonal(1), new Jump());
		case DRAUGHTS_MAN -> List.of(new Diagonal(1, true), new Jump(true));
	 };
     ```
   - Add the `getMovementStrategies()` method, which returns the list of movement strategies.
     Be aware of violating encapsulation when returning a private collection.
     > Discuss when it's necessary to use `Collections.unmodifiable*`. 
     > Why is it not required (exceptionally!) in this special case?
   - Add the `Set<Position> getAllPossibleMoves(Game game)` method. 
     While the `getMovementStrategies()` getter returns moving strategies, this method finds and returns valid 
     target positions on the game's board, taking into account the position of the piece. More precisely, the method:
     - finds the position of the pieces on the game's board;
     - takes movement strategies one by one;
     - for each strategy, it finds all allowed moves of the piece oin the game 
       (i.e., possible target positions for the strategy);
     - puts found target positions into the output set.
     > Don't use streams! Consider the consequences of setting the output set as modifiable/unmodifiable.
     > Note that the `Piece` class remains immutable.

3. Add the `removePieces(Set<Piece> toRemove)` method to the `Board` class. 
   - Removes all pieces included in the input set from the board. 
   - Returns positions of removed pieces. The order of positions in the output collection is not important.
     Select the proper return type. 
   - Be aware that there can be multiple pieces of the same type and color on the board, e.g., multiple white pawns. 
     All of them have to be removed if a white pawn is in the input set.
   - An empty collection is returned if no piece has been removed (for any reason).
   > Don't use streams
   
## Takeaways
* Never return your private modifiable collections directly. Always create an unmodifiable wrapper via `Collections.unmodifiable*`.
* The `List.of()` and similar methods create unmodifiable collections!
* Always use the closest collection interface as a collection type.
  - Correct: `Set<String> col = new HashSet<>();`.
  - Incorrect: `HashSet<String> col = new HashSet<>();`
  - Incorrect: `Collection<String> col = new HashSet<>();`

## Features to be manually checked by tutors 
*  
