package lectures.part1basics
/**
 * Created by Arunraja on May 9 2021
 */
object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 44))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunc(aString: String, n: Int): String= {
    if(n == 1) aString
    else aString + " " + aRepeatedFunc(aString, n-1)
  }
  println(aRepeatedFunc("hello", 4))
  // WHEN YOU NEED LOOPS, USE RECURSION ***
  // ALWAYS SPECIFY A RETURN TYPES FOR FUNCTION, especially for recursive ones
  // USE Unit as return type for side effect for example:

  def aFuncwithSideeffect(aInt: Int): Unit = {
    println(aInt)
  }
  println(aFuncwithSideeffect(4))

  //Code block also allows you to define an auxillary function

  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b
    aSmallFunction(n, n-1) //return expression
  }
  println(aBigFunction(10))

  def aGreet(name: String, age: Int): String={
    return "Hi, my name is " + name + " and I am " + age + " years old."
  }
  println(aGreet("dummyName", 45))

  def aFactorialFunc(i: Int): Int ={
    if(i <= 0) 1
    else i * aFactorialFunc(i-1)
  }
  println(aFactorialFunc(4))

  def fibanocci(n: Int): Int ={
    if (n <= 2) 1
    else fibanocci(n-1) + fibanocci(n-2)
  }
  println(fibanocci(6))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(12))
}
