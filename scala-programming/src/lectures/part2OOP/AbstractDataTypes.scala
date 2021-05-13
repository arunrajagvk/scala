package lectures.part2OOP
/**
 * Created by Arunraja on May 13 2021
 */
object AbstractDataTypes extends App {
  //abstract
  // It cannot be instantiated - because we have to implement the class methods
  // They are meant to be extended later
  // It can have unimplemented methods and regular ones

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("shout out") //not always recommended to use override
  }

  //traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore { //Use with to extend the class with a trait
    override val creatureType: String = "aligator"
    override def eat: Unit = println("crunch it")
    def eat(animal: Animal): Unit = println(s"I an $creatureType and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  dog.eat
  println(dog.creatureType)
  val aligator = new Crocodile
  println(aligator.creatureType)
  aligator.eat
  aligator eat dog

  //traits Vs Abstract classes
  // -- They both can have abstract and non abstract members
  // 1. Trait do not have constructor parameter
  // 2. you can inherit multiple traits by the same class -- MULTIPLE INHERITANCE
  // 3. traits = Behaviour (Carnivour), class = "thing" -- like dog or cat
}
