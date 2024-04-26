# PB162 seminar project

## Objectives
* Streams
* Exceptions

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

1. In the following methods, comment out the code (leave it there) and re-implement it using streams.
   Discuss the approach (desired data pipeline principles) in the classroom before implementing it.
   - `Tournament.findGamesOfPlayer`
   - `Board.getAllPiecesFromBoard`
   - `Chess.updateStatus`
   - `Piece.getAllPossibleMoves`
   > Use `Arrays.stream(...) to create a stream from an array`
2. When creating a `Piece` instance, the color and piece type are mandatory. Therefore, refactor the constructor
   so that a piece without color or type cannot be created. Throw an appropriate unchecked exception.
   > Consider using utility methods from the `Objects` class to simplify the code.
3. Similarly, for the `Piece` copy constructor, the input parameter is mandatory. Therefore, the constructor
   has to throw the null pointer exception if the input argument is missing.
   > Is any change of the code necessary? Compare the situation with the previous constructor.
4. Create the following exceptions in the `exceptions` package that are specific to our application. 
   We intentionally mix checked and unchecked exceptions to practice how to handle them.
   - `EmptySquareException` and `NotAllowedMoveException` are **checked** exceptions.
   - `InvalidFormatOfInputException` and `MissingPlayerException` are **unchecked** exceptions.
   - Create at least **two constructors** in each class that call the constructor from the superclass.
5. Refactor the `Chess.Builder.build()` and ``Draughts.Builder.build()`` methods so that they throw 
   the `MissingPlayerException` exception if either the first or second player is not set
   (they have not been specified by calling the `addPlayer` method twice).
6. Refactor the `Game.play` method so that it throws exceptions. Update the signature of the `Playable.play()` accordingly. 
   Do not forget to write _meaningful messages_ to exceptions. Do not forget to update Javadocs. 
   - `EmptySquareException` if the user wants to move a piece from the empty position or the position is not on the board.
   - `NotAllowedMoveException` if the user wants to make an illegal move.
   - `InvalidFormatOfInputException` if the user's input is in the wrong format. The format must be `<char><int> <char><int>`.

> Note that you should program defensively from the very beginning. There are many other methods in our code in 
> which we should check input parameters and respond to their possible errors. Do it for your code voluntarily.
> Don't forget to update Javadoc adequately.
   
## Takeaways
* Streams require you to change your mindset from iterative data inspection to data pipelines.
* Program defensively. Use special return values to indicate errors in input parameters. 
  Use exceptions if special values are not possible (i.e., for constructors).
* Use exceptions carefully. Throwing an exception triggers the crash of the application
  (and then makes users angry) unless some code "above" stops it.

## Features to be manually checked by tutors 
* Using streams in the task 1.
* The copy constructor of the `Piece` should throw an exception implicitly (no special code throwing the exception 
  should be present).
* Throwing exceptions in the `play` and `getInputFromPlayer` methods (task 6).
