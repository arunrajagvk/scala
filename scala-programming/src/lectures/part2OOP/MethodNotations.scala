package lectures.part2OOP
/**
 * Created by Arunraja on May 10 2021
 */
object MethodNotations extends App {

  class Person(val name: String, favMovie: String, val age: Int =0) {
    def likes(movie: String): Boolean = movie == favMovie
    def +(person: Person): String = s"${this.name} and ${person.name} are friends"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favMovie)
    def unary_! : String = s"${favMovie} is awesome"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def pfix: String = favMovie.take(3)
    def apply(): String = s"Hi I am $name and my fav movie is ${this.favMovie}"
    def apply(n: Int): String = s"$name watched $favMovie $n times"
    def learns(learnsWhat: String) = s"$name learns $learnsWhat"
    def learnsScala: String = learns("scala")


  }
  // infix notation -- apply only for single parameter
  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") //both are same only works with single param
  //operators
  val john = new Person("John", "Anaconda")
  println(john.+(mary))
  println(john + mary)

  println(1 + 2)
  println(1.+(2))

  //prefix notation only allowed for +, -, !, ~
  println(-1)
  println(1.unary_-)
  println(!mary)

  //postfix with no parameter
  import scala.language.postfixOps
  println(mary.name)
  println(mary pfix)


  //apply -- used without parameter and class name as method name
  println(mary.apply())
  println(mary())
  println(mary(2))

  // exercise
  println(mary learns "scala")
  println(mary learnsScala)
  println(mary(4))
  println(john + "the rockstar" learns "JAVA")
  println((john + "the rockstar")())
  println((+mary).age)
  println(mary + mary)

}
