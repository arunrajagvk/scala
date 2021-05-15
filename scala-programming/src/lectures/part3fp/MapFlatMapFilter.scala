package lectures.part3fp
/**
 * Created by Arunraja on May 15 2021
 *
 */
object MapFlatMapFilter extends App {

  val list = List(1, 3, 4, 2, 7, 5, 6)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ * 2))
  println(list.map( _ + "-ggeerr"))

  //filter
  println(list.filter(_ % 2 == 0))

  //flatMap
  println(list.flatMap((x: Int) => List(x , x + 1)))
  // combinations
  val num = List(1,2,3,4)
  val chars = List('a','b','c')
  val colors = List("black","white")

  val combinations = num.flatMap(n => chars.flatMap(c => colors.map(col => "" + n + c + "-" + col)))
  println(combinations)

  //for - comprehension
  val forComp = for {
    n <- num if(n % 2 == 0)
    c <- chars
    col <- colors
  } yield ("" + n + c + "-" + col)
  println(forComp)

  num.foreach(println)
  // identical
  for{
    n <- num
  }println(n)
}
