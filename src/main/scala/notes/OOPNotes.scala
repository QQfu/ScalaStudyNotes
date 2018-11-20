package notes

import scala.util.Random

class AnyAmt(val amount:Double) extends AnyVal {
  override def toString: String = "%.2f".format(amount)
}


trait Filter7 extends Any {
  def  filter7(value:Int):Boolean = {
    if (value%7 == 0) true
    else false
  }
}

class Roll(val number: Int) extends AnyVal with Filter7 {
  override def toString: String = {
    if (filter7(number)) "Ding!" + number.toString
    else "Oops!" + number.toString
  }
}

//Scala可以自定义一元运算符,MyFraction自定义了分数类和四则运算，以及取反运算。
case class MyFraction(numerator:Int, denominator:Int) {

  //取两个数的最大公约数
  def getMaxCommonDiv(a:Int, b:Int):Int = {
    var x = 0
    var y = 0
    if (a < 0) x = -a else x = a
    if (b < 0) y = -b else y = b
    if (a < b) {
      val temp = x
      x = y
      y = temp
    }
    var r = 0
    while (y != 0) {
      r = x % y
      x = y
      y = r
    }
    x
  }

  //取反运算
  def unary_- : MyFraction = MyFraction(-numerator, denominator)

  //加法运算
  def +(other: MyFraction):MyFraction = {
    if (denominator == other.denominator) {
      val sumNun = numerator + other.numerator
      val sumDe = denominator
      val commDiv = getMaxCommonDiv(sumNun,sumDe)
      MyFraction(sumNun/commDiv,sumDe/commDiv)
    }
    else {
      val sumNun = numerator * other.denominator + other.numerator * denominator
      val sumDe = denominator * other.denominator
      val commDiv = getMaxCommonDiv(sumNun,sumDe)
      MyFraction(sumNun/commDiv,sumDe/commDiv)
    }
  }

  //减法运算
  def -(other: MyFraction):MyFraction = {
    if (denominator == other.denominator) {
      val sumNun = numerator - other.numerator
      val sumDe = denominator
      val commDiv = getMaxCommonDiv(sumNun,sumDe)
      MyFraction(sumNun/commDiv,sumDe/commDiv)
    }
    else {
      val sumNun = numerator * other.denominator - other.numerator * denominator
      val sumDe = denominator * other.denominator
      val commDiv = getMaxCommonDiv(sumNun,sumDe)
      MyFraction(sumNun/commDiv,sumDe/commDiv)
    }
  }

  //乘法运算
  def *(other:MyFraction):MyFraction = {
    val sumNun = numerator * other.numerator
    val sumDe = denominator * other.denominator
    val commDiv = getMaxCommonDiv(sumNun,sumDe)
    MyFraction(sumNun/commDiv,sumDe/commDiv)
  }

  //除法运算
  def /(other:MyFraction):MyFraction = {
    val sumNun = numerator * other.denominator
    val sumDe = denominator * other.numerator
    val commDiv = getMaxCommonDiv(sumNun,sumDe)
    MyFraction(sumNun/commDiv,sumDe/commDiv)
  }

  //重写toString
  override def toString: String = {
    s"MyFraction($numerator,$denominator)"
  }
}

class OOPNotes {
  def valueClassExample():Unit = {
    //Scala以Any作为根类型，AnyVal作为Any的一个子类，是所有值类型的父类，对应JVM的
    // int，float，double，long, short, boolean, char, byte, void
    //当需要为这些类型进行封装又想避免生成引用类型的时候可以直接继承AnyVal生成Value Class。

    println(new AnyAmt(7392.1))
  }

  def universalTraitExample():Unit = {
    //当一个Trait直接继承自Any的时候，那它就是一个universal trait。Value Class可以继承universal trait。
    //Universal trait只能定义方法，以及实现。
    println(new Roll(14))
    for (_ <- 1 to 7) {
      println(new Roll(Random.nextInt(100)))
    }
  }

  def myFractionExample():Unit = {
    val f1 = MyFraction(1,2)
    val f2 = MyFraction(1,6)
    val f3 = MyFraction(2,6)
    val f4 = MyFraction(5,6)
    val f5 = MyFraction(3,8)
    val f6 = -f5
    println(s"f1:$f1 + f2:$f2 = ${f1 + f2}")
    println(s"f2:$f2 + f3:$f3 = ${f2 + f3}")
    println(s"f4:$f4 * f5:$f5 = ${f4 * f5}")
    println(s"f1:$f1 / f2:$f2 = ${f1 / f2}")
    println(s"f2:$f2 / f1:$f1 = ${f2 / f1}")
    println(s"f6:$f6 * f5:$f4 = ${f6 * f4}")
  }
}
