# Java Air Quality Simulation

A Java air quality simulation project built with object-oriented design. The project models different elements on a map, such as cars, buses, bikes, airplanes, and woodland, and simulates how they affect pollution levels over time.

The project was developed as part of an object-oriented programming course. It focuses on inheritance, polymorphism, GUI interaction, and simulation logic.

## Features

- Interactive map-based simulation
- Add different element types to the map
- Moving elements such as cars, buses, bikes, and airplanes
- Non-moving woodland elements
- Pollution generation from vehicles
- Pollution reduction from woodland
- Pollution diffusion over the grid
- Removes moving elements when they leave the grid
- Validates land-based placement for relevant elements

## Tech Stack

- Java
- Object-oriented programming
- Swing-based course GUI
- Course-provided support library

## Key Concepts

- Inheritance
- Abstract classes
- Polymorphism
- Method overriding
- Encapsulation
- GUI event handling
- Grid-based simulation
- Basic diffusion logic

## Project Structure

```text
src/
  Main.java
  elements/
    AirPlane.java
    Bike.java
    Buss.java
    Car.java
    Elements.java
    ElementType.java
    WoodLand.java
  view/
    Definition.java
```
## How It Works
The simulation uses a course-provided GUI and map. The user selects an element type and places it on the grid. Each element has its own behavior:

Cars and buses move randomly and increase pollution
Bikes move randomly without creating pollution
Airplanes move in a fixed direction and create pollution
Woodland stays in place and reduces pollution
After each time step, the simulation moves active elements, updates pollution values, diffuses pollution across nearby grid cells, and repaints the GUI.

Dependencies
This project depends on a course-provided library from Malmö University. The .jar file is not included in this repository because it is an external course dependency.

To run the project locally, the required course library must be added to the classpath.

Status
Educational Java project focused on object-oriented design, GUI interaction, and simulation logic.
