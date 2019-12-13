# A Java Entity-Component-System game engine

This is a two-weeks Java student project. Its architecture results from many experiments. The following document explains its core principles, decisions, and how to use it. It is provided with a demo (graphics and music are the respective property of Nintendo and Braxton Burks).

## Features

* **Live programming**: modify your code while your game is running.
* **Constant complexity**: you can start contributing to any game just after reading this documentation.
* **Collaborative**: the engine is completely composable. The code of two mods cannot be contradictory.

## Table of contents

* [Principles](#principles)
    * [State/logic separation](#statelogic-separation)
    * [Composition over inheritance](#composition-over-inheritance)
* [Architecture](#architecture)
    * [Entity and Component](#entity-and-component)
    * [System](#system)
* [Usage](#usage)
    * [Creating a new Component](#creating-a-new-component)
    * [Creating a new Entity](#creating-a-new-entity)
    * [Creating a new System](#creating-a-new-system)
    * [Prototyping](#prototyping)
    * [Multiplayer](#multiplayer)

## Principles

Everything follows from the core principle: developer experience. Legible code, a fast prototyping loop, a minimal and expressive API.

### State/logic separation

Java favours grouping state (attributes) and logic (methods) inside a class. Completely separating them brings about significant advantages:
* The state of the world can be serialised, saved and loaded, and sent over the network for multiplayer games.
* Since logic only operates on the state, it can be changed at will while the game is running. This brings fast prototyping and no-restart updates for multiplayer games.

### Composition over inheritance

Similarly, Java favours inheritance. While the engine itself uses it, it offers a purely composable environment:
* Contributing to a particular class does not require knowing the whole lineage.
* Many components, such as "solid", "inflammable", "wet", etc. can be added to an entity, allowing for emergent gameplay (inspired by the [_Breath of the Wild_ prototype](https://www.youtube.com/watch?v=QyMsF31NdNc)).

## Architecture

The whole engine codebase is inside the `code` repertory. There are 4 classes:
* **Component**, the data building brick.
* **Entity**, build out of components.
* **System**, (actually `System_` since Java reserves `System`) applied to entities.
* **Frame**, code for I/O.

Finally, **Engine** contains the `main` method.

### Entity and Component

From the user point-of-view, a component is nothing more than a wrapper around a value. Then, entities are made of components. 3 syntaxes have been tested:

1. `Component`s would be an interface and `Entity`s would implement them.
```java
interface Inventory extends Component {

    public String getInventory();
    public void setInventory( String inventory );
}

public Mario implements Inventory {...}
```
Unfortunately, the logic gets cluttered with casts and `instanceof`s:
```java
if ( mario instanceof Inventory )
if ( mario instanceof Health )
if ( ( ( Health ) mario ).getHealth() == 0 )
if ( ( ( Inventory ) mario ).getInventory() == "mushroom" ) {
    ( ( Health ) mario ).setHealth( 1 );
    ( ( Inventory ) mario ).setInventory( null );
}
```
In addition, this duplicates many getters and setters. 

2. `Component`s would be empty classes and entities would store a map from components to their value.
We choose to map `Component`s instead of `Strings` to ensure name availability when developing a new `Component`, even when one does not know the whole list of `Components` (constant complexity principle).
`Entity` would have 3 methods:
* `bool has( Class component )`
* `Object get( Class component )`
* `void set( Class component, Object value )`
```java
Entity mario = new Entity();
...
if ( mario.has( Inventory.class ) )
if ( mario.has( Health.class ) )
if ( ( Integer ) mario.get( Health.class ) == 0 )
if ( ( String ) mario.getInventory() == "mushroom" ) {
    mario.set( Health.class, 1 );
    mario.set( Inventory.class, null );
}
```
Drawbacks:
* `get` requires casting
* `set` accepts any `Object`
* adding `.class` is mandatory in Java

3. The least intuitive but most effective: components store a map from entities to their component values.
`Component`s have 3 methods (with `T` defined within the `Component`):
* `bool in( Entity entity )`
* `T of( Entity )` as getter
* `of( Entity entity, T newValue )` as setter
```java
Entity mario = new Entity();
...
if ( Inventory.in( mario ) )
if ( Health.in( mario ) )
if ( Health.of( mario ) == 0 )
if ( Inventory.of( mario ) == "mushroom" ) {
    Health.of( mario, 1 );
    Inventory.of( mario, null );
}
```
Advantages:
* legibility
* typing

The default implementation is a `WeakHashMap`, meaning that components from removed entities can be garbage-collected. No memory leaks!

In addition, `Component`s can provide custom methods for high-performance operations. _For example, a `X` `Component` for position may have an `Entity[] getEntitiesBetween( double x1, double x2 )` for computing collisions._

Note that the last two syntaxes, although less idiomatic, allow adding components at runtime. One class per `Entity` instance is not necessary.

### System

Whereas `Entity`s and `Component`s handle the state, `System`s handle the logic, per the state/logic separation principle. After their instantiation, each `System` gets called one after another `Frame.FPS` times per second through `void apply( Entity[] entities )`. Since they sequentially mutate the state, they can never mutually contradict themselves.

⚠ State (instance variables) is forbidden in a `System`! The only exception is performance, such as buffering graphics for the `Render` `System`.

Usually, try to keep a `System` minimal and composable. For instance:
* breaking `Physics` up into `Friction`, `Gravity`, `Move`
* each `System` should try to perform only associate and commutative operations. Each component can be provided with one [reducer](https://en.wikipedia.org/wiki/Fold_(higher-order_function)). Here, `Friction` and `Gravity`  mutate the forces on an `Entity` and `Move`, the reducer, computes the new position or transmits forces to colliding `Entity`s.

## Usage

After downloading the engine, open it in [Eclipse](https://www.eclipse.org). The engine code in `/core` is around 50 lines (with the exception of `core.Frame`, boileplate handling the I/O), so you can read and master it in 10 minutes.

### Creating a new `Component`

Copy and paste within Eclipse an existing `Component` file with the type you want, rename. Eclipse will handle the rest.
If you need to change the type, simply open your component class and change the types; it takes less than 10 seconds.

### Creating a new `Entity`

Instantiate a new `Entity`, then define its `Component`s. This should be written in a `System`.
```java
Entity flower = new Entity();
X.of( flower, 1.0 );
Y.of( flower, 2.0 );
Z.of( flower, 0.0 );
```
You can remove an entity with `Entity.remove()`. If you create many entities with the same components, you may consider extending `Entity`:
```java
public class Flower extends Entity {

    public Flower( double x, double y, double z ) {

        X.of( this, x );
        Y.of( this, y );
        Z.of( this, z );
    }
}
...
new Flower( 1.0, 2.0, 0.0 );
```

### Creating a new `System`

Create a new class extending `core.System_`. The boilerplate code is automatically created. You just have to complete the `apply`  method. It usually follows this pattern:
1. For each entity
2. Given some conditions on components
3. Mutate the entities

For instance, suppose we have a `Health` `Component`. We want a minimal system damaging burning `Entity`s:
```java
import core.*;

public class BurningDamage extends System {

    public void apply( Entity[] entities ) {

        for ( Entity entity: entities )   // 1
        if  ( Burning.in( entity ) )      // 2: if the entity has a Burning component
        if  ( Burning.of( entity ) )      //    if Burning is set to true
        if  ( Health .in( entity ) )      //    if entity has a Health component
              Health .of( entity,         // 3: we set it to
              Health .of( entity ) - 1 ); //    its current value minus one
    }
}
```
In Engine.java, we add `new BurningDamage()` to the list of `System`s. Done!

This allows composition of systems and multiplicative gameplay. If we already have a system removing `Entity`s with their `Health` < 0, our burnt entities will automatically be removed. Note also how the above code cannot contradict any existing code.

### Prototyping

Run the engine by clicking the 🐞 (debug) icon. If you want to change some code at runtime, you can keep the game running, change the code and save it. The JVM will replace the `System` on the fly. This is only possible because `System`s contain no state.

### Multiplayer

One final example showing the extensibility of the ECS architecture. Imagine you developed a successful game. How to make it multiplayer? A usual Java architecture (inheritance, grouping state and logic by class) would make it hard to pivot. State would be computed to the server, but should be sent to the client... With the ECS architecture:
* Keep a single client/server codebase
* Remove all systems from the client except I/O
* In the client's `Component`, instead of reading and writing the local state, make it read and write the distant state
* In the server codebase, add a `System` to send the relevant parts of the state to the clients.

That is it.