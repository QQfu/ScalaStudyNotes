package notes

class FuncProNotes {
  def basicExample():Unit = {
    //Scala支持高阶函数，或者将函数视为一个变量。
    def f1(i1:Int):Int = i1*2
    def f2(i2:Int)(f:Int=>Int):Int = f(i2)

    val x:Int = 10
    println("f1(x) = " + f1(x))
    println("f2(x)(f1) = " + f2(x)(f1)) //f1作为一个参数传给f2
  }

  def partialApplyExample():Unit = {
    //Scala支持partial applied function
    def f3(s1:String)(s2:String):String = s1 + ", " + s2
    def f4 = f3("He He")_
    println("""f3("Ha Ha")("Hei Hei") = """ + f3("Ha Ha")("Hei Hei"))
    println("""f4("Hei Hei") = """ + f4("Hei Hei"))

    //也可以用另一种形式表达f3
    def f5: String => String => String = (s1:String) => (s2:String) => s1 + ", " + s2
    println("""f5("Xi Xi")("Hei Hei") = """ + f5("Xi Xi")("Hei Hei"))
  }
}
