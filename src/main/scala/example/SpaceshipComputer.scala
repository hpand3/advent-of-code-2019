package example

class SpaceshipComputer extends ConsoleStream {

  def processOpcode(storage: Array[Int]): Array[Int] = {

    var instructionStart = 0
    var continue = true

    while (instructionStart < storage.length && continue) {
      val instructionRaw = storage(instructionStart)
      val instruction = "%04d".format(instructionRaw)
      val opcode = instruction.takeRight(2).toInt

      if (opcode == 99) {
        continue = false
      } else {
        opcode match {
          case 1 =>
            var parameterMode1 = 0
            var parameterMode2 = 0
            if (instruction.length > 2) {
              parameterMode1 = instruction.charAt(1).asDigit
              parameterMode2 = instruction.charAt(0).asDigit
            }

            var firstMem = 0
            if (parameterMode1 == 0) {
              val location = storage(instructionStart + 1)
              firstMem = storage(location)
            } else if (parameterMode1 == 1) {
              firstMem = storage(instructionStart + 1)
            }

            var secondMem = 0
            if (parameterMode2 == 0) {
              val location = storage(instructionStart + 2)
              secondMem = storage(location)
            } else if (parameterMode2 == 1) {
              secondMem = storage(instructionStart + 2)
            }

            val storageLocation = storage(instructionStart + 3)
            storage(storageLocation) = firstMem + secondMem
            instructionStart += 4
          case 2 =>
            var parameterMode1 = 0
            var parameterMode2 = 0
            if (instruction.length > 2) {
              parameterMode1 = instruction.charAt(1).asDigit
              parameterMode2 = instruction.charAt(0).asDigit
            }

            var firstMem = 0
            if (parameterMode1 == 0) {
              val location = storage(instructionStart + 1)
              firstMem = storage(location)
            } else if (parameterMode1 == 1) {
              firstMem = storage(instructionStart + 1)
            }

            var secondMem = 0
            if (parameterMode2 == 0) {
              val location = storage(instructionStart + 2)
              secondMem = storage(location)
            } else if (parameterMode2 == 1) {
              secondMem = storage(instructionStart + 2)
            }

            val storageLocation = storage(instructionStart + 3)
            storage(storageLocation) = firstMem * secondMem
            instructionStart += 4
          case 3 =>
            val storageLocation = storage(instructionStart + 1)
            storage(storageLocation) = readLine("Enter int").toInt
            instructionStart += 2
          case 4 =>
            var parameterMode1 = 0
            if (instruction.length > 2) {
              parameterMode1 = instruction.charAt(1).asDigit
            }

            var outputValue = 0
            if (parameterMode1 == 0) {
              val location = storage(instructionStart + 1)
              outputValue = storage(location)
            } else if (parameterMode1 == 1) {
              outputValue = storage(instructionStart + 1)
            }

            print(outputValue.toString)
            instructionStart += 2
        }
      }

    }
    storage
  }
}
