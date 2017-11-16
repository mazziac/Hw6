JFLAGS = -g                                                                     
JC = javac
SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        CirBuffer.java \
        Cons.java \
		Prod.java \
		HW6.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

