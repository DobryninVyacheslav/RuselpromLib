<h1 align="center">RuselpromLib</h1>
 
# Description
<div style="text-align: justify ">RuselpromLib is a simple, higher-level set of functions based on the Java API for Creo Parametric. The library is designed to easily interact with Creo Parametric using Java and consists of classes that implement the basic logic needed to develop auxiliary applications for Creo Parametric.
At this stage, this library does not use such generally accepted principles as SOLID, so it is simple and not very extensible.</div>

# Class documentation
## package [ru.ruselprom.lib.assembly](src/main/java/ru/ruselprom/lib/assembly)
### Class [ComponentOfAsm](src/main/java/ru/ruselprom/lib/assembly/ComponentOfAsm.java)
| Method        | Description                                          | 
| ------------- | -------------------------------------------------- | 
|void addToAsmByCsys (Model currCompModel, [RefCoordSystems](src/main/java/ru/ruselprom/lib/assembly/argument/RefCoordSystems.java) refCoordSystems)|adds the model specified by the first parameter to the assembly specified in the class constructor. The addition occurs by aligning the coordinate systems specified in the second parameter of this method.|
## package [ru.ruselprom.lib.base](src/main/java/ru/ruselprom/lib/base)
### Class [Macro](src/main/java/ru/ruselprom/lib/base/Macro.java) 
| Method        | Description                                          | 
| ------------- | -------------------------------------------------- | 
|void execute(String macroCode)|Executes the macro passed to the parameter|
### Enum [Direction](src/main/java/ru/ruselprom/lib/base/Direction.java)
| Constant      | Description                                          | 
| ------------- | -------------------------------------------------- | 
|CLOCKWISE|Clockwise direction|
|COUNTERCLOCKWISE|Counterclockwise direction|

| Method        | Description                                          | 
| ------------- | -------------------------------------------------- | 
|int getValue()|getting a numerical value for the direction of rotation|
|static int getValue(Direction direction)|getting a numerical value for the direction of rotation by direction constant|
### Class [Regeneration](src/main/java/ru/ruselprom/lib/base/Regeneration.java)
| Method        | Description                                          | 
| ------------- | -------------------------------------------------- | 
|static void regenerateSolid (Solid currSolid)|Regenerates the solid model passed to the method parameter|
