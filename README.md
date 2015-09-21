sbt-ynolub
==========

setup
-----

### setup

For sbt 0.13.6+ add sbt-ynolub as a dependency in `project/ynolub.sbt`:

```scala
addSbtPlugin("com.eed3si9n" % "sbt-ynolub" % "0.1.0")
```

### what this does

This plugin will switch your Scala to a hacked version of 2.11 that implements `-Yno-lub` flag.
It's an experimenal flag to turn of lubbing duing the type inference.

Plain Scala:

```scala
scala> if (true) Right(1) else Left(false)
res0: Product with Serializable with scala.util.Either[Boolean,Int] = Right(1)

scala> 1 match { case 1 => Array(1); case n => Vector(n) }
res1: java.io.Serializable = Array(1)

scala> List(1, false)
res2: List[AnyVal] = List(1, false)
```

With this plugin:

```
scala> if (true) Right(1) else Left(false)
<console>:12: error: same types expected: scala.util.Right[Nothing,Int] and scala.util.Left[Boolean,Nothing]
       if (true) Right(1) else Left(false)
       ^

scala> 1 match { case 1 => Array(1); case n => Vector(n) }
<console>:12: error: same types expected: Array[Int] and scala.collection.immutable.Vector[Int]
       1 match { case 1 => Array(1); case n => Vector(n) }

scala> List(1, false)
<console>:12: error: same types expected: Int and Boolean
       List(1, false)
           ^
```

### how is this possible?

This plugin just adds the following settings:

```scala
scalaOrganization := "com.eed3si9n"
scalaVersion := "2.11.8-X1"
scalacOptions += "-Yno-lub"
resolvers += Resolver.bintrayRepo("eed3si9n", "maven")
```
