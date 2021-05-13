package lectures.part2OOP

object AnonymousClasses extends App {

  abstract class Animal {
    def eat
  }

  //anonymous class - works for abstract classes,
  // anonymous works for classes or traits
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("anonymous Animal")
  }
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def greet: String = s"Hi from $name"
  }

  val jack = new Person(name = "Jack"){
    override def greet: String = super.greet
  }
  println(jack.greet)
}
