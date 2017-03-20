# jgrafzahl

a java implementation of the [Graf Zahl](https://github.com/mattf/grafzahl).

## Quick start

1. Build application jar
   ```bash
   mvn package
   ```

1. Get a copy of Apache Spark (requires 2.1.0+)
   ```bash
   mkdir spark
   curl https://www.apache.org/dist/spark/spark-2.1.0/spark-2.1.0-bin-hadoop2.7.tgz | tar zx -C spark --strip-components=1
   ```

1. [Setup Apache Kafka](https://kafka.apache.org/documentation.html#quickstart)

1. Run the app
   ```bash
   spark-submit --class io.radanalytics.jgrafzahl.App \
                --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.1.0,com.sparkjava:spark-core:2.5.5 \
                target/jgrafzahl-1.0-SNAPSHOT.jar
   ```

1. [Connect to the app](http://127.0.0.1:8080)

1. Publish some words to topic `word-fountain`

## OpenShift start

This application is meant to be used as a Java based drop-in replacement for
the python [Graf Zahl](https://github.com/mattf/grafzahl) application. It can
be used in conjunction with the
[radanalytics.io Graf Zahl tutorial](http://radanalytics.io/applications/grafzahl)
with two changes.

1. Install the Java s2i deployment template
   ```bash
   oc create -f https://raw.githubusercontent.com/radanalyticsio/oshinko-s2i/master/java/javabuilddc.json
   ```

2. Change the command to install the application
   ```bash
   oc new-app --template=oshinko-java-spark-build-dc \
              -p APPLICATION_NAME=jgrafzahl \
              -p GIT_URI=https://github.com/elmiko/jgrazfahl \
              -p APP_ARGS='apache-kafka:9092 word-fountain' \
              -p SPARK_OPTIONS='--packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.1.0,com.sparkjava:spark-core:2.5.5'
   ```

