.DEFAULT_GOAL := build
.PHONY: build

build:
	./gradlew clean build ${ARGS}

native:
	./gradlew nativeCompile

test:
	./gradlew nativeTest