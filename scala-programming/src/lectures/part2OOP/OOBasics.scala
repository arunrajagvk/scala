package lectures.part2OOP

import org.w3c.dom.css.Counter

/**
 * Created by Arunraja on May 10 2021
 */
object OOBasics extends App {
  val person = new Person("Arun", 30)
  println(person.x)
  person.greet("Sow")
  person.cgreet("Sow")
  println(person.greet)


  val counter = new Counter
  counter.currCnt
  counter.inc.currCnt
  counter.inc.inc.inc.inc.currCnt
  counter.dec(5).currCnt


  val author = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 2000, author)
  println(novel.isWrittenBy(author))
  println(author.fullname())
  println(novel.authorAge)
  val newReleased = novel.copy(2020)
  println(newReleased.authorAge)
}

class Person(name: String, val age: Int = 1) {

  val x = 2
  println(1 + 2) //This prints or evaluated while instantiating this class .. side effect

  def greet(name: String): Unit = println(s"$name says! Hi $name")
  def cgreet(name: String): Unit = println(s"${this.name} says! Hi $name") //class attribute

  // Overloading same method name with diff parameter
  def greet(): Int = 44
  // this won't work overloading is not for change in only return types like greet(): Unit

  //multiple Constructor
  def this(name: String) = this(name, 2) //secondary constructor
  def this() = this("Joe") //can call primary or secondary

}

class Novel (name: String, year_of_release: Int, author: Writer ) {

  def authorAge = year_of_release - author.year

  def isWrittenBy(author: Writer) = author == this.author //test author of novel is writer

  def copy(new_year_of_release: Int): Novel = new Novel(name, new_year_of_release, author) //copy (new year of release) = new instance of Novel
}

class Writer(firstname: String, surname: String, val year: Int) {

  def fullname(): String = this.firstname+ " " +this.surname

}

class Counter(val cnt: Int = 0) {


  def currCnt = println(cnt)

  def inc = {
    println("incrementing")
    new Counter(cnt + 1)
  } //immutability

  def dec = {
    println("decrementing")
    new Counter(cnt - 1)
  } //immutability

  def inc(n: Int): Counter = {
    if(n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if(n <= 0) this
    else dec.dec(n-1)
  }
 /* def incCnter(incCnt: Int): Int = {
    this.finalCounter += incCnt
    this.finalCounter
  }

  def decCnter(decCnt: Int): Int = {
    this.finalCounter -= decCnt
    this.finalCounter
  }*/
}

