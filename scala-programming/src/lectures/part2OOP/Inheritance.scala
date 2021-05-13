package lectures.part2OOP
/**
 * Created by Arunraja on May 12 2021
 */
object Inheritance extends App {

  class Animal {
    private val planet = "Earth"
    val sounds = "shhhhhh"
    def eyes = 1
    def legCount: Int = 0
    val newv: Double = 3.4444
    def newPrint = println(sounds)

  }

  // Override
  class Cat extends Animal{
    override def legCount: Int ={
      println(super.legCount)
      4
    }
    override val sounds = "meow"

  }
  val cat = new Cat //can call the protected inside as overrided ones and cannot use private
  println(cat.legCount)
  println(cat.newPrint)
  println(cat.newv)

  val animal = new Animal //cannot call the protected and private here
  println(animal.newv)
  println(animal.legCount)

  class Dog(override val newv: Double) extends Animal{
    override def eyes = 2
    override val sounds = "bowwww"
  }

  val dog = new Dog(4.5)
  println(dog.eyes)
  println(dog.newv)
  println(dog.newPrint)
  println(dog.legCount)

  // Polymorphism
  //Eventhough the Type is Animal it is overridden by Dog Instance. Method call always goes to the recent overridden ones (derived implementation)
  val unknownAnimal: Animal = new Dog(5)
  println(unknownAnimal.eyes)
  println(unknownAnimal.newv)
  println(unknownAnimal.legCount)
  //overRIDDING - Supplying a different implementation in derived classes
  //overLOADING - supplying multiple methods with diff signature with the same name in the same class

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, haveId: Boolean) extends Person(name)

  // super.

  // final
       // final val v = 10  --- we cannot use v further in any of the classes
      // final class Animal -- cannot extend them further
      // seal class -- can only extend within this file CANNOT extend in another FILE or Outside this file

    sealed class Sample {
      final val dummy = 3
    }

    class Dummy extends Sample {

    }

}
