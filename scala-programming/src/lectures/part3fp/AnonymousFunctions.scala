package lectures.part3fp
/**
 * Created by Arunraja on May 14 2021
 * Anonymous Functions - LAMBDA
 */
object AnonymousFunctions extends App {

  // Function defined in Object oriented way of defining and instantiating
  val doubler = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  // The above doubler can be defined as below
  // LAMBDA -- anonymous function
  val doublerFun: Int => Int = (x: Int) => x * 2

  // we can further short them as - compiler will help you with type inference
  val doublerFunShort: Int => Int = x => x*2

  //Multiple parameters
  val adderFunc: (Int, Int) => Int = (a: Int,b: Int) => a + b
  val adderFuncShort: (Int, Int) => Int = (a,b) => a + b

  println(adderFunc(2, 4))
  println(adderFuncShort(2, 4 + 5))

  // NO Parameter
  val justDoSomething: () => Int = () => 55

  //you need to call the lambda with only empty parenthesis ()
  println(justDoSomething) // returns lectures.part3fp.AnonymousFunctions$$$Lambda$9/787387795@7907ec20 (function itself)
  println(justDoSomething()) // returns 55  (actual call)

  //Curly braces with lambdas
  val stringToInt= { (str: String) =>
    str.toInt
  }

  // More Syntactic sugar
  // IMPORTANT -- NEED function definitions with proper type
  val niceIncrementer: Int => Int = x => x +1
  val niceIncrementerShort: Int => Int = _ + 1 // equivalent to x => x + 1

  val niceAdder: (Int, Int) => Int = (a, b) => a + b
  val niceAdderShort: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b


  /*
    1. MyList to change all functionX calls with lambdas
    2. Curried special adder as an anonymous functions
   */

  //1 . Check MyListExercise.scala
  //2.
  val superAdder = new Function[Int, Function1[Int, Int]] {
    override def apply(x: Int): Int => Int = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val superAdderLambda = (x:Int) => (y:Int) => x + y

  println(superAdderLambda(5)(10))


}
