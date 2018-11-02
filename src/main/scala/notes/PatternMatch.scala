package notes

class PatternMatch {
  def basicExample():Unit = {
    //Scala用match-case做匹配
    val s1 = Seq(1, 2, 3.7, "He He", "Ha Ha", "a", 5.2f)
    for (x <- s1) {
      //范围小的放在前面，范围大的放在后面。
      val in = x match {
        case 1 => "This is 1."  //直接匹配常量
        case _:Int => "This is Int." + x  //类型匹配，_通配传入值。
        case _:Double => "This is double." + x
        case "He He" => "This is He He."
        case _:String => "This is String." + x
        case _ => "This is default." + x  //最后需要一个全匹配做default的功能。
      }
      println(in)
    }
  }

  def varMatchExample():Unit = {
    //可以用变量做匹配，变量做匹配的时候用做通配变量，而不引用同名的全局变量或者全局方法。例子来源Learning Scala。
    val nonEmptySeq    = Seq(1, 2, 3, 4, 5)
    val emptySeq       = Seq.empty[Int]
    val nonEmptyList   = List(1, 2, 3, 4, 5)
    val emptyList      = Nil
    val nonEmptyVector = Vector(1, 2, 3, 4, 5)
    val emptyVector    = Vector.empty[Int]
    val nonEmptyMap    = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val emptyMap       = Map.empty[String,Int]

    //case中的head，tail并不对应Seq中的head，tail方法，这里只做通配。既，任何一个Seq都可以拆分成Head+:tail的形式。
    def seqToString[T](seq: Seq[T]): String = seq match {
      case head +: tail => s"$head +: " + seqToString(tail) //递归遍历数组
      case Nil => "Nil"
    }

    //Map不是Seq的子类，需要用toSeq方法转化成Seq类型。
    for (seq <- Seq(
      nonEmptySeq, emptySeq, nonEmptyList, emptyList,
      nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
      println(seqToString(seq))
    }
  }

  def matchWithIfExample():Unit = {
    //可以match-case的同时用if来过滤条件
    for (x <- Seq(1,2,3,4,5,6,7,8,9,10)) {
      x match {
        case _ if x%2 == 0 => println(s"$x is Good.")
        case _ => println(s"$x is Bad.")
      }
    }
  }

  def caseClassMatchExample():Unit = {
    //Scala case class可以用来做match
    case class Phone(name:String, price:Double, brand:String)
    val phone1 = Phone("iphoneX", 8999, "Apple")
    val phone2 = Phone("Mate 20", 5499, "HuaWei")
    val phone3 = Phone("Lumina", 2399, "Nokia")

    for (phone <- Seq(phone1,phone2,phone3)) {
      phone match {
        case Phone(_,_,"Apple") => println("This phone uses IOS.")
        case Phone("Mate 20",_,_) => println("This phone uses Android.")
        case Phone(_,_,"Nokia") => println("This phone uses unknown OS.")
      }
    }

    //可以用p @ 语法获取类的所有field
    for (phone <- Seq(phone1, phone2, phone3)) {
      phone match {
        case p @ Phone(_, _, "Apple") => println(s"p.name = ${p.name}, p.price = ${p.price}, p.brand = ${p.brand}")
        case p @ Phone("Mate 20",_,_) => println(s"p.name = ${p.name}, p.price = ${p.price}, p.brand = ${p.brand}")
        case p @ Phone(_,_,"Nokia") => println(s"p.name = ${p.name}, p.price = ${p.price}, p.brand = ${p.brand}")
      }
    }

    //Note, type match的时候不能用List[String]这样的运行时类型做match，编译器不认。
  }
}
