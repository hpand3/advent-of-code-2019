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

  it should "accept when two or more adjacent digits in a group exist with other adjacent digits 1" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasAdjacentDigits("112233"))
  }

  it should "accept when two or more adjacent digits in a group exist with other adjacent digits 2" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasAdjacentDigits("112234"))
  }

  it should "accept when two or more adjacent digits in a group exist with other adjacent digits 3" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasAdjacentDigits("123455"))
  }

  it should "not accept when two or more adjacent digits exist as a group of three" in {
    val passwordCriteria = new PasswordCriteria
    assert(!passwordCriteria.hasAdjacentDigits("123444"))
  }

  it should "accept when two or more adjacent digits exist with other doubles" in {
    val passwordCriteria = new PasswordCriteria
    assert(passwordCriteria.hasAdjacentDigits("111122"))
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

    assert(passwordCriteria.validPassword("112233"))
    assert(passwordCriteria.validPassword("111122"))
    assert(!passwordCriteria.validPassword("123444"))
    assert(!passwordCriteria.validPassword("123456"))
    assert(!passwordCriteria.validPassword("11224A"))
  }

  it should "find the number of passwords that fit the criteria" in {
    val start = 193651
    val end = 649729

    val passwordCriteria = new PasswordCriteria
    val validPasswords = passwordCriteria.getPossibleCombinations(start, end)
    assert(validPasswords.length == 1102)
  }
}
