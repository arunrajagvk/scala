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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

object EmptyList extends MyList[Nothing] { //Nothing is the base type for nothing
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, EmptyList)
  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = EmptyList
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = EmptyList
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
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
  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

  /*
   [1,2,3].map(n * 2)
    = new Cons(2, [2,3].map(n * 2))
    = new Cons(2, new Cons(4, [3].map(n * 2)))
    = new Cons(2, new Cons(4, new Cons(6, EmptyList.map(n * 2)))) // new Cons(2, new Cons(4, new Cons(6, EmptyList)))
   */
  def map[B](transformer: MyTransformer[A,B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
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
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(ele: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(ele: A): B
}

object TraitExercise extends App {
  val listInt= new Cons(1, new Cons(2, new Cons(3 , EmptyList)))
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

  println(listInt.filter(new MyPredicate[Int] {
    override def test(ele: Int): Boolean = ele % 2 == 0
  }).toString)

  println(listInt.map(new MyTransformer[Int, Int] {
    override def transform(ele: Int): Int = ele * 2
  }).toString)

  println(listInt ++ listInt)

  println(listInt.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(ele: Int): MyList[Int] = new Cons(ele, new Cons(ele + 1, EmptyList))
  }).toString)
}