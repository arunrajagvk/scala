package lectures.part1basics

/**
 * Created by Arunraja on May 9 2021
 */
object Expressions extends App {

  val x = 2
  println(2 + 3 - 4)
  println(1 == x) // == != > >= < <=
  println( !(1 == x)) // ! && ||

  var aVar = 2
  aVar += 3 // -= *= /= .... side effects (var, console, changes)
  println(aVar)

  // Instructions (DO) Vs expressions(Value)
  // If expression (not instruction in scala) because instructions are executed , expressions are evaluated
  val aCondition = true
  val aConditionedVal = if(aCondition) 5 else 3 //If expression
  println(aConditionedVal)
  println(if(aCondition) 5 else 3)
  println(1+3)

  //Loops in Scala -- discouraged in Scala -- cause side effects
  // NEVER write like below ones -- this is imperative programming like in python, java (don't do it in Scala)
  // while is side effects
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

  //Everything in Scala is an Expression (except def of val, package, class)

  val aWeirdValue = (aVar = 3) // Unit === void  ..... Side effect (reassigning a variable are returning a Unit)
  println(aVar)
  println(aWeirdValue)

  // expressions returning unit .. side effects : println(), whiles, reassigning

  //code blocks

  val aCodeBlock = {
    val y = 2
    val z = y +1
    if(z > 2) "hello" else "bye!"
  }
  // The above one returns value of the last expression which is the if one
  // The right side is an expression
  // vals only visible inside the block
}
