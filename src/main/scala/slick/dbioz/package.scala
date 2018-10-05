package slick

import scalaz.MonadError
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

package object dbioz {
  implicit def dbioMonadError(implicit executionContext: ExecutionContext): MonadError[DBIO, Throwable] = new MonadError[DBIO, Throwable] {
    override def point[A](a: => A): DBIO[A] = DBIO.successful(a)
    override def bind[A, B](fa: DBIO[A])(f: A => DBIO[B]): DBIO[B] = fa flatMap f
    override def raiseError[A](e: Throwable): DBIO[A] = DBIO.failed(e)
    override def handleError[A](fa: DBIO[A])(f: Throwable => DBIO[A]): DBIO[A] = fa.asTry.flatMap {
      case scala.util.Success(a) => point(a)
      case scala.util.Failure(e) => f(e)
    }
  }
}
