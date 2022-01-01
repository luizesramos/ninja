mainClass = Ninja
binDir = bin
jarDir = publish
jarName = $(mainClass).jar
manifest = $(jarDir)/META-INF/MANIFEST.MF

all: build run

build: clean
	mkdir -p $(binDir)
	javac $(mainClass).java -d $(binDir)

clean:
	rm -f $(binDir)/*.class

run:
	java -cp $(binDir) $(mainClass)


# jar: build
# 	mkdir -p $(jarDir)/META-INF
# 	jar -cf $(jarDir)/$(jarName) $(binDir)/*.class res/*
# 	touch $(manifest)
# 	echo "Main-Class: $(mainClass)" > $(manifest)

# runJar:
# 	java -jar $(jarDir)
