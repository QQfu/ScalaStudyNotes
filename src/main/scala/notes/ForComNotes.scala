package notes

class ForComNotes {
  //使用Option过滤unexpected null value
  def optionExample():Unit = {
    val v1:Seq[Option[Int]] = Seq(Some(1),None,Some(100))
    val v2 = for {Some(v) <- v1}
      yield v*100
    v2 foreach println
  }

  def eitherExample():Unit = {
    //Either可以用来同时存放两种数据类型的数据
    def skip7(l:Int):Either[Int,String] = {
      if (l%7 == 0 || l.toString.contains("7"))
        Right("Pass")
      else Left(l)
    }
    for {
      i <- 1 to 20
      result = skip7(i)
      str = if (result.isLeft) result.left.get
      else result.right.get
    } println(str)
  }

  def tryExample():Unit = {
    //Scala可以用try来模拟try/catch的操作
    import scala.util.{Try, Success, Failure}
    def verifyPositive(i:Int):Try[Int] = {
      if (i>0) Success(i)
      else Failure(new AssertionError(s"$i is not positive."))
    }

    val verify = for {
      x <- -10 to 10
    } yield verifyPositive(x)

    var result = 0
    var amount:List[Int] = List()
    for (i <- verify) {
      if (i.isSuccess) {
        result = i.get
      }
      else result = 0
      amount = amount:+result
    }

    val message = verify.zip(amount)
    message foreach println
  }
}
