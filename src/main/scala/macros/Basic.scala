package macros

object Basic {}
import scala.quoted.*

inline def assert(inline expr: Boolean): Unit =
  ${ assertImpl('expr) }

def assertImpl(expr: Expr[Boolean])(using Quotes): Expr[Unit] = '{
  if ! $expr then
    throw AssertionError(s"failed assertion: ${${ showExpr(expr) }}")
}

def showExpr(expr: Expr[Boolean])(using Quotes): Expr[String] =
  '{ ??? }

def to[T: Type, R: Type](f: Expr[T] => Expr[R])(using Quotes): Expr[T => R] =
  '{ (x: T) => ${ f('x) } }

def toLifted[T, R](f: Expr[T] => Expr[R])(using t: Type[T], r: Type[R])(using
    Quotes
): Expr[T => R] =
  '{ (x: t.Underlying) => ${ f('x) } }

def from[T: Type, R: Type](f: Expr[T => R])(using
    Quotes
): Expr[T] => Expr[R] =
  (x: Expr[T]) => '{ $f($x) }
