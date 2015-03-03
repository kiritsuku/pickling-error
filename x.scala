import java.io.File
import sbt.serialization._

final case class Attributed[T](data: T)
object Attributed {

  implicit def unpickler[T: Unpickler](implicit ttag: FastTypeTag[Attributed[T]]): Unpickler[Attributed[T]] = new Unpickler[Attributed[T]] {

    override val tag = ttag

    override def unpickle(tag: String, reader: PReader): Any = {
      val r = reader.readField("data")
      val a = implicitly[Unpickler[T]].unpickleEntry(r).asInstanceOf[T]
      Attributed(a)
    }
  }

  implicit def pickler[T: Pickler](implicit ttag: FastTypeTag[Attributed[T]]): Pickler[Attributed[T]] = new Pickler[Attributed[T]] {

    override val tag = ttag

    override def pickle(a: Attributed[T], builder: PBuilder): Unit = {
      builder.hintTag(tag)

      builder.beginEntry(a)
      builder.putField("data", { b ⇒
        val p = implicitly[Pickler[T]]
        b.hintTag(p.tag)
        b.hintStaticallyElidedType()
        p.pickle(a.data, b)
      })
      builder.endEntry()
    }
  }
}
