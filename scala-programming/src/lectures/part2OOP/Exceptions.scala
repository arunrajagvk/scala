package lectures.part2OOP
/**
 * Created by Arunraja on May 14 2021
 * Exceptions
 */
object Exceptions extends App {
  // 1. Throwing Exceptions
  val x: String = null
  //println(x.length) //this will crash with NullPointerExceptions
  //
  //throw new NullPointerException // this will throw an exception - also this is an exception

  //val aWeirdValue = throw new NullPointerException //instanstiating exception and throw
  // aWeirdValue holds Nothing

  //throwable classes extend the Throwable class
  //Exception and Error are the major throwable subtypes


  // 2. Catch Exceptions

  def getInt(withExceptions: Boolean): Int =
    if(withExceptions) throw new RuntimeException("No int for you")
    else 42

  try{
    // failing code
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught RT exception")
    case n: NullPointerException => println("Caught NP exception")
  } finally {
    // code will get executed no matter what
    println("finally")
  }

  val potentialFail = try{
    // failing code
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught RT exception")
  } finally {
    // code will get executed no matter what
    println("finally")
  }

  // potentialFail has AnyVal as it return type which is from catch block , if we change the println= unit to an Int then it has Int as its return type
  // finally does not influence the return type of potentialFail
  // finally is optional
  // use finally only for side effects

  // 3. Define own exceptions
  class MyException extends Exception
  val exception = new MyException
  //throw exception

  /*
  // crash program with OutofMemoryError

    val array = Array.ofDim(Int.MaxValue) // Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit

   */

  /*
  // Crash with StackOverflowException
    def infiniteloop: Int = 1 +infiniteloop
    val nolimit = infiniteloop //Exception in thread "main" java.lang.StackOverflowError

   */

  /*
      //PocketCalculator
        -- add(x,y)
        --sub(x,y)
        --multiply(x,y)
        --divide(x,y)

      //throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if sub(x,y) exceeds Int.MIN_VALUE
          - MathCalculationException for divide by 0
   */
  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result

    }
    def sub(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    }

    def mult(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    }

    def div(x: Int, y:Int) = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }

  //println(PocketCalculator.add(Int.MaxValue,1))
  println(PocketCalculator.div(Int.MaxValue,0))


}
