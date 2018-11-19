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
}
