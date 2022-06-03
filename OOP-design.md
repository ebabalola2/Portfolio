# OOP Design Document

![](/Users/emmanuelbabalola/project-2-ebabalola2-1/UML-3.png)

## Overview
This cat and mouse simulator is multiple complex OOP that has a couple of interlooped parts. It uses classes like, simulator, city, creatures, cat, mouse and zombie cat which are extentions of creatures through polymorphism. It also uses Gridpoint which extends Object. The class City is encapsulated in Simulator. There are other encapsulation and inheritance.



[For each of the classes in your OOP design, including ones provided
for you, offer a brief description of their functionality and how they
interact. Be sure to highlight good OOP like encapsulation,
inheritance, and polymorphism. Once you do, remove this text! Also be
sure the headers below match the class names, and not Class 1 and
Class 2 and etc.]

## Simulator
This class starts the cat and mouse simulation. It counts and debug after every round to see the posotions and amount of cat and mice dots that is on the plotter. Then determines where new ones are placed using the modulo operator.

## City 
The city is generated with a width and height of 80. It functions much like a torus, where if a point manages to go off the edge of a grid, it is wrapped around to the other side. There's a list of creatures that is implemented in a HashMap in conjunction with its current position, and there is also a remove and add Queue. It is possible to find out how many creatures are in the grid world currently, as well as clearing the queue. The updateGrid function serves to update the position of every creature every round, so that targets can be found for creatures that need them using the findTarget method, or to be notified if a creature is eaten.

## Creature
This class navigates the movements of the creatures. Each creature has their own specific directions. Positions are done by random generator and each creature has a color code unique to each type.
## GridPoint
This class is used in the hashmap, It relates to the positons of the dots which can be hased to be stroed in the Hashmap, and it can also guage the distance of a creature.
## mouse
The class has it own unique color code, it is stored in GridPoint. It changes direction using the maybeTurn function. It will die/disapper if it caught by a cat or by a round counter.
## cat
It has the same functionality as the mouse class, except it can jump two spaces and change direction quickier. It also has the chase method to chase the mice on the grid. If a cat is eaten, its turned into a ZMCat, and its eaten variable will change to true, in which the methods in the class will adjust accordingly. 

## ZombieCat
This class has the same function as the cat except it is faster and eats both cat and mouse. Also if a cat gets eaten it turns into a ZombieCat.

## Dog
This class has the same functionality as the ZombieCat, expect it's prey is the Cat and the Zombie Cat. It is more agile than both creatures. If the dog doesn't eat for 100 rounds it dies. When the Dog is not chasing another creature it is displayed as Green, when it is chasing another creature it is displayed as Pink.
