class A:
  def f: Int = 0
  def g: Int = f

class B extends A:
  override inline def f = 22
  override inline def g: Int = f + 11

object Overriding extends App:
  val b: B = new B
  val a: A = b
  assert(a.f == 22)
  assert(b.f == 22)

transparent inline def choose(b: Boolean): A =
  if b then new A else new B

val obj1 = choose(true) // static type is A
val obj2 = choose(false)
