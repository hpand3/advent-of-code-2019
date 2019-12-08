package example

import scala.io.StdIn

trait ConsoleStream {
  def print(s: String): Unit = Console.print(s)
  def readLine(s: String): String = StdIn.readLine(s)
}
