# jgrafzahl

a java implementation of the [Graf Zahl](https://github.com/mattf/grafzahl).

## OpenShift quickstart

This application is meant to be used as a Java based drop-in replacement for
the python [Graf Zahl](https://github.com/mattf/grafzahl) application. It can
be used in conjunction with the
[radanalytics.io Graf Zahl tutorial](http://radanalytics.io/applications/grafzahl)
with one change.

1. Change the command to install the application
   ```bash
   oc new-app --template=oshinko-java-spark-build-dc \
              -p APPLICATION_NAME=jgrafzahl \
              -p GIT_URI=https://github.com/elmiko/jgrafzahl \
              -p APP_MAIN_CLASS=io.radanalytics.jgrafzahl.App \
              -p APP_ARGS='apache-kafka:9092 word-fountain' \
              -p SPARK_OPTIONS='--packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.3.0,com.sparkjava:spark-core:2.5.5,org.glassfish:javax.json:1.0.4  --conf spark.jars.ivy=/tmp/.ivy2'
   ```

