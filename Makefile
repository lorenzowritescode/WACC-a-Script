# Sample Makefile for the WACC Compiler lab: edit this to build your own comiler
# Locations

ANTLR_DIR	:= antlr
JS_ANTLR_DIR:= src/extension/jsparser
SOURCE_DIR	:= src
OUTPUT_DIR	:= bin 

# Tools

ANTLR	:= antlrBuild
FIND	:= find
RM	:= rm -rf
MKDIR	:= mkdir -p
JAVA	:= java
JAVAC	:= javac
DEPENDENCIES := lib/xstream-1.4.7.jar:lib/antlr-4.4-complete.jar:lib/commons-cli-1.2.jar

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp $(DEPENDENCIES)

# the make rules

all: rules

# runs the antlr build script then attempts to compile all .java files within src
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR) 
	cd $(JS_ANTLR_DIR) && ./$(ANTLR)
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) rules

clean:
	$(RM) rules $(OUTPUT_DIR)
	$(RM) *.s


