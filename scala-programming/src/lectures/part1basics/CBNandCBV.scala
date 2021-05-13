package lectures.part1basics
/**
 * Created by Arunraja on May 9 2021
 */
object CBNandCBV extends App {

  def callByValue(x: Long): Unit = {
    println("first print val: " + x) // computed x passed in
    println("second print val: " + x) // computed x passed in
  }

  def callByName(x: => Long): Unit = { //delays the evaluation as the expression passed as the parameter, lazy streams
    println("first print name: " + x)
    println("second print name: " + x)
  }

  callByValue(System.nanoTime()) //computed the time first then it is passed into the function for usage
  callByName(System.nanoTime())
}
