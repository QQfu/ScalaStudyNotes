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
}
