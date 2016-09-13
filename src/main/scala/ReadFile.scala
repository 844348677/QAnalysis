import scala.io.Source

/**
  * Created by liuh on 2016/9/12.
  */
object ReadFile {

  def main(args: Array[String]) {
    println("readfile")
    val filename = "Q:\\data\\CZC-1-0\\CF.CZC\\" + "20110901.csv"
    val fileList = Source.fromFile(filename).getLines().toList
    println(fileList.length)
    println(fileList.head)


  }
}
