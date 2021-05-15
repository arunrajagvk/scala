package lectures.part2OOP
/**
 * Created by Arunraja on May 13 2021
 * Case Classes
 */
object CaseClasses extends App {
  /*
  quick lightweigh data structures
  equal, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields (without explicit vals)
  val jim = new Person("jim", 44)
  println(jim.name)

  // 2. Sensible toString
  println(jim.toString) // Prints Person("jim", 44)
  println(jim) //syntactic Sugar

  //3. equal and hashCode implemented out of the box
  val jim2 = new Person("jim", 44)
  println(jim == jim2) //equals compute for same reference for case class

  //4. CC's have copy methods
  val jim3 = jim.copy(age=55)
  println(jim3) //modified age

  //5. CC's have companion objects
  val thePerson = Person
  val mary = Person("mary",30) //apply methods will be called internally
  println(mary)

  // 6. CC's are serializable -- which make CC's useful in distributed systems (AKKA - sending seri messages through network)

  //7. CC's have extractor patters - can be used for PATTERN MATCHING

  case object UK {
    def name: String = "The UK"
  }


}
