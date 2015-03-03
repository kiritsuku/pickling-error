import java.io.File
import scala.pickling._
import sbt.serialization.pickler.JavaExtraPicklers

object picklers extends JavaExtraPicklers

final case class Attributed[T](data: T)
object Attributed {

  implicit def unpickler[T: Unpickler]: Unpickler[Attributed[T]] = new Unpickler[Attributed[T]] {

    override val tag = implicitly[FastTypeTag[Attributed[T]]]

    override def unpickle(tag: String, reader: PReader): Any = {
//      reader.hintTag(this.tag)
//      reader.hintStaticallyElidedType()
//      reader.pinHints()

      val r = reader.readField("data")
      val a = implicitly[Unpickler[T]].unpickleEntry(r).asInstanceOf[T]

//      reader.unpinHints()
      Attributed(a)
    }
  }

  implicit def pickler[T: Pickler]: Pickler[Attributed[T]] = new Pickler[Attributed[T]] {

    override val tag = implicitly[FastTypeTag[Attributed[T]]]

    override def pickle(a: Attributed[T], builder: PBuilder): Unit = {
      builder.hintTag(tag)
      builder.hintStaticallyElidedType()

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
