package lectures.part3fp

/**
 * Created by Arunraja on May 14 2021
 * Functional Programming 101
 */

object WhatsAFunction extends App {
  // use functions as a first class element
  // use functions as values
  // pass functions as parameters
  // Scala works on top of the JVM - designed for JAVA -- for which first-class elements are objects(instances of classes)
  // ALL SCALA functions are objects
  //function traits, up to 22 params
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) //doubler is a instance of a function like class can be called like a function
  // scala support function types - function1, function2 ... function22
  // Example
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("4") + 5)

  // Next function
  // syntactic sugar Function2[A, B , R] = ((A, B) => R)
  val adder: ((Int, Int) => Int )= new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  println(adder(2,3))

  //ALL SCALA FUNCTIONS ARE OBJECTS

  /*
  1. Function takes 2 strings and concat them
   */
  val concat: ((String, String) => String) = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concat("new", "language"))

  /*
  2. change the MyList to Function types for MyPredicate and MyTransformers
   */

  /*
  3. def a function takes int and a function ( int, int)
  -- what's the type of this function
  -- how to do it
   */

  val superAdder = new Function[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Int => Int = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }

  val adder3 = superAdder(3) //passed as a function
  println(adder3(4)) //v2
  // Curried function
  println(superAdder(4)(6)) // when it first applied with 4 returns function1 and then applied it with 6

  //More shorter

  val superAdderLambda = (x:Int) => (y:Int) => x + y

  println(superAdderLambda(5)(10))

}


// This is how we can use function simulated from instance as
trait MyFunction[A, B] {
  def apply(element: A): B
}

trait MyFunctionCon[A, B, R] {
  def apply(element: A, element2: B): R
}
