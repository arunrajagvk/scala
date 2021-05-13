package exercises
/**
 * Created by Arunraja on May 13 2021
 * Linked List implementation with Integers
 */
// The below implementation is only for Int -- for all types introduce Generics follow down
/**
abstract class MyList {

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(elem: Int): MyList
  def printElements: String
  //Polymorphic call
  // toString, hashcode, equals are methods that are present in AnyRef class - so we need to override them to use it
  override def toString: String = "[" + printElements + "]"
}

// Why object -- as we does not need any parameter
object EmptyList extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(elem: Int): MyList = new Cons(elem, EmptyList)
  override def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(elem: Int): MyList = new Cons(elem, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  val list = new Cons(1, EmptyList)
  println(list.head)
  println(list.tail)
  val list2 = new Cons(1, new Cons(2, new Cons(3 , EmptyList)))
  println(list2.head)
  println(list2.tail.tail.head)
  println(list2.add(6).head)
  println(list2.isEmpty)
  print(list2.toString) // This will call the printElements from the Cons class
}

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
}

object EmptyList extends MyList[Nothing] { //Nothing is the base type for nothing
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, EmptyList)
  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
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
}