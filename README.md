# esb-integration-tests

The base code was adopted from [here](https://github.com/cschneider/osgi-testing-example).

A video related to the code above can be found [here](https://www.youtube.com/watch?v=9WGbnfa2BaM).

## [Maven Failsafe](https://maven.apache.org/surefire/maven-failsafe-plugin/)

The `maven-failsafe-plugin` handles the execution of integration tests
(in contrast to the `maven-surefire-plugin` which is designed for unit test execution).

Looking at the parent POM you'll see that the plugin is set to trigger on the `integration-test` and `verify` goals.

When triggered, the plugin will execute tests in classes that match a specific pattern.
By default, these are:
- `**/IT*.java`
- `**/*IT.java`
- `**/*ITCase.java`

But the plugin can be configured to recognise more if needed.

**Note**: if you name a test class something like `**/*TestIT` tests may be executed twice as the filename matches both
what the `maven-surefire-plugin` and `maven-failsafe-plugin` are looking for.
So ensure the naming-conventions are discussed beforehand to avoid this.

## [Pax Exam](https://ops4j1.jira.com/wiki/spaces/PAXEXAM4)

**Warning**: the website for Pax Exam isn't in the most up-to-date state.

`pax-exam` allows us to execute integration tests within an in-memory OSGi container.
In our case we want to use Pax Exam to spin up a Karaf instance.
The container can be configured via code: bundles & bundle configuration provisioning can be specified this way.


### Pax Exam Concepts

- `framework` - the OSGi framework that runs the `system under test`.
- `system under test` - the collection of application and library bundles.
- `driver` - the entry point to Pax Exam. It evaluates configuration options and creates a `reactor` (probably JUnit).
- `container` - the wrapper around the `framework` and it can be used to communicate with the `driver`.
- `reactor` - manages the collection of tests to be executed in one or more containers.
- `probe` - a synthetic artifact which is injected to the `system under test`. It contains the test class and resources
  etc.
- `configuration` - defines provisioning and various system and environment properties.

## TODO

- Add the Karaf container to Pax Exam and configure it to run the simple test
- Configure karaf
- Deploy custom bundles
- Create test scenario using the custom bundles
