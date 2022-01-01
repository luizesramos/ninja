binDir = bin
jarDir = publish
jarName = Game.jar
manifest = $(jarDir)/META-INF/MANIFEST.MF

all: build run

build: clean
	mkdir -p $(binDir)
	javac Game.java -d $(binDir)

clean:
	rm -f $(binDir)/*.class

run:
	java -cp $(binDir) Game


# jar: build
# 	mkdir -p $(jarDir)/META-INF
# 	jar -cf $(jarDir)/$(jarName) $(binDir)/*.class res/*
# 	touch $(manifest)
# 	echo "Main-Class: Game" > $(manifest)

# runJar:
# 	java -jar $(jarDir)
