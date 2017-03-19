# jgrafzahl

build

```
$ mvn package
```

run with spark

```
$ spark-submit --class io.radanalytics.jgrafzahl.App \
               --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.1.0,com.sparkjava:spark-core:2.5.5 \
               target/jgrafzahl-1.0-SNAPSHOT.jar
```

the server should be listening on at `localhost:4567`

