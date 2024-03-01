# PB162 seminar project

## Objectives
* Immutable classes/objects.
* Records.
* Static attributes and constants.
* The implementation of methods that support "chaining" calls.

## Tasks
Unless otherwise instructed, create new classes/enums in the `cz.muni.fi.pb162.project` package.

1. Create an _enumeration type_ (enum) called `PieceType`. 
   Define [all chess pieces](https://en.wikipedia.org/wiki/Chess_piece). Use names in the singular form.
   > Review the naming conventions for constants.
2. Define a `Color` that can be either white or black (nothing else).
   Add the `getOppositeColor()` method, which returns the opposite color: white => black & black => white.
   > Discuss and choose the proper element. Should it be class or enum? Why?
3. Modify the `Piece` class:
   - A piece knows its _color_ and _type_. Name the corresponding attributes `color` and `pieceType`.
   - Update the constructor to instantiate a piece with user-defined color and piece type (in this order).
   - Add all getters and setters that do not violate the immutability of the `Piece` class.
     > Review the immutability feature. 
     > Discuss the role of the _final_ keyword. Is the usage of `final` necessary for immutability?
4. Modify the `Player` class:
   - A player knows the color (either white or black) of his/her pieces.
     The color is given as a second argument of the constructor.
   - Override `toString` method to return a text in the following format: "<name>-<color>".
   - Enforce the immutability of players by transforming the class into _record_. Think about differences.
     Remove all unnecessary methods.
     > Review restrictions and features of Java records (immutability, inheritance, constructors, and methods).
     > Discuss why these class can be converted into a record.
5. Modify the `Position` class:
    - Convert the class into record. Remove all unnecessary methods.
    - Add the `add` method, which takes another position as an input parameter and calculates a new position
      by summing the two. You can calculate the sum by summing the individual components
      (just as complex numbers). The implementation does not change the _state_ of the original object 
      and enables _method chaining_, e.g.,
        ```
              Position c = (new Position(1, 2))
                                     .add(new Position(3, 5))
                                     .add(new Position(7, 0));
       ```
6. Modify the `Board` and `Position` classes:
   - Add an overloaded constructor to the `Board` that creates a board of size 8. 
     Defined the default size as a _public constant_.
   - Define 97 (the UNICODE value of 'a') in the `Position` as a _private constant_. Use it in the code.
     > Discuss modifiers (keywords) used for public and private constants.
7. Modify the `Piece` class:
   - Every piece has a unique ID. Use `java.util.concurrent.atomic.AtomicLong` to arrange tha
     a new instance of the piece has always been assigned a unique ID.
     > Why do we need two attributes?
     > Consider whether the atomic counter should be final, static, public/private. Why?
     > The same goes for the 'id' attribute with a unique value.
8. Modify the `Board` class:
   - Add the `findCoordinatesOfPieceById` method, which takes the `id` of type long as an input parameter and 
     returns the piece with the same id from the board. If there is no piece of this ID on the board,
     then the method returns `null`.
9. Edit the executable `Main` class:
   - Delete the current code in the main method.
   - Create two players, Pat and Mat, with opposite colors.
   - Create a board with default size.
10. Document your code by Javadoc.
   > Consider which method may remain undocumented. 
   > Document records correctly.

## Takeaways
* Immutability is the property of your implementation. Using `final` modifiers or records instead of classes
  helps enforce immutability and avoid its unintentional violation cased by later modifications.
* Private immutable attributes (e.g., of the `String` type) can be returned outside the object without 
  the danger that somebody changes its state (and then violates encapsulation of the objects). Therefore,
  define immutable classes whenever possible. Changed objects can be created by instantiating a copy with 
  a modified state.
* Note: It is not usual to introduce IDs into classes because their addresses distinguish individual objects. 
  However, sometimes it can be useful, especially if we redefine the "equality" of objects, 
  as we will learn later.

## Features to be manually checked by tutors 
* Javadoc.
* `final`, `static`, and `public/private` modifiers.
* The code in the `Main` class.

