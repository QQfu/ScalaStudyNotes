package notes

class BasicKnowledge {
  def basicKnowledgeExample():Unit = {
    //Scala中，单参数的方法调用时可以直接跟参数而不加任何其他的符号。所以，Scala所有的运算符实际上都是一个方法。
    println("1 + 2 = " + (1+2).toString)
    println("1.+(2) = " + 1.+(2).toString)

    //当我们定义单参数的方法的时候也可以像调用运算符一样调用这个方法。
    def oneParmMethod(i:Int): Int = { //传入一个Int，返回100倍的数字
      i*100
    }
    println("List(1,2,3,4,5) map oneParmMethod foreach println = ")
    List(1,2,3,4,5) map oneParmMethod foreach print
    println()
    println("List(1,2,3,4,5).map(oneParmMethod).foreach(println) = ")
    List(1,2,3,4,5).map(oneParmMethod).foreach(print)
    println()

    //特殊运算符，以及右向运算符
    //:: 两个冒号在集合头部添加一个元素，可以用于模式匹配
    println("List(1,2).::(3) = " + List(1,2).::(3))
    println("4::List(1,2) = " + (4::List(1,2))) //+的优先级大于::,所以要用括号把::运算包起来
    println("5::6::Nil = " + (5::6::Nil)) //Nil表示List结束
    //由于::是右向运算符，而它总把元素放到集合头部，所以5::6::Nil的顺序是5,6.

    //:+ +: 在加号的方位追加一个元素，不能用于模式匹配，总是以靠近:的元素作为基准。
    println("List(1,2):+3 = " + (List(1,2):+3))
    println("List(1,2).+:(3) = " + List(1,2).+:(3)) //List(1,2)+:3不适用，因为3不能作为基准来添加一个List。
    println("1+:2+:Nil = " + (1+:2+:Nil))
    println("Nil:+1:+2 = " + (Nil:+1:+2))

    //++ 两个加号连接两个集合
    println("List(1,2)++List(3,4) = " + (List(1,2)++List(3,4)))
    //::: 三个冒号连接两个List
    println("List(1,2):::List(3,4) = " + (List(1,2):::List(3,4)))
  }

  def loopExample():Unit = {
    //Scala中for循环用<-做迭代标识
    val exampleList = List(1,2,3,4)
    for (x <- exampleList) {
      print(x + " ")
    }
    println()

    //只有一行执行代码的时候可以省略方括号
    for (x <- exampleList) print(x + " ")
    println()

    //可以用方括号代替for循环的圆括号变成for comprehension
    for {x <- exampleList} print(x + " ")
    println()

    //可以在comprehension中加过滤条件
    for { x <- exampleList
      if x > 2
    } print(x + " ")
    println()

    //可以在comprehension中加多个条件
    for { x <- exampleList
      if x > 1 && x < 4
    } print(x + " ")
    println()

    //可以用yield赋值,output会变成一个List
    val px = for { x <- exampleList
      if x > 1
    } yield x
    println(px)

    //while循环， do-while循环与Java一致
    var i = 0
    while (i < 4) {
      i += 1
      print(i + " ")
    }
    println()
    println("i = " + i)

    do {
      i -= 1
      print(i + " ")
    } while (i != 0)
    println()
    println("i = " + i)
  }

  def enumerationExample():Unit = {
    //Scala的Enumeration需要继承创建
    object WeekDay1 extends Enumeration {
      //Enumeration中，Value既是一个type也是一个method
      type WeekDay1 = Value    //建立WeekDay1到Value的alias
      val Monday: WeekDay1.Value = Value("The first day.")  //调用Value方法建立一个枚举变量
      val Tuesday: WeekDay1.Value = Value("The second day.")
      val Wednesday: WeekDay1.Value = Value("The third day.")
      val Thursday: WeekDay1.Value = Value("The forth day.")
      val Friday: WeekDay1.Value = Value("The fifth day.")
      val Saturday: WeekDay1.Value = Value("The sixth day.")
      val Sunday: WeekDay1.Value = Value("The seventh day.")
    }

    for (day <- WeekDay1.values) println(s"${day.id}\t$day")

    //Enumeration也可以直接省略Value的参数用变量名代替说明。
    object WeekDay2 extends Enumeration {
      type WeekDay2 = Value
      val Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday = Value
    }

    WeekDay2.values foreach println
  }

  def stringFormatExample():Unit = {
    //Scala可以对字符串做替换操作和格式化操作，在字符串前加s做替换操作，加f做格式化操作。
    val v1:Int = 5
    val f1:Float = 3.2f
    val s1:String = "Baby"
    //Output = "Hello, Baby!"
    println(s"Hello, $s1!") //用$做替换锚点，后接{}内容被替换，如果仅为一个变量可以省略{}。
    //Output = "I want $3.2!"
    println(s"I want $$$f1!") //用$$表示原生的dollar符。
    //Output = "I got 3.20, but I want 05."
    println(f"I got $f1%.2f, but I want $v1%02d.") //用%做格式化锚点，小数点前后的数字表示前后精确的位数。
    //Output = "The percent is 5%."
    println(f"The percent is $v1%%.") //用%%表示原生的百分号符。
    //Output = "Baby, 05 is not what I want!"
    println("%s, %02d is not what I want!".format(s1,v1)) //也可以用format函数来做格式化，此时%为格式化锚点和替换锚点。
  }

  def traitExample():Unit = {
    //Scala没有Interface，Scale用Trait来实现Interface的功能
    trait BaseInterface {
      def alert(msg:String):Unit
    }
    class BaseClass1 extends BaseInterface {
      def alert(msg:String):Unit = {
        println(s"Alert: $msg")
      }

      def info(infomsg:String):Unit = {
        println(s"Info: $infomsg")
      }
    }
    new BaseClass1().alert("This is a message.")

    //像Java一样，Scala支持同时继承类和实现接口。
    trait BaseInterface2 {
      def alert2(msg:String):Unit
    }

    class  BaseClass2 extends BaseClass1 with BaseInterface2 {
      override def alert(msg: String): Unit = {
        println(s"Alert in BaseClass2: $msg")
      }

      override def alert2(msg: String): Unit = {
        println(s"Alert2: $msg")
      }
    }

    val exampleClass = new BaseClass2()
    //alert被重载了，此时会用到Alert in BaseClass2.
    exampleClass.alert("Message.")
    //alert2继承自BaseInterface2
    exampleClass.alert2("Message2.")
    //info继承自父类BaseClass1
    exampleClass.info("Information message.")

    //Scala支持接口的多重继承
    class multiClass extends BaseInterface with BaseInterface2 {
      def alert(msg: String): Unit = {
        println(s"From first trait: $msg")
      }

      def alert2(msg: String): Unit = {
        println(s"From second trait: $msg")
      }

      def alert3(msg:String):Unit = {
        println(s"From self class: $msg")
      }
    }

    val multiClassExample = new multiClass()
    multiClassExample.alert("Message1.")
    multiClassExample.alert2("Message2.")
    multiClassExample.alert3("Message3.")
  }
}
