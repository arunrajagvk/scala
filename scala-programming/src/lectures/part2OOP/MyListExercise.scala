package lectures.part2OOP
/**
 * Created by Arunraja on May 13 2021
 * Generics complete with map, filter, flatmap
 */

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](elem: B): MyList[B]
  def printElements: String
  //Polymorphic call
  // toString, hashcode, equals are methods that are present in AnyRef class - so we need to override them to use it
  override def toString: String = "[" + printElements + "]"
  /*
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

   */
  // Now the below set of functions map, flatmap and filter are functional one and are called HOF = higher order functions
  // because it uses functions as first class citizens
  // takes functions as its argument and returns function
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
  def map[B](transformer: (A => B)): MyList[B]
  def flatMap[B](transformer: (A => MyList[B])): MyList[B]
  def filter(predicate: (A => Boolean)): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
  def foreach(f: (A => Unit)) : Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C ): MyList[C]
  def fold[B](start: B) (operator: (B, A) => B): B
}

case object EmptyList extends MyList[Nothing] { //Nothing is the base type for nothing
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, EmptyList)
  override def printElements: String = ""

  def map[B](transformer: (Nothing => B)): MyList[B] = EmptyList
  def flatMap[B](transformer: (Nothing => MyList[B])): MyList[B] = EmptyList
  def filter(predicate: (Nothing => Boolean)): MyList[Nothing] = EmptyList
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  def foreach(f: (Nothing => Unit)) : Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C ): MyList[C] = {
    if(!list.isEmpty) throw new RuntimeException(" List do not have same length")
    else EmptyList
  }

  def fold[B](start: B) (operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
    [1,2,3].filter(n % 2 == 0)=
      [2,3].filter(n % 2 == 0)=
      = new Cons(2, [3].filter(n % 2 == 0))=
       = new Cons(2, EmptyList.filter(n % 2 == 0))=
       = new Cons(2, EmptyList)
   */
  def filter(predicate: (A => Boolean)): MyList[A] = {
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
   [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, EmptyList.map(n * 2)))) // new Cons(2, new Cons(4, new Cons(6, EmptyList)))

     def map[B](transformer: MyTransformer[A,B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))


   */

 // Chaging to function type of Above
  def map[B](transformer: (A => B)): MyList[B] =
    new Cons(transformer(h), t.map(transformer))
  /*
    [1,2] ++ [3,4,5] =
    new Cons(1, new Cons(2, new Cons(3 ......))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t.++(list))

  /*
  [1,2,3].flatMap( n => [n, n+1])
  = [1,2] ++ [2,3].flatMap( n => [n, n+1])
  = [1,2] ++ [2,3] ++ [3].flatMap( n => [n, n+1])
  = [1,2] ++ [2,3] ++ [3,4] ++ emptyList
  = [1,2,2,3,3,4]
   */
  def flatMap[B](transformer: (A => MyList[B])): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def foreach(f: (A => Unit)) : Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if(sortedList.isEmpty) new Cons(x, EmptyList)
      else if (compare(x, sortedList.head) <=0 ) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }


  def zipWith[B, C](list: MyList[B], zip: (A, B) => C ): MyList[C] =
    if(list.isEmpty) throw new RuntimeException(" List do not have same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  /*
  [1, 2, 3].fold(0) (+) =
   = [2,3].fold(1) (+)
   = [3].fold(3) (+)
   = [].fold(6)(+)
   = 6
   */

  def fold[B](start: B) (operator: (B, A) => B): B = {
    //val newStart = operator(start, h)
    t.fold(operator(start, h)) (operator)
  }


}
// Removed the earlier version to use functionTypes instead

/*trait MyPredicate[-T] {
  def test(ele: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(ele: A): B
}*/

object MyListExercise extends App {
  val listInt= new Cons(1, new Cons(2, new Cons(3 , EmptyList)))
  println(" *** INIT ***")
  val testInt = Cons(1, Cons(2, EmptyList))
  println(testInt)
  println(listInt.head)
  println(listInt.tail.tail.head)
  println(listInt.add(6).head)
  println(listInt.isEmpty)
  println(listInt.toString)
  val listString= new Cons("Hello", new Cons("There", new Cons("TTT" , EmptyList)))
  println(listString.head)
  println(listString.tail.tail.head)
  listString.add("ScalaHere").head
  println(listString.toString)

  val shortlistInt= new Cons(1, new Cons(2, EmptyList))

  println(" **** FILTER **** ")
  // FILTERING
  // STEP 1
  println(listInt.filter(new Function1[Int, Boolean] {
    override def apply(ele: Int): Boolean = ele % 2 == 0
  }).toString)
  // STEP 2 -- Use Anonymous - lambda
  println(listInt.filter(ele => ele % 2 == 0).toString)
  println(listInt.filter(_ % 2 == 0).toString)

  println(" **** MAP **** ")
  // MAP
  // STEP 1
  println(listInt.map(new Function1[Int, Int] {
    override def apply(ele: Int): Int = ele * 2
  }).toString)
  // STEP 2
  // Further Changing it to Anonymous function - LAMBDA
  println(listInt.map( elem => elem * 2 ).toString)
  println(listInt.map(_ * 2).toString)

  println(" **** CONCAT **** ")
  // CONCAT
  println(listInt ++ listInt)

  println(" **** FLATMAP **** ")
  // FLATMAP
  // STEP 1 -- Reduce the MyTransformer trait to Functions
  /*
  println(listInt.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(ele: Int): MyList[Int] = new Cons(ele, new Cons(ele + 1, EmptyList))
  }).toString)
  */
  // Change the above flatMap to Function types as

  println(listInt.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(ele: Int): MyList[Int] = new Cons(ele, new Cons(ele + 1, EmptyList))
  }).toString)

  // STEP 3 - LAMBDA
  // Further shortening to Anonymous function Lambda
  println(listInt.flatMap(ele => new Cons(ele, new Cons(ele + 1, EmptyList))).toString)
  // NOTE : We cannot use _ notation here as we use ele twice - _ works if we use one per notation

  println(" **** FOREACH **** ")
  // FOREACH
  listInt.foreach(println)

  println(" **** SORT **** ")
  // SORT
  println(listInt.sort((x, y) => y - x))

  println(" **** ZIP **** ")
  // ZIP
  println(listInt.zipWith(listString, (x: Int, y: String) => x +"-"+ y))
  println(listInt.zipWith[String,String](listString, _ +"-"+ _))

  println(" **** FOLD **** ")
  // FOLD
  println(listInt.fold(0)(_ + _))
  println(listInt.fold(2)(_ - _))


  val clonelistInt= new Cons(1, new Cons(2, new Cons(3 , EmptyList)))
  println(clonelistInt == listInt) //because of implementing case class
}