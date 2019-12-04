package example

import scala.io.{BufferedSource, Source}

class SpaceshipComputer {

  def processOpcode(storage: Array[Int]): Array[Int] = {

    var opcodeStart = 0;
    var continue = true

    while (opcodeStart < storage.length && continue) {
      val opcode = storage(opcodeStart)

      if (opcode == 99) {
        continue = false
      } else {
        val firstMem = storage(storage(opcodeStart + 1))
        val secondMem = storage(storage(opcodeStart + 2))
        val storageLocation = storage(opcodeStart + 3)
        if (opcode == 1) {
          storage(storageLocation) = firstMem + secondMem
        } else if (opcode == 2) {
          storage(storageLocation) = firstMem * secondMem
        }
      }
      opcodeStart += 4
    }
    storage
  }
}
