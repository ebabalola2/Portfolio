# Portfolio
Each branch in the github is a diffrent project, There is a total of two project in the portfolio This project was done in my software Engineering class. 
# Project 1 (Boggle Game)
I coded each method in both hashmap.c and linked list.c to make sure spellcheck work, matching whatever word produced to a word in the dictionary. I then load the dictionary into the hashmap and tranverse it. Using recursion method I have the boggle have limits using diffrent base cases, comparing the current words to the last word made, while seeing what is visited.
# Project 2(Cat and mouse Simulator)
This cat and mouse simulator is multiple complex OOP that has a couple of interlooped parts. It uses classes like, simulator, city, creatures, cat, mouse and zombie cat which are extentions of creatures through polymorphism. It also uses Gridpoint which extends Object. The class City is encapsulated in Simulator. There are other encapsulation and inheritance.

Simulator

This class starts the cat and mouse simulation. It counts and debug after every round to see the posotions and amount of cat and mice dots that is on the plotter. Then determines where new ones are placed using the modulo operator.

City

The city is generated with a width and height of 80. It functions much like a torus, where if a point manages to go off the edge of a grid, it is wrapped around to the other side. There's a list of creatures that is implemented in a HashMap in conjunction with its current position, and there is also a remove and add Queue. It is possible to find out how many creatures are in the grid world currently, as well as clearing the queue. The updateGrid function serves to update the position of every creature every round, so that targets can be found for creatures that need them using the findTarget method, or to be notified if a creature is eaten.

Creature

This class navigates the movements of the creatures. Each creature has their own specific directions. Positions are done by random generator and each creature has a color code unique to each type.

GridPoint

This class is used in the hashmap, It relates to the positons of the dots which can be hased to be stroed in the Hashmap, and it can also guage the distance of a creature.

