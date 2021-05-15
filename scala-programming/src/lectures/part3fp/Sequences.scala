package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  val aSequence = Seq(1,2,3,5,7,6)
  println(aSequence)
  println(aSequence.length)
  println(aSequence.reduce(_ + _))
  println(aSequence(2))
  println(aSequence ++ Seq(8,3,4))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 15
  aRange.foreach(println)
  val newRange: Seq[Int] = 1 until 10
  newRange.foreach(println)

  (1 to 10).foreach(x=> println("Test Times "+x))

  // lists
  val aList = List(1,2,3)
  val prepend = 42 :: aList
  println(prepend)
  val prependPrePostFix = 44 +: prepend :+ 55
  println(prependPrePostFix)

  val apples5 = List.fill(4,2)("filled")
  println(apples5)
  println(aList.mkString("!-!"))

    // arrays
   // They are JAVA implementation ones
  val arr = Array(1,2,3,4)
  arr.foreach(println)
  val threeElement = Array.ofDim[Double](2)
  threeElement.foreach(println)

  //mutation
  arr(2) = 0
  println(arr.mkString(" # "))

  //Array and Seq
  val numbersSeq: Seq[Int] = arr //implicit conversion
  println(numbersSeq.mkString(" "))

  // vector - 32 tree
  val nVec = Vector(1,2,4,5,5,5)
  nVec.foreach(println)
  println(nVec.mkString(" "))

  //Benchmarking Vector Vs List
  val maxRun = 10000
  val maxRandomCap = 1000000

  def testBenchmark(collection: Seq[Int]): Double = {
    val r = new Random
    val runTime = for {
      it <- 1 to maxRun
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxRandomCap), r.nextInt())
      System.nanoTime() - currentTime
    }
    runTime.sum * 1.0 / maxRun
  }

  val numbersList = (1 to maxRandomCap).toList
  val numbersVector = (1 to maxRandomCap).toVector

  //keeps reference to tail
  // accessing head is faster
  //updates in between are slower
  println(testBenchmark(numbersList))

  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(testBenchmark(numbersVector))

}
