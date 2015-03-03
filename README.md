Unpickler doesn't work:

```
% sbt                                                                                                                                                       ??
[info] Loading global plugins from /home/antoras/.sbt/0.13/plugins
[info] Loading project definition from /home/antoras/dev/runtime-EclipseApplicationwithEquinoxWeaving/pickling-error/project
[info] Set current project to pickling-error (in build file:/home/antoras/dev/runtime-EclipseApplicationwithEquinoxWeaving/pickling-error/)
> console
[info] Starting scala interpreter...
[info]
import scala.pickling.Defaults._
import scala.pickling.json._
import java.io.File
import picklers._
att: Attributed[java.io.File] = Attributed(/a/b/c)
Welcome to Scala version 2.11.5 (OpenJDK 64-Bit Server VM, Java 1.8.0_31).
Type in expressions to have them evaluated.
Type :help for more information.

scala> att.pickle
res0: pickling.json.pickleFormat.PickleType =
JSONPickle({
  "data": "file:\/a\/b\/c"
})

scala> res0.unpickle[Attributed[File]]
scala.MatchError: file:/a/b/c (of class java.lang.String)
  at scala.pickling.json.JSONPickleReader$$anonfun$beginEntry$2.apply(JSONPickleFormat.scala:212)
  at scala.pickling.json.JSONPickleReader$$anonfun$beginEntry$2.apply(JSONPickleFormat.scala:203)
  at scala.pickling.PickleTools$class.withHints(Tools.scala:521)
  at scala.pickling.json.JSONPickleReader.withHints(JSONPickleFormat.scala:170)
  at scala.pickling.json.JSONPickleReader.beginEntry(JSONPickleFormat.scala:203)
  at scala.pickling.Unpickler$class.unpickleEntry(Pickler.scala:78)
  at sbt.serialization.pickler.JavaExtraPicklers$$anon$1.unpickleEntry(JavaExtraPicklers.scala:30)
  at Attributed$$anon$3.unpickle(x.scala:20)
  at scala.pickling.Unpickler$class.unpickleEntry(Pickler.scala:79)
  at Attributed$$anon$3.unpickleEntry(x.scala:10)
  at scala.pickling.functions$.unpickle(functions.scala:11)
  at scala.pickling.UnpickleOps.unpickle(Ops.scala:23)
  ... 43 elided
```
