package lectures.part2OOP

import lectures.part1basics.CheckClass
import java.util.Date
import java.sql.{Date => SqlDate} //alias

/**
 * Created by Arunraja on May 14 2021
 * Packaging
 */
object PackagingAndImports extends App {

  val writer = new Writer("Arun","Raja", 2010) //same package
  val check = new CheckClass //imported from part 1 automatically or give fully qualified name here
  val newCheck = lectures.part1basics.CBNandCBV
  newCheck.callByValue(6)


  val d = new SqlDate(2018, 5, 12)

  // package Object - one per package
  // it will be default with the file name package.scala inside the base lectures/par2OOP package for par2OOP
  // Useful for global methods and static variables usage for all part2OOP

  println(PI)



}
