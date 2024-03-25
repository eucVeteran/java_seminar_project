# PB162 seminar project

## Objectives
* Objects' identity (equality)
* Generics
* Nested classes

## Tasks
Unless otherwise instructed, create new classes/enums/records in the `cz.muni.fi.pb162.project` package.

1. Redefine equality of the following classes. You have to know the purpose and implementation principles. 
   Therefore, don't use automatically generated code. Write the code manually.
    - Two pieces are the same if they are of the same type and color (regardless of the `id`). 
      Use `getClass()` for the implementation (to practice this approach).
      > Review the relationship between the object's identity and hashing. Consider using `instanceof` vs. `getClass()`.
    - Two boards are the same if they are the same size and the same pieces (type and color) are at the same positions.
      > Study and use the `Arrays.equals` and `Arrays.deepEquals` methods.
    - Two games are the same if the first and second players are the same.
      Game state and board state do not matter.
      Use JDK17+ `instanceof` syntax with a variable definition for the implementation (to practice this approach).
2. The `Prototype` interface uses the `Piece` type. However, the interface is generic. It just aims to provide
   a cloning method regardless of the returned type. Therefore, use _generics_ to refactor the interface so that
   any type of object can be returned using the `makeClone` method. Update the `Piece` class correspondingly.
   > Consider how easy or difficult it could be now to also make the `Board `cloneable, then the `Game`, etc.  

So far, games like chess or draughts have been instantiated by a constructor that created an initial layout of the board.
Since now, we'd like to enable a step-by-step creation of a game with the layout of pieces that can differ from 
the standard initial layout. 

Step-by-step creation of objects can be achieved by using the [Builder](https://en.wikipedia.org/wiki/Builder_pattern) 
design pattern decomposition. Our product is a game (chess or draughts) with players and pieces on certain board positions.
Director is the `Main` class that instructs builders to create desired games.
Our goal is to implement builders for chess and draughts games.

3. Create an abstract class `GameBuilder` with the following methods. Use generics so that instances of `Chess` and `Draughts` 
can be finally obtained (or any other game introduced later).
    - The `addPlayer(Player player)` method adds the first and then the second player to the game. The method returns `GameBuilder`
      so that method calls can be chained. Add the following code to the `Main` class that demonstrates the usage:
      ```java
      var game = new Chess.Builder()
              .addPlayer(new Player("Mat", Color.WHITE))
              .addPlayer(new Player("Pat", Color.BLACK))
              .addPieceToBoard(new Position('e', 1), new Piece(Color.WHITE, PieceType.KING))
              .build();
      System.out.println(game.getBoard());
      ```
    - The `addPieceToBoard(Position pos, Piece piece)` method adds the piece to the desired position of the board.
      Again, method chaining has to be supported.
    - The `build()` method returns either `Chess` or `Draughts` (or any other game). 
      At this level of abstraction, we don't know what type of game to return. Therefore, the method can not be implemented.
      We could use `Game` as the return type. However, more practical will be to use a generic type value. 
      Therefore, use generics for the return type definition. 
      > Discuss advantages and disadvantages of both approaches. Consider the manual retyping when the polymorphism 
        of the `Game build()` variant is used. On the contrary, be aware that `GameBuilder<Chess>` and 
        `GameBuilder<Draughts>` are independent types without any polymorphic relationship (their parent class 
        is the `Object`).
4. Implement specific builders for chess and draughts. The builders have to be created as **nested static classes**
   utilizing the previous abstract class.
    - Introduce constructors to the class hierarchy of games that take a board instance as an input parameter 
      (current constructors instantiate a new board and set it into the initial game layout). 
      Avoid unnecessary code duplicities.
    - Ensure that the new constructors (with predefined board) are available only to game builders and not to external code!
    > Discuss the advantages of defining specific builders as nested classes instead of standard external classes.
    > Discuss the possibility and (dis)advantages of using static vs non-static internal classes.

## Takeaways
* Change also the `hashCode` method whenever the `equals` is changed!
* For subclasses, there is no ideal implementation of `equals`. 
  If you use the `getClass`, then objects are only equal to other objects of the same class, the same runtime type. 
  It violates (a strict interpretation of) [Liskov substitution principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle).
  On the other hand, the problem with the `instanceof` is that it breaks the "symmetry" contract of 
  `Object.equals()` in that it becomes possible that `x.equals(y) == true` but `y.equals(x) == false`
  `if x.getClass() != y.getClass()`.  
* Generics are template classes or interfaces that internally deal with objects of a specified type. The type is specified either 
  by the class implementing/extending generic interface/class or at runtime when the generics-based object is instantiated 
  or a generics-based method is called.
* Nested classes can improve access control and then preserve encapsulation (new constructors of the `Chess` and `Draughts`
  can be protected yet still accessible for their builders) and organize code (builder-specific code is encapsulated in 
  the special `GameBuilder` class and its subclasses).

## Features to be manually checked by tutors 
* In the equals/hashCode of the board, the size should not be checked if it's already checked by comparing arrays.
  Also, no primes or random numbers should appear in the `hashCode`.
* The hash code of `Piece` should use `getClass` while `Game` should use `instanceof` (to practice both approaches).
* In the equals/hashCode of the game, you must use players only.
* The usage of generics in the `Prototype` and `Piece`.
* Protected/private constructors in the `Game`, `Chess`, and `Draughts`. 
* Potentially duplicate code in the constructors fo the `Game`. 
