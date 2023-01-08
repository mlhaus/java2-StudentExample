# Java2

This is the project for my CIS-175 Java II course at Kirkwood Community College. Below is a journal of all the things I learned throughout the class.

## Introduction

The first week of Java2 we reviewed.
Brushing up on classes, constructors, methods,
and basic programing. One thing that I learned was
how to use the tools and features provided by the
IntelliJ development software. Learning how to
make projects, packages, and classes. Another thing that we
reviewed was Lists and ArrayList going over how to initialize
them and the static methods that are in their classes. Arraylists
have methods like add, set and remove to adjust items in the array list.
There are also get which gets the value of an item, and size which returns
the number of items in the array list.

## Test Driven Development

This week we learned about test driven development. Which includes making
test classes for java classes, and how to make tests for each method in a class.
Getter methods are test by using assertEquals function, testing the predicted output to the actual.
Setter methods use assertEquals in the same way but will need tests for all valid ways a variable can be set, 
and invalid ways by using an assertThrows. Objects for getter and setter methods that are implemented using interfaces
can be tested by using the assertTrue function.

## Collections

This week we learned about the Collections interface. Collection has
several Container classes such as List, Set, and Map. List can make collections 
such as ArrayList and LinkedList, an ArrayList is an unordered list of objects while
a Linked list (is an ordered list of objects. These act as arrays and have their own
methods such as add() to add an element to the list, and get() which returns the object
at a specific index in the list, however in order to be used as an array object
the list needs to use the toArray() method in order to create an array out
of the objects in the list. Map is special Collection which consists of key-value pairs, 
with a specific key holding, or representing a value. The put(key, value) method adds a pair to the collection
and the remove(key) method removes the pair. Map collections also have search methods like
containsKey(key) which returns a boolean based on if the key is in the Map.

## Contributing to Open Source Projects
The week we learned about the way open source projects work. Open source projects
are projects that various people can contribute work towards. The Maintainer is the person who
typically starts the project and allows for updates and contributions to be added to it. While 
a Contributor is the person that added and creates work to contribute towards the project for the 
Maintainer to approve. We have used origin as our remote alias though out the semester to communicate from our local
machine to a forked or stored copy we have on Github. On an open source project since the contributor does not have direct
access to the original project on their origin they need to use the alias upstream. Another thing we learned about when coding
an open source project was creating branches. Branches are parallel versions of a repository and can be helpful when contributing
because it allows a person to create duplication of their code implement a feature of code and test it, without changing and updating
the original repository it was duplicated from.

## Generics
This week we learned about the generic type. A generic is a data type that can be given to an object, class, interface or methods and 
is set with <> and the name of the parameter inside. The generic type is references whatever data type is passed to it making code for using 
generics more flexible, cleaner, and reliable as opposed to setting a normal data type. The Collections api uses generic types in a lot of 
its methods, it does this because Collections has a lot of extension types it wants to be able to implement its methods with. This way if a class
that extends Collections calls a collections method it doesn't need to care what data type was set in the collection that is calling it, because
the datatype will be copied by the generic. A ? is used to signify a wildcard selector, a wildcard selector is an unknown type and can be used
in conjunction with the extends keyword and a generic in order to only allow type that extend off of that generic type. Which means that if there was
a class Apple that extended the class Fruit then <? extends T> with T being a Fruit object would allow the class Apple along with any class that extends
the Fruit class.

## Lambda Expressions
This week we learned about lambda expressions. Lambda expressions are a block of code that can be executed like a method,
they are implemented using functional interfaces and at least one abstract method is needed. The functional interface can be
one made for the code or there are functional interfaces provided by Java.
- Supplier<T>, takes no arguments and returns a value of type T
- Consumer<T>, takes input of type T and returns void
- Predicate<T>, takes input of type T and returns a boolean
- Function<T, R> takes input of type T and returns object of type R
- UnaryOperator<T> takes input of type T and returns object of type T

We also learned about method references which look like Object::objectName,
which allow for an easier and shorter way to instantiate a new object in a lambda
expression.

## General Ethical Promise

### 1.1 Contribute to society and to human well-being
I promise to use my skills for the benefit of society and its members.
In order to prevent the negative consequences of computing, that my threaten health, security, and privacy.
I also promise to promote environmental sustainability both locally and globally.

### 1.2 Avoid Harm
I promise to never purposefully cause any unjust harm. I promise to take careful consideration with
my actions in order to prevent any harm. If I do cause harm I promise to take responsibility to undo
or mitigate the harm as much as possible.

### 1.3 Be honest and trustworthy
I promise to be honest and trustworthy. Too not deliberately spread false claims, fabricate data, or accept bribes.
To be honest about my own qualifications and any limitations I may have in completing a task.

### 1.4 Be fair and take action not to discriminate
I promise to uphold the values of equality, respect, and justice. I promise to foster fair participation
among all types of people. I also promise not to harass or abuse any power of
authority I might have.

### 1.5 Respect the work required to produce new ideas, inventions, creative works, and computing artifacts
I promise respect copyrights, patents, trade secrets, license agreements, and all other forms of
protecting authors work. I promise to credit the other creators of ideas, inventions, work, and artifacts.
I promise not to claim ownership of work others have shared and public resources.

### 1.6 Respect privacy
I promise to uphold the responsibility of respecting privacy. I promise to only use others personal information
for legitimate ends, without violating the rights of these individuals or groups. I promise to provide transparency
so individuals understand what data is being collected and how it is used.

### 1.7 Honor confidentiality
I promise to uphold confidentiality. To be entrusted with trade secrets, client data, nonpublic business strategies,
financial information, research data, pre-publication scholarly articles, and patent applications. Except in cases when
it is evidence to the violation of the law, regulations, or of the Code

