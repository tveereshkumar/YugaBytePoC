git checkout master
git pull origin master
mvn clean install
java -cp "target/YugabytePoC-1.0-SNAPSHOT.jar:target/lib/*" com.yugabyte.poc.YugaBytePoCApplication