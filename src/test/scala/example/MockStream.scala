package example

trait MockStream extends ConsoleStream {
  var printOutput: Seq[String] = Seq()
  var read: String = ""

  override def print(s: String): Unit = printOutput = printOutput :+ s
  override def readLine(s: String): String = read
}
