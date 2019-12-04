package example

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
}
