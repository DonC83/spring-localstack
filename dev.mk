### Dependencies

## Install dependencies
deps.install:
	./gradlew dependencies
.PHONY: deps.install

## Update dependencies
deps.update:
	./gradlew dependencies --refresh-dependencies
.PHONY: deps.update

### Application - Build, clean & run

## Compile the classes of the project
build:
	./gradlew classes
.PHONY: build

## Clean project build artifacts
clean:
	./gradlew clean
.PHONY: clean

## Run the application locally
run:
	./gradlew bootRun
.PHONY: run

## Create an executable jar of the application
package:
	./gradlew bootJar
.PHONY: package

### Application - Tests

## Execute the unit tests
test.unit:
	./gradlew test
.PHONY: test.unit

### Verify - Code verification and static analysis

## Run static code analysis
verify:
	./gradlew detekt
.PHONY: verify

## Run static analysis and apply fixes if possible
verify.fix:
	./gradlew detekt --auto-correct
.PHONY: verify.fix

### Devstack

## Start the devstack
devstack.start:
	lima nerdctl compose -f ./devstack/docker-compose.yml up -d
.PHONY: devstack.start

## Stop the devstack
devstack.stop:
	lima nerdctl compose -f ./devstack/docker-compose.yml down
.PHONY: devstack.stop

## Show the devstack's status
devstack.status:
	lima nerdctl compose -f ./devstack/docker-compose.yml ps
.PHONY: devstack.status
