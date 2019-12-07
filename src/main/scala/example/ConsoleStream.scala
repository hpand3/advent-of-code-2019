package example

trait ConsoleStream {
  def print(s: String): Unit = Console.println(s)
  def readLine(s: String): String = readLine(s)
}
