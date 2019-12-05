package example

class PasswordCriteria {
  def isSixDigits(password: String): Boolean = password.length == 6 && (password forall Character.isDigit)
}
