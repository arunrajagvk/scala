package lectures.part1basics

/**
 * Created by Arunraja on May 9 2021
 */
object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)
  // VALS are immutable
  // Compilers can infer types
  // Semicolon unnecessary unless multiple expressions in the same line

  val aString: String = "hello"
  val aBool: Boolean = false
  val aShort: Short = 4614
  val aLong: Long = 586868993984L
  val aFloat: Float = 2.0f
  val aDouble: Double = 33.555

  // variable var
  var aVar: Int = 4
  aVar = 66 //side effects
  println(aVar)
}
