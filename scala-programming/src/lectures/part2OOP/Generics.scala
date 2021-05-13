package lectures.part2OOP
/**
 * Created by Arunraja on May 13 2021
 * COLLECTIONS
 */
object Generics extends App {

  class TestList[A]
  class MyKVPair[K, V]
  val listOfInt = new TestList[Int]
  val listOfStr = new TestList[String]

  class MyList[+A] { //Bounded types can solve covariance problem - incase of List
    /*
    def add(element: A): MyList[A] = ???
    //Covariant type A occurs in contravariant position in type A of value element
    This answers the hard problem
    adding list dog to list cat
    */

    def add[B >: A](element: B): MyList[B] = ??? // Add B which is super type of A then it should return List of super type which is B
    //Say Animal is added to Dog then it should return type Animal

  }
  // Generic Method
  object TestList {
    def empty[A]: TestList[A] = ???
  }
  val emptyListofInt = TestList.empty[Int]

  //VARIANCE PROBLEM
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  // If Cat extends Animal,,,, Can List[Cat] extends List[Animal] ?? 3 answers
  // 1. Yes -- It is called COVARIANCE LIST
  class CovariantList[+A]
  val animal: Animal = new Cat //polymorphic type substitution
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // Cats are Animals
  // Can I add animalList.add(new Dog) ??? different type

  // 2. NO - Using Invariance list
  class InvariantList[A] //same as TestList[A] -- we cannot substitute one another
  // Ex: val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] is not possible we have to use "new InvariantList[Animal]" on the right
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. HELL NO ! -- CONTRAVARIANT LIST
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  // Cats replacing with Animals  - exactly opposites
  // Animal can be Dog or something in the above case - Especially for List of types is not possible

  //Let's say if this sematics for Trainer Class
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // In the above scenario if we requested for trainer for Cat, it is also fine if we get trainer for Animals .. This is good for this trainer case

  //Bounder Types:
  // Upper Bound
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car) // It will throw - inferred type arguments do not conform to class Cage

  //Lower Bound
  class LCage[A >: Animal](animal: A)


}
