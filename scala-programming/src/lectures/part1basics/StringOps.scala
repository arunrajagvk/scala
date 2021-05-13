package lectures.part1basics
/**
 * Created by Arunraja on May 10 2021
 */
object StringOps extends App {

  val str: String = "String here !!"

  println(str.charAt(2))
  println(str.substring(10, 13))
  println(str.split(" ").toSeq)
  println(str.split(" ").toList)
  println(str.startsWith("Str"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)
  println(str.reverse)
  println(str.take(3))

  val aNum = "2"
  val adummy = 'a' +: aNum :+ 'z' //append prepend
  println(adummy)

  val name = "Arun"
  val age = 30
  val speed = 35.3f
  // S-interpolators
  val greetings = s"My name is $name and my age is ${if (age <= 30) age + 2 else age} ."
  println(greetings)

  // F-interpolators
  val greetingsNew = f"My name is $name and my age is ${if (age <= 30) age + 2 else age} running at $speed%3.4f."
  println(greetingsNew)

  // Raw-interpolators
  val greetingsRaw = s"My name is $name and my age is ${if (age <= 30) age + 2 else age} running \n at $speed%3.4f."
  println(greetingsRaw)
}
