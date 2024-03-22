# PB162 seminar project

## Objectives
* Abstract classes and methods.

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

1. Extend the functionality of the `Game.move()` method. Actually, the method moves a piece from one 
   position to another one. However, we want to detect and support so-called **promotion**.
   During a chess game, if the moved piece is a pawn and is to be placed on the last (opposite) line of the board, 
   then the piece turns into a queen. During a draughts game, if the moved piece is a man and is to be placed 
   on the last (opposite) line of the board, then the man is replaced with a king.
   - Extend the functionality of the `move` method so that it is able to detect promotion in chess and draughts. 
     Put a specific code to subclasses, leave the common code in the `Game` class, and re-use it.
2. Add an `updateStatus` method to desktop games. The method aims to check whether the game has finished and then 
   change the status (the winner) of the game appropriately.
   - This method must be implemented in all games. On the other hand, the implementation differs in specific
     games. Your goal is to enforce the presence (implementation) of the method in the specific game while
     providing no default implementation (as it does not exist).
     > Review abstract methods and classes.
   - Add a public `getAllPiecesFromBoard()` method to the `Board` that returns the array of all pieces lying on the board.
     Use this method to implement the `updateStatus`.
   - For chess, the game ends if there is only one king on the board.
   - For draughts, the game ends if there are no pieces of white or black color on the board.
   - The `updateStatus` method is meant to be used by only the `Game` class and their subclasses. 
     Therefore, choose the correct visibility.
     > Can the method be declared private?
3. Add the `void play()` method into the `Playable` interface. This method aims to demonstrate the gameplay.
   - It loops until the end of the game. 
   - In each loop, it finds out which player is next, gets input from the player (from standard input), 
     increases the round by one, and makes a move. Also, the state of the game is updated automatically
     (see the `updateStatus`).
   - The input from the user consists of a board position from which (s)he wants to move the piece
     and a position to which (s)he wants to move the piece. For example:
     ```
     a2 a3
     ```
   - For reading user input, use the following *private constant* and *non-public method*.
     Set proper visibility and scope. Study the code and discuss unclear aspects with your tutor.
      ```java
      Scanner SCANNER = new Scanner(System.in);
      
      private Position getInputFromPlayer() {
         var position = SCANNER.next().trim();
         char column = position.charAt(0);
         int line = Integer.parseInt(String.valueOf(position.charAt(1)));
         return new Position(column, line);
      }
      ```
      > Note that the `play` method in the `Game` class can invoke the `updateStatus` method, which
        is abstract in the class. The `play` method is so-called 
        [template method](https://en.wikipedia.org/wiki/Template_method_pattern).
4. Overload the `toString` method of the `Board` so that it returns the current 
   layout of pieces on the board.
    - Use `StringBuilder` instead of adding strings using the plus sign.
    - **Use `System.lineSeparator()` instead of `\n`.**
    - Update the `Main` class so that it instantiates chess and draughts games and prints 
      the layout of their initial board. Check the output that should look like follows:
```
    A   B   C   D   E   F   G   H 
  --------------------------------
8 | R | K | B | Q | K | B | K | R |
  --------------------------------
7 | P | P | P | P | P | P | P | P |
  --------------------------------
6 |   |   |   |   |   |   |   |   |
  --------------------------------
5 |   |   |   |   |   |   |   |   |
  --------------------------------
4 |   |   |   |   |   |   |   |   |
  --------------------------------
3 |   |   |   |   |   |   |   |   |
  --------------------------------
2 | P | P | P | P | P | P | P | P |
  --------------------------------
1 | R | K | B | Q | K | B | K | R |
  --------------------------------
```
```
    A   B   C   D   E   F   G   H 
  --------------------------------
8 |   | D |   | D |   | D |   | D |
  --------------------------------
7 | D |   | D |   | D |   | D |   |
  --------------------------------
6 |   | D |   | D |   | D |   | D |
  --------------------------------
5 |   |   |   |   |   |   |   |   |
  --------------------------------
4 |   |   |   |   |   |   |   |   |
  --------------------------------
3 | D |   | D |   | D |   | D |   |
  --------------------------------
2 |   | D |   | D |   | D |   | D |
  --------------------------------
1 | D |   | D |   | D |   | D |   |
  --------------------------------
```

## Takeaways
* Make methods public only if they are really needed by external code. As soon as you make 
  a method public, it is very difficult to remove or modify it. Moreover, unnecessary public methods
  can increase the complexity of dependencies among objects, making the system less maintainable.
* Make methods private only if you want to protect them from being used in subclasses. A special
  case is if they are invoked from constructors (recall the discussion in the previous iteration).
* Make a method abstract (either public or protected) if you want to enforce its implementation
  in subclasses. See also the [template method](https://en.wikipedia.org/wiki/Template_method_pattern)
  design pattern to learn more about the possible usage.
* Note: A final method cannot be overridden. A final class cannot be extended (i.e., record is the final class).

## Features to be manually checked by tutors 
* JavaDoc `Playable.play`
* The usage of `equals()` in `move()`
* Abstract and protected `updateStatus` in the `Game`
* Private static final scanner
* The logic in the `play()` method
