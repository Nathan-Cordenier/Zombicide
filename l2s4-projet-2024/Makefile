# Variables
JAVAC = javac
JAVA = java
JAR = jar
JAVADOC = javadoc

SRC_DIR = src
TEST_DIR = test
DOCS_DIR = docs

MAIN_PACKAGE = zombicide
MAIN_CLASS = $(MAIN_PACKAGE).Main

JAVA_SOURCES = $(wildcard $(SRC_DIR)/*.java $(SRC_DIR)/$(MAIN_PACKAGE)/*.java)
CLASSES_DIR = classes

JAR_NAME = livrable3.jar

# Targets
.PHONY: all clean compile jar test javadoc run-test run

# Default target
all: compile jar test javadoc run-test run

# Compile Java source files
compile: $(JAVA_SOURCES)
	$(JAVAC) -sourcepath $(SRC_DIR) -d $(CLASSES_DIR) $^

# Run the livrable2 Java application
run:
	$(JAVA) -cp $(CLASSES_DIR) $(MAIN_CLASS) $(ARGS)

# Compile tests
test: compile
	$(JAVAC) -cp junit-console.jar:$(CLASSES_DIR) $(TEST_DIR)/$(MAIN_PACKAGE)/*.java

# Run tests
run-test: test
	$(JAVA) -jar junit-console.jar -cp $(TEST_DIR):$(CLASSES_DIR)

# Generate Java documentation
javadoc:
	$(JAVADOC) -sourcepath $(SRC_DIR) -d $(DOCS_DIR) -subpackages $(MAIN_PACKAGE)

# Create a JAR file
jar: compile
	$(JAR) cvfe $(JAR_NAME) $(MAIN_CLASS) -C $(CLASSES_DIR) .

run-jar: jar
	$(JAVA) -jar $(JAR_NAME) $(ARGS)

# Clean up generated files
clean:
	rm -rf $(CLASSES_DIR) $(DOCS_DIR) $(JAR_NAME)

# Dependency rules
$(CLASSES_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) -d $(CLASSES_DIR) $<
