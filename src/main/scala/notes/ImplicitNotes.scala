package notes

class ImplicitNotes {
  def basicExample():Unit = {
    //Scala可以有隐式定义，最简单的是隐式参数。

    //multiTheNumber定义了一个隐式参数secNum，返回两数相乘的结果
    def multiTheNumber(firNum: Double)(implicit secNum: Double) = {
      firNum * secNum
    }

    //DoubleTheNumber类引用了multiTheNumber方法，并给隐式参数secNum赋值为2。
    class DoubleTheNumber {
      implicit val sec: Double = 2.0

      def printResult(num: Double): Unit = {
        println(multiTheNumber(num))
      }
    }

    //结果为100
    new DoubleTheNumber().printResult(50)
    //结果为200
    println(multiTheNumber(50)(4))

    //隐式定义具有定域性。
    object Imp1 {
      implicit def secNum: Double = 3.0
    }

    object Imp2 {
      implicit def secNum: Double = 5.0
    }

    {
      //输出150
      import Imp1.secNum
      println(multiTheNumber(50))
    }

    {
      //输出250
      import Imp2.secNum
      println(multiTheNumber(50))
    }
  }

  //Implicit type definition可以用来做参数限定和类型区分。
  def implicitEvidenceExample():Unit = {
    //用implicit evidence来验证类型。
    val intList:List[Int] = List(1,2,3,4,5)
    val strList:List[String] = List("1", "2", "3", "4", "5")
    def printInt[A](inList:List[A])(implicit ev: A <:< Int):Unit = {
      intList foreach println
    }

    printInt(intList) //不会报错，因为Int是Int的子类。
    //printInt(strList) //编译错误，无法证明String <:< Int， String不是Int的子类。

    def printStr[A](inList:List[A])(implicit ev: A <:< String):Unit = {
      intList foreach println
    }

    printStr(strList) //不会报错。
    //Notes：printInt和printStr如果是用同样的函数名并且直接用List[Int]和List[String]来区分类型的话，编译器不认。
    //def printX(intList:List[Int]) 和 def printX(inList:List[String]) 会被编译器认为是同一个函数，不构成重载。
    //换句话说，像List这样后面方括号里的类型是运行时类型，编译时并不构成具体的类型。
  }

  //implicit class可以定义单参数的方法来进行隐式转换。
  def implicitConversionExample():Unit = {
    object ImpConversionLink {
      implicit final class link2[A](str:A) {
        def ->>>[V] (v:V): String = str.toString + v.toString
      }
    }

    import ImpConversionLink._
    val a:Int = 2
    val b:Double = 3.73
    val c:String = "Hello~"
    //Int， Double， String都没有->>>方法，但是编译器会从implicitConversionExample找到隐式类link2，
    // 并构造一个link2类，把自己传参进去，再将->>>后的变量当作->>>的参数传参进去的到结果。
    println(s"a ->>> b = ${a ->>> b}")
    println(s"b ->>> c = ${b ->>> c}")
    println(s"c ->>> a = ${c ->>> a}")
  }

  def stringContextExample():Unit = {
    //当使用s"",f"",raw""的形式调用字符串变量替换时，实际上是使用底层的StringContext的隐式类。
    //既，s"a $thing" = StringContext("a ", " ").s(thing)。由此可以自定义变量替换规则
    object MyContext {
      implicit final class MyStringContext(val sc: StringContext) {
        def ps(v: Any*): String = sc.parts + v.toString()
      }
    }

    import MyContext._
    val s1 = "Dog"
    val s2 = "He"
    println(ps"Dog $s1")
    println(ps"He $s2")
  }
}
