package lectures.part3fp

/**
 * Created by Arunraja on May 14 2021
 *  HOF and Curries
 */

object HOFsandCurries extends App {

  //Read a complex function definition from the last right return type
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // The above one is HOF

  // How to write a function that applies a function n times over a value x
  // nTimes(f, n, x) ==== nTimes(f, 3, x) === nTimes(f, 2, f(x))
  // f(f(f(x))) ==== nTimes(f, n, x) = nTimes(f, (n-1), f(x)) == recursive apply

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))
  println(nTimes(plusOne,0,1))

  def nTimesBetter(f: (Int => Int), n: Int): (Int => Int) =
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1) (f(x))

  val plus10 = nTimesBetter(plusOne,10)
  println(plus10(10))

  // CURRIED
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10)) // 13
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String) (x: Double): String = c.format(x)
  println(curriedFormatter("%5.6f")(4.6))

  val stdFormat: (Double => String) = curriedFormatter("%4.2f")
  println(stdFormat(Math.PI))

  /*
  1. Expand MyList
      - foreach method A => Unit
       [1,2,3].foreach(x => println(x))

      - sort function ((A, A) => Int) => MyList
      [1, 2, 3].sort((x, y) => y - x)) => [3, 2, 1]

      - zipWith (list, (A, A) => B) => MyList[B]
      [1, 2, 3].zipWith([4, 5, 6], x * y) => [1*4, 2*5, 3*6]=> [4, 10, 18]

      - fold(start) (function) => a value
      [1, 2, 3].fold(0)(x+y) = 6
   */

    /*
      2. toCurry(f: (Int, Int) => Int ) => Int => Int => Int
          fromCurry(f: Int => Int => Int) => (Int, Int) => Int
     */
      def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
        x => y => f(x,y)

      def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
        (x, y) => f(x) (y)

      def superAdder2: (Int => Int => Int) = toCurry(_ + _)
      def add4: Int => Int = superAdder2(4)
      println(add4(10))

      /*
       3. compose(f, g) => x => f(g(x))
          andThen(f, g) => x => g(f(x))
       */
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def composeG[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThenG[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  val times3: (Int => Int) = _ * 3
  val add2: (Int => Int) = _ + 2
  val composed = composeG(add2, times3)
  val ordered = andThenG(add2, times3)
  println(composed(5)) // 5 *3 -> 15 +2 = 17
  println(ordered(5)) // 5 + 2 -> 7 * 3 = 21
}
