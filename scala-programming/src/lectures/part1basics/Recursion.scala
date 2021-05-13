package lectures.part1basics

import scala.annotation.tailrec

/**
 * Created by Arunraja on May 9 2021
 */
object Recursion extends App {
  // Factorial
  def newFactorial(n: Int): BigInt = {
    @tailrec
    def innerFactorial(n1: Int, acc: BigInt): BigInt = {
      if(n1 <= 1) acc
      else innerFactorial(n1 - 1, n1 * acc)
    }
    innerFactorial(n, 1) //Tailrecursion no multiple stacks created for each iterative loop instead accumulator stores them which use single stack increment
  }
  println(newFactorial(3))

  //string Concat
  def ipString(ipstr: String, times: Int): String = {
    @tailrec
    def insideConcat(intimes: Int, acc: String): String = {
      if (intimes <1) acc
      else insideConcat(intimes-1, ipstr + " " + acc)
    }
    insideConcat(times, "")
  }
  println(ipString("hello",6))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t-1, n % t !=0 && isStillPrime)
    }
    isPrimeTailrec(n / 2, true)
  }
  println(isPrime(13))
  println(isPrime(2003))
  println(isPrime(16))

  // 1 1 2 3 5 8 13 21 ...
  def fibonacci(n: Int): Int = {
    def fibTail(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibTail(i + 1, last + nextToLast, last)
    }
    if(n <= 2) 1
    else fibTail(2, 1, 1)
    }
  println(fibonacci(7))



}
