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

  it should "not accept if a following digit is lower than the previous one" in {
    val passwordCriteria = new PasswordCriteria
    assert(!passwordCriteria.hasIncreasingDigits("123996"))
  }

  it should "accept if a following digit if is greater than the previous one" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasIncreasingDigits("123456"))
  }

  it should "apply all the password criterias" in {
    val passwordCriteria = new PasswordCriteria

    // Increasing digits
    assert(passwordCriteria.validPassword("112345"))
    assert(!passwordCriteria.validPassword("112365"))

    // Two adjacent digits
    assert(passwordCriteria.validPassword("112346"))
    assert(!passwordCriteria.validPassword("123457"))

    // Alphanumeric characters
    assert(passwordCriteria.validPassword("112346"))
    assert(!passwordCriteria.validPassword("11234A"))
  }
}
