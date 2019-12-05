package example

import org.scalatest.flatspec.AnyFlatSpec

class PasswordCriteriaTest extends AnyFlatSpec {
  it should "only accept 6 digit password" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.isSixDigits("121423"))
  }

  it should "not accept 7 digit password" in {
    val passwordCriteria = new PasswordCriteria
    assert(!passwordCriteria.isSixDigits("1214237"))
  }

  it should "not accept 6 character alphanumeric password" in {
    val passwordCriteria = new PasswordCriteria
    assert(!passwordCriteria.isSixDigits("12142A"))
  }

  it should "not accept when two adjacent digits don't exist" in {
    val passwordCriteria = new PasswordCriteria
    assert(!passwordCriteria.hasAdjacentDigits("123456"))
  }

  it should "accept when two adjacent digits exist" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasAdjacentDigits("122456"))
  }
}
