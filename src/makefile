JC = javac
JVM = java
JOPTS = -cp ./lib/junit-4.4.jar:./lib/org.apache.commons.io.jar:./lib/hamcrest-all-1.3.jar
JEXOPTS = -cp ././.:./lib/junit-4.4.jar:./lib/hamcrest-all-1.3.jar:./lib/org.apache.commons.io.jar
MAIN = Main.Main
MAINDRIVER = Domain.Drivers.MainDriver
TEST = Domain.Tests.LZ78Test

default:
	$(JC) $(JOPTS) */*.java */*/*.java

run:
	$(JVM) $(MAIN)

runtest:
	java $(JEXOPTS) org.junit.runner.JUnitCore $(TEST)

runmaindriver:
	$(JVM) $(MAINDRIVER)

clean:
	$(RM) */*.class */*/*.class */*.form
