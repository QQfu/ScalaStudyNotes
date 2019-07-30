package notes.scalacookbook

class StringNotes {
  def stringExample1():Unit = {

    val s1 =
      """
        |Line1
        |Line2
        |Line3
        |""".stripMargin

    //println(s1)

    val s2 =
      """
        #Line1
        #Line2
        #Line3
        #""".stripMargin('#')

    //println(s2)

    val s3:String =
      """
        |Line1
        |Line2
        |Line3
        |""".stripMargin.replace("\n","").replace("\r","")

    s3.foreach(println) //Line1 Line2 Line3 [Each char in one line]
    s3.getBytes.foreach(print) //761051101014976105110101507610511010151
    println()
    println(s3.length) //15
    println(s3) //Line1Line2Line3

  }
}
