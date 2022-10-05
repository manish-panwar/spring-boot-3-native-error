.DEFAULT_GOAL := build
.PHONY: clean build docker
ARGS=

ifeq ($(OS),Windows_NT)
    WINDOWS_OS := true
endif

help: ##@Miscellaneous Show this help
	@echo "build               : It will build code and execute unit & integration tests."
	@echo "build-and-skip-test : It will build code and skip running any tests."
	@echo "unit-test           : It will only run the unit tests."
	@echo "integration-test    : It will only run the integration tests."
	@echo "run-bg-tests        : It will blue-green tests."
	@echo "docker              : Building code, execute unit and integration tests, and then generate docker image."
	@echo "deploy-docker       : Deploy all Replication Proxy containers along with any dependencies via docker-compose."

build:
	./gradlew clean build ${ARGS}

native:
	./gradlew nativeCompile

test:
	./gradlew nativeTest