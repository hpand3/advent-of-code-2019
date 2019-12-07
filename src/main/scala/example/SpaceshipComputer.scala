package example

class SpaceshipComputer extends ConsoleStream {

  def processOpcode(storage: Array[Int]): Array[Int] = {

    var opcodeStart = 0
    var continue = true

    while (opcodeStart < storage.length && continue) {
      val opcode = storage(opcodeStart)

      if (opcode == 99) {
        continue = false
      } else {
        opcode match {
          case 1 =>
            val firstMem = storage(storage(opcodeStart + 1))
            val secondMem = storage(storage(opcodeStart + 2))
            val storageLocation = storage(opcodeStart + 3)
            storage(storageLocation) = firstMem + secondMem
            opcodeStart += 4
          case 2 =>
            val firstMem = storage(storage(opcodeStart + 1))
            val secondMem = storage(storage(opcodeStart + 2))
            val storageLocation = storage(opcodeStart + 3)
            storage(storageLocation) = firstMem * secondMem
            opcodeStart += 4
          case 3 =>
            val storageLocation = storage(opcodeStart + 1)
            storage(storageLocation) = readLine("Enter int").toInt
            opcodeStart += 2
          case 4 =>
            val storageLocation = storage(opcodeStart + 1)
            print(storage(storageLocation).toString)
            opcodeStart += 2
        }
      }

    }
    storage
  }
}
