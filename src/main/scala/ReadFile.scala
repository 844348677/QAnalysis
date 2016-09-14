import java.io.File
import breeze.plot._

import scala.collection.mutable.ListBuffer
import scala.io.Source


/**
  * Created by liuh on 2016/9/12.
  */
object ReadFile {

  def main(args: Array[String]) {
    println("readfile")
    val fileDir = "Q:\\data\\CZC-1-0\\SR.CZC\\"
    val filename = fileDir + "20110901.csv"
    val fileList = Source.fromFile(filename).getLines().toList //读文件构造list
    println(fileList.length)
    println(fileList.head)

    val dataList = for(i <- fileList) yield i.split(",") //去掉逗号构造List
    println(dataList.getClass)
    println(dataList.head(20).toInt/10000)

    //判断文件在不在
    println(fileExist(fileDir+"a.csv"))
    println(dirExist("Q:\\a"))

    /*
    //f.drawPlots()
    val fileDir2 = "Q:\\data\\CZC-1-0\\MA.CZC\\"
    val iter = subdir(new File(fileDir2))
    println(iter.size)
    val xDay = new ListBuffer[Int]
    val yValue = new ListBuffer[Int]
    var index = 0

    for(i <- iter){
      val fileList = Source.fromFile(i).getLines().toList
      val dataList = for(i <- fileList) yield i.split(",") //去掉逗号构造List
      println(i +" "+dataList.head(20).toInt/10000)
      index = index + 1
      xDay.append(index)
      yValue.append(dataList.head(20).toInt/10000)
    }
    */

    val xDay = new ListBuffer[Int]
    val yValue = new ListBuffer[Int]
    var index = 0
    for(i <- fileList){
      val data = i.split(",")

        index = index + 1
        xDay.append(index)
        yValue.append(data(4).toInt/10000)

    }


    val f = Figure()
    val p = f.subplot(0)
    val x = List(1,2,3)
    val y = List(2,3,5)
    val y2 = List(3,4,7)
    //p += plot(x,y)
    //p += plot(x,y2)

    p += plot(xDay.toList,yValue.toList)

    f.saveas("Q:\\a.png")


      //println(i.toString)
  }

  def fileExist(fileName:String):Boolean={
    val f = new File(fileName)
    if(f.exists())
      return true
    else
      return false
  }
  def dirExist(dirName:String):Boolean={ //这个好像要修改，有点头大
    val f = new File(dirName)
    if(!f.exists() && f.isDirectory)
      return false //不存在这个文件并且也不是目录，就返回false  意思就是不存在这个文件夹
    else
      return true
  }

  def subdir(dir:File):Iterable[File] = { //遍历目录下面的文件
    val d = dir.listFiles.filter(_.isDirectory)
    val f = dir.listFiles.filter(_.isFile).toIterable
    f ++ d.toIterator.flatMap(subdir _)
  }
}
