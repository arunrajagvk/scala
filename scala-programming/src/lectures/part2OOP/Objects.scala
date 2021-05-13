package lectures.part2OOP
/**
 * Created by Arunraja on May 12 2021
 */
object Objects extends App {

  //Scala does not have call level functionalities like "Static var, methods"
  // SINGLETON INSTANCE (pattern)
  object Person { //type Person + its only instance
    // "static" and "class" level functionality
    val PI: Double = 3.14
    def stMethod = false

    //factory method
    def from(mom: Person, dad: Person): Person = new Person("Bobbie")
    // the purpose is to only build a
    def apply(mom: Person, dad: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance level functionality
  }
  // companions - same scope and same name
  //
  import scala.language.postfixOps
  println(Person.PI)
  println(Person stMethod)

  val name1 = Person
  println(name1)
  val name2 = Person
  println(name2)
  println(name1 == name2) //single instance and they both name1 and name2 points to same instance

  val name3 = new Person("name3")
  println(name3)
  val name4 = new Person("name4")
  println(name4)
  println(name3 == name4) //It refers to the class person, so you can instantiate newer one

  val bobbie = Person.from(name3, name4)
  val name5 = Person(name3, name4) //the above one with apply method which is widely used

  // SCALA APPLICATION  has to be Scala object with
      // def main(args: Array[String]): Unit
  // similar to adding "extends App"
}
