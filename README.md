# PB162 seminar project

## Objectives
* State of objects.
* Simple inheritance and polymorphism. Overridden methods.
* Alternate ways to object's construction (prototyping).
* Implementation of existing interface and interface definition.

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

1. Create the `Game` class representing a gameplay of two players.
    - Each game is played by two players (name the corresponding attributes `playerOne` and `playerTwo`) on 
      the board of size 8x8. Neither the player nor the board is changed during the game. The players and the board are 
      set in a constructor with players provided as input parameters.
      We assume that one player (not necessarily the first one) is "white", while another is "black". But the correctness
      of their state is not checked right now (we will do it later).
    - Every game has a state which gains one of the following values: "white player win", "black player win", "pat", 
      and "playing". The initial game state is "playing". Define these states as predefined `StateOfGame` values. 
      Arrange that if the value is used in the context of text strings, then lowercase letters are printed.
    - A game has the ability to count rounds, starting with zero, i.e., the first round is 0, the second 1, etc.
      For now, add only the appropriate attribute. Later on, we will introduce a method that will increment rounds 
      during the gameplay.
    - Add the `getCurrentPlayer` method, which returns the player whose turn it is
        - The white player always starts, i.e., even rounds are of the white player, while odd rounds belong to the 
          black player.
        - Players take turns regularly.
          > Use the "modulo" operator to transform rounds into even (0) or odd (1) numbers.
    - Mark attributes that never change as **final**. Add getters.
      Add setters for attributes that can be changed during the gameplay by external code.
      In general, prefer encapsulation over openness.
2. Update the `Piece` class:
   - Override the `toString` method to return the first letter of `pieceType`.
     > How is it possible to talk about "overriding" when the `Piece` class does not extend any other class?
   - Implement the `Prototype` interface so that our class will follow the so-called 
     [Prototype design pattern](https://refactoring.guru/design-patterns/prototype) - an alternate way of creating 
     an object by cloning an existing object (with possibly changed state).
     - Introduce an overloaded _copy constructor_ into the class. The new piece will have the same attributes except 
       for the `id` that will be set unique. 
     - Implement the required method using the copy constructor.
       - Think about the difference between cloning a piece by calling the copy constructor and cloning the piece
         via `makeClone()` method.
       > HINT: Consider polymorphism. Assume that an instance of `SpecificPiece extends Piece` is retyped to `Piece`.
       > Calling the overridden `makeClone()` on this object produces an object of type `SpecificPiece`.
       > On the contrary, if you want to use the copy constructor, then ou have to know whether to call 
       > the copy constructor of `Piece` or `SpecificPiece`.
     - Discuss whether it is necessary to override the `makeClone()` method in subclasses.
       > HINT: Yes. The reason is suggested in the previous hint :-)
     - Discuss what is the better decomposition for distinguishing types of pieces.
       - Option (a): A single class is defined with the `pieceType` attribute (i.e., our approach). 
       - Option (b): The `Piece` class has no `pieceType` attribute. Subclasses like `King extends Piece`
         are introduced instead.
       > HINT: What would you prefer if there are no differences in the state (attributes) among piece types?
       > What if some piece type needs a specific attribute? What if a piece type has a specific behavior, e.g., 
       > specific moves? In this case, the approach (a) with a single class may lead to if-else branches with code 
       > specific to the piece type, while (b) supports better maintainability because the specific behavior can be 
       > located in the overridden method and then implemented without modifying other classes.
3. Introduce `Chess` and `Draughts` types of `Game`.
    - Create these classes and use inheritance between `Chess`, `Draughts`, and `Game`. Choose carefully the class hierarchy.
      Use private attributes, avoid attribute overriding!
      > Review rules for inheritance/polymorphism and rules for constructors of subclasses.
    - Add the `setInitialSet()` method, which sets the initial layout of the pieces on the chess board,
      either for [chess game](https://en.wikipedia.org/wiki/Rules_of_chess#Initial_setup) or
      for [draughts played on chess board](https://en.wikipedia.org/wiki/English_draughts).
      This method is implemented in the corresponding subclasses and called automatically from constructors (nowhere else).
      Use the sample code below - modify it if necessary.
      > Discuss why this method should be private or final.
4. Create a new `Playable` **interface** introducing a single well-documented method.
    - The `void move(Position oldPosition, Position newPosition)` method moves the piece from the old position to the 
      new position on the board. If there is no piece at the source position or the positions are wrong, then the 
      method does nothing (this is not the best reaction to failure, but we will repair it later).
    - Make the `Game` implementing this interface.

## Chess layout
```java
    private void setInitialSet() {
        getBoard().putPieceOnBoard(new Position('e', 1), new Piece(Color.WHITE, PieceType.KING));
        getBoard().putPieceOnBoard(new Position('d', 1), new Piece(Color.WHITE, PieceType.QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 1), new Piece(Color.WHITE, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('h', 1), new Piece(Color.WHITE, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('b', 1), new Piece(Color.WHITE, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 1), new Piece(Color.WHITE, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 1), new Piece(Color.WHITE, PieceType.BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 1), new Piece(Color.WHITE, PieceType.BISHOP));

        getBoard().putPieceOnBoard(new Position('e', 8), new Piece(Color.BLACK, PieceType.KING));
        getBoard().putPieceOnBoard(new Position('d', 8), new Piece(Color.BLACK, PieceType.QUEEN));
        getBoard().putPieceOnBoard(new Position('a', 8), new Piece(Color.BLACK, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('h', 8), new Piece(Color.BLACK, PieceType.ROOK));
        getBoard().putPieceOnBoard(new Position('b', 8), new Piece(Color.BLACK, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('g', 8), new Piece(Color.BLACK, PieceType.KNIGHT));
        getBoard().putPieceOnBoard(new Position('c', 8), new Piece(Color.BLACK, PieceType.BISHOP));
        getBoard().putPieceOnBoard(new Position('f', 8), new Piece(Color.BLACK, PieceType.BISHOP));

        for (int i = 0; i < getBoard().getSize(); i++) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(Color.WHITE, PieceType.PAWN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(Color.BLACK, PieceType.PAWN));
        }
    }
```

## Draughts layout
```java
    private void setInitialSet() {
        for (int i = 0; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 0), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 2), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 6), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
        for (int i = 1; i < getBoard().getSize(); i += 2) {
            getBoard().putPieceOnBoard(new Position(i, 1), new Piece(Color.WHITE, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 5), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
            getBoard().putPieceOnBoard(new Position(i, 7), new Piece(Color.BLACK, PieceType.DRAUGHTS_MAN));
        }
    }
```

## Takeaways
* If a class cannot be immutable, use at least `final` attributes whenever possible to 
  prevent their unintentional modification. But be aware that the state of even a non-primitive final
  attribute can be modified if the object referenced by the attribute is not immutable!
  Therefore, the protection is only partial.
* Never override (repeat) attributes from super-classes in subclasses. Use public, protected, and private 
  methods to access or modify them.
* A constructor of a subclass must always call a constructor of its super-class.
* There are alternate ways of constructing an object than just calling their constructors, e.g., cloning. 
  See the [creational design patterns](https://en.wikipedia.org/wiki/Creational_pattern) for more 
  construction strategies.
* Interfaces are either implemented (by one or more classes) or used (we will practice next week).
* Interfaces must be precisely documented.

## Features to be manually checked by tutors 
* Javadoc. Especially in the `Playable` interface and various constructors.
* Call of `this()` in the copy constructor of `Piece`.
* The use of the `final` keyword set setters in the `Game`.
* The use of `@Override` in the `Game.move` method.
