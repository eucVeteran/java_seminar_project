# PB162 seminar project

## Objectives
* Practice the knowledge gained so far. 

## Tasks
We want to use fancy black-and-white Unicode icons instead of boring letters when showing the game board.
The icons are defined in the `pieces.txt` file. If you open the file in the IDE and see Unicode values,
e.g., "\u2654", instead of fancy icons, you need to set a monospace font in the settings, e.g.,
DejaVu Sans Mono, Everson Mono, FreeSerif, Chrysanthi Unicode, etc.

1. Update the `Piece` class so that it is displayed as an icon of the correct color instead of a letter.
   - You need to store a fancy icon for each combination of a piece's color and type. 
     Decide how to do this efficiently. Add an appropriate attribute to the `Piece` class.
   - Refactor the `toString()` method so that it returns an appropriate icon based on the type and color
     of the specific piece. To get the icons, you have to read them from the file. However, 
     the icons are read from the file only once **and not sooner than firstly required** (lazy reading),
     i.e., after only the first call of the `Piece.toString()` method (and never again).
     > Icons are encoded as Unicode characters. Therefore, read them as ordinary `String` values.
   - If anything goes wrong during the icons reading, then the `toString()` method fails with an exception.
     But because it can't fail with checked exceptions, you have to transform them into unchecked exceptions.
2. Clear the `Main.main` method. Add the following code:
   - Create a chess game with Mat (white pieces) and Pat (black pieces) players.
   - Create an infinite loop for `Game.play()`. If the method throws some exception (due to incorrect input), 
     then the error message is printed to the standard **error** output, and the game continues. 
3. Run the game :-) Use `CTRL+D` to end the game.

# Takeaways
* The Unicode values used for icons are as follows:
  ```
  WHITE-KING:\u2654
  WHITE-QUEEN:\u2655
  WHITE-BISHOP:\u2657
  WHITE-ROOK:\u2656
  WHITE-KNIGHT:\u2658
  WHITE-PAWN:\u2659
  BLACK-KING:\u265A
  BLACK-QUEEN:\u265B
  BLACK-BISHOP:\u265D
  BLACK-ROOK:\u265C
  BLACK-KNIGHT:\u265E
  BLACK-PAWN:\u265F
  WHITE-DRAUGHTS_MAN:\u26C0
  WHITE-DRAUGHTS_KING:\u26C1
  BLACK-DRAUGHTS_MAN:\u26C2
  BLACK-DRAUGHTS_KING:\u26C3
  ```

## Features to be manually checked by tutors 
* The "pieces.txt" is defined as a constant.
* The icons are read only once and only when first needed.
* Dealing with exceptions in the `Piece.toString()`, conversion to unchecked exceptions.
* Dealing with exceptions in the `Main` class.
