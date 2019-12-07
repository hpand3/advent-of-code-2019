package example

import java.io.ByteArrayInputStream

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class SpaceshipComputerTest extends AnyFlatSpec{
  it should "process single add opcode" in {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("testsingleaddopcode.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    val output = spaceshipComputer.processOpcode(storage).mkString(",")
    assert(output == "2,0,0,0,99")
  }

  it should "process single multiply opcode" in {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("testsinglemultiplyopcode.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    val output = spaceshipComputer.processOpcode(storage).mkString(",")
    assert(output == "2,3,0,6,99")
  }

  it should "process multiple opcodes" in {
    val spaceshipComputer = new SpaceshipComputer

    val opcodeFile = Source.fromResource("testmultipleopcodes.csv")
    val opcodeStr = opcodeFile.getLines().toArray
    val storage = opcodeStr(0).split(",").map(_.toInt)

    val output = spaceshipComputer.processOpcode(storage).mkString(",")
    assert(output == "3500,9,10,70,2,3,11,0,99,30,40,50")
  }

  it should "process read and output opcode" in {
    val spaceshipComputer = new SpaceshipComputer with MockStream
    val inputValue = "12"
    spaceshipComputer.read = inputValue

    val storage = "3,0,4,0,99".split(",").map(_.toInt)

    spaceshipComputer.processOpcode(storage)

    assert(spaceshipComputer.printOutput contains inputValue)
  }
}
