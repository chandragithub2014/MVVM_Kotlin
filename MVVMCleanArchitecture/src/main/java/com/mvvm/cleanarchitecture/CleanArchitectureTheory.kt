package com.mvvm.cleanarchitecture

/*
What is Clean Architecure?
Is a way to organize project to achieve maintainability and Scalability

One concern per component

Structured in layers of dependency which means different layers in Architecture depend on other layers.

This architecture is not specific to mobile development ,its s/w development architecture

Advantages:
Strict Architecture - Hard to make mistakes as all components are layered.
Encapsulated Business logic - Business logic is separated from Presentation logic.
Allows for parallel development - All components can be worked on in parallel by multiple developers.
Highly scalable  - Project can be grown easily.
Easy to understand and maintain.
Testing is facilitated.

Clean Architecture Components:
4 basic Layers:
(1) Entities -> Contains Business rules
(2) UseCases -> Depends on Entities
(3)Controllers/Presenters/Adapters
(4)Infrastructure -> Devices

Entities:
Domain Objects
POJO objects
Foundational business logic

Uses Cases:
Basically are the actions that can be taken on entities.
Depend on Entities.
What can we do with the data provided by entities.
The business logic that can be applied to entities

Controllers/Presenters/Adapters:
Basically these are interfaces.
Present data in a specific format like XML,JSON etc..
Takes data from Usecase and present it to upper level layers to use this data.
These components depend on Architecture like Android .It contains something related to framework.

Infrastructure:
How data is going to be interpreted and  presented.
This layer is volatile and depend on interface, when interface layer changes this layer changes.
Dependent on Framework to display the data like we use Activity ,Fagments etc to display data.


**********************SOLID PRINCIPLES**********************

Single Responsibility - A class should have only one job. One reason to change. If there are 2 reasons class should be split into two.

Open - Closed  - A class should be open for extension and close for Modification.

Liskov substitution - Low level classes can be substituted without effecting higher level classes.Achieved using Abstract classes and interfaces .

Interface segregation  -  Many Specific interfaces are better than generic interfaces.

Dependency Inversion   -  A class should get dependencies from External source with out class instantiating on its own.


 PROJECT STRUCTURE:

 Layer 1:
 data ->

 Layer 2 : usecase & Repository
 Layer 3:  Framework and MVVM
 Layer 4:  Presentation and View.


 The Project can be divided into two App and Core.
 App contains all Android related
 Core contains all business logic that is non-Android related.

 Core:                         App:
 data                          ViewModel, RoomDataBase,Dependency Injection  (Framework),  Views(Activity,Fragment etc.) (Presentation)
 Repository
 Usecases



************************MVVM**********************
Model : Contains Data and Business logic
View  : Data to be displayed to the user using Activity,Fragments,Adapter
ViewModel : Acts as interface b/w View and Model

 */

/*
Navigation :
Handles App Navigation
Fragment Transactions
Args passing
Deep Linking

Navigation Components:
(1) Navigation Graph: Displays all the Fragments that are in the app and allows to make connections from one screens to other
 (2) NavHost: This is layout component that hosts Fragments . One Activity Multiple Fragments
 (3) NavController : Class that allows to perform transitions from one screen to another.
 */

//https://stackoverflow.com/questions/54572613/with-android-studio-3-x-x-adding-local-aar-file-to-gradle-build-is-not-working

/*Room
Room is Abstraction layer over SQLite
Reduces lot of boiler plate code and can easily store data to Database.
Provides compile time checks . Checks the DB queries @ compile time only

Room SetUP
@Entity  : Its a POJO class which represents the table in the DB
@DAO : Used to create DB . Contains methods related to Query DB  like Queries
@DataBase : Used to create the DataBase and it provides the @Dao interface which gives access to info stored in DB and Database also build Entity.
OPerations Performed by ROOM:
@Insert
@Update
@Delete
@Query
@RawQuery : Not checked @ compile time . This is not recommended . Mostly used in exceptional cases.

DataBaseMigration Functionality:
Old Version of database to be transformed to new version of the DB.

Unit Testing functionality: Allows mocking of data to unit test
DataBase access on BG Thread




 */



/*
 Approach For Clean Architecture:

 Data : It can be Pojo Object or Data Class

 UseCases: Represent each functionality in the app . There should be separate classes for Each Functionality
           Ex: Add a Record, Delete a Record, Fetch Record.
               For each functionality there should be separate class.



 */