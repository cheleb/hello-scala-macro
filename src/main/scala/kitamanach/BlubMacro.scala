package kitamanach

import scala.quoted.*

inline def blub: Unit = ${ blubImpl }

def blubImpl(using Quotes): Expr[Unit] = '{ () }
