package example

import scala.io.Source
import scala.util.control.Breaks

object Problem1 {
  def main(args: Array[String]) {
    val spaceship = new Spaceship("componentmasses.txt")
    println(spaceship.getFuel)
  }
}

object Problem2 {
  def main(args: Array[String]) {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("opcodes.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    println(spaceshipComputer.processOpcode(storage)(0))
  }
}

object Problem2_2 {

  def main(args: Array[String]): Unit = {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("opcodes.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    val loop = new Breaks
    var noun = 0
    var verb = 0

    loop.breakable({
      for (n <- 0 until 100) {
        for (v <- 0 until 100) {
          noun = n
          verb = v
          storage(1) = noun
          storage(2) = verb
          val output = spaceshipComputer.processOpcode(storage.clone())
          if (output(0) == 19690720) {
            loop.break()
          }
        }
      }
    })
    println(s"noun: $noun, verb: $verb")
    val answer = 100 * noun + verb
    println(s"Answer: $answer")
  }
}
