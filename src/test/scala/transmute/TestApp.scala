package transmute

case class Person(name: String, age: Int)
case class User(name: String, age: Int) //, email: String)

object TestApp extends App {

  val p = Person("John", 30)

//  p.into[User].transform

  //  p.transform

}
