PojoBuilder -  A Code Generator for Pojo Builders
=================================================
[![Build Status](https://travis-ci.org/mkarneim/pojobuilder.svg?branch=master)](https://travis-ci.org/mkarneim/pojobuilder)
[![Coverage Status](https://coveralls.io/repos/mkarneim/pojobuilder/badge.png?branch=master)](https://coveralls.io/r/mkarneim/pojobuilder?branch=master)

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

About
-----

PojoBuilder is a Java annotation processor that creates a fluent builder class for POJOs (Plain Old Java Object).

The generated builder offers:

* A fluent interface to set the pojo's properties.
* A build() method that creates a new pojo instance with the given values.

You can use a generated pojo builder like this:

```java
	Contact james = new ContactBuilder()
		.withSurname("Bond")
		.withFirstname("James")
		.withEmail("007@secretservice.org")
		.build();
```
Builders can help you create test data by letting you set only the relevant properties.

For more details on:

* Test data builders, see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html (by Nat Pryce).
* The builder pattern, see http://en.wikipedia.org/wiki/Builder_pattern.
* Fluent interfaces, see http://www.martinfowler.com/bliki/FluentInterface.html (by Martin Fowler)

License and Dependencies
------------------------
The source code in the "src" directory is in the PUBLIC DOMAIN.
For details, read the [COPYING] file.

PojoBuilder is a code generator. It does not add runtime dependencies to your project.

It adds one compile-time dependency with its own license:

* [JavaWriter] 2.5.0 (see [license](http://github.com/mkarneim/pojobuilder/blob/master/license/javawriter-apache-license.txt))


Download
--------
PojoBuilder is open source. The source code is available at
http://github.com/mkarneim/pojobuilder.
For older versions and a change log, see the [release history page].

PojoBuilder binaries are available at [Sonatype OSS Maven Repository] and [Maven Central].

If you do not use a build automation tool that supports Maven repos, download the [`pojobuilder-4.3.1-jar-with-dependencies.jar`] to get PojoBuilder with all its dependencies.

How To Use
----------

PojoBuilder uses an annotation processor to generate pojo builders.
You can trigger code generation during compilation by:

* [annotating a pojo's constructor](#annotating-a-constructor)
* [annotating the pojo class](#annotating-the-pojo)
* [annotating a factory method](#annotating-a-factory-method)

### Annotating a Constructor ###

To generate a builder, annotate one of the pojo's constructors with `@GeneratePojoBuilder`.

Example:
```java
public class Contact {
  private final String surname;
  private final String firstname;
  private String email;

  @GeneratePojoBuilder
  public Contact(String surname, String firstname) {
    this.surname = surname;
    this.firstname = firstname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSurname() {
    return surname;
  }

  public String getFirstname() {
    return firstname;
  }
}
```
The [@GeneratePojoBuilder] annotation tells the processor to create a source file named `ContactBuilder`. Check [`ContactBuilder.java`] to see the generated code.

Ensure the constructor is public or accessible to the generated builder (for example, if it is protected, the builder must be in the same package).

Also, the constructor parameter names must match the pojo's property names exactly.

You can use an optional [@ConstructorProperties] annotation to map constructor parameter names to bean property names if they differ.
```java
public class Contact {
  private final String surname;
  private final String firstname;
  private String email;

  @GeneratePojoBuilder
  @ConstructorProperties({"surname","firstname"})
  public Contact(String arg1, String arg2) {
    this.surname = arg1;
    this.firstname = arg2;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSurname() {
    return surname;
  }

  public String getFirstname() {
    return firstname;
  }
}
```

### Annotating the Pojo ###

If your pojo does not define a specific constructor, has only a public default constructor, or defines exactly one constructor, annotate the class with @GeneratePojoBuilder..

Example:
```java
@GeneratePojoBuilder
public class User {
  private String name;
  private char[] password;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(char[] password) {
    this.password = password;
  }
}
```

See [`UserBuilder.java`] to review the generated code.

### Annotating a Factory Method ###

If you cannot modify the pojo's source or prefer not to annotate the pojo, annotate a factory method:

```java
public class UrlFactory {

  @GeneratePojoBuilder(withName="UrlBuilder", intoPackage = "samples")
  public static URL createUrl(
    String protocol, String host, int port, String file, URLStreamHandler handler)
      throws MalformedURLException {
    return new URL(protocol, host, port, file, handler);
  }
}
```
Check [`UrlBuilder.java`] to see the generated code.

Ensure the factory method is public and static. Its parameter names must match the pojo's property names exactly.

You can use an optional [@FactoryProperties] annotation to map the parameter names if needed.
```java
public class FileFactory {

  @GeneratePojoBuilder(intoPackage = "samples")
  @FactoryProperties({"path"})
  public static File createFile(String arg1) {
    return new File(arg1);
  }
}
```
See [`FileBuilder.java`] for the generated code.

### Annotating a Record ###

Starting with PojoBuilder 4.3, you can annotate a Java 17 record:

```java
@GeneratePojoBuilder
public record MyRecord(int x, int y, String blah) {}
```

### Directives ###
You can use the following elements of [@GeneratePojoBuilder] to configure code generation:

* **withName=&lt;String&gt;**
  specifies the pattern of the builder's name. An asterisk will be
  replaced with the pojos simple name. For example, the result of the pattern `Fluent*Builder` will become
  `FluentContactBuilder` if the pojo's name is `Contact`. The default pattern is `*Builder`.
* **withConstructor=&lt;Visibility&gt;**
  Specifies the visibility of the builder's constructor.
  Default is `Visibility.PUBLIC`.
* **intoPackage=&lt;String&gt;**
  specifies the package of the generated builder. An asterisk will be
  replaced with the pojos package. For example, the result of the pattern `*.util` will become
  `com.example.util` if the pojo's package is `com.example`. The default pattern is `*`.
* **withBaseclass=&lt;Class&gt;**
  specifies the base class of the generated builder. The default class is `Object.class`.
* **withBuilderInterface=&lt;Class&gt;**
  specifies the interface of the generated builder. The interface must declare exactly one type parameter
  and a build method with this type as return type.
  For an example please see [`Address.java`], [`Builder.java`] and [`AddressBuilder.java`].
  Default is `Void.class`, which means, that no interface should be implemented.
* **withBuilderProperties=&lt;boolean&gt;**
  specifies whether the generated builder should define builder-based with-methods using the
  builder interface (see above).
  For an example please see [`Recipient.java`], [`Builder.java`] and [`RecipientBuilder.java`].
  Default is `false`.
* **includeProperties=&lt;String[]&gt;**
  specifies which of the pojo's properties will be included into the generated builder. All properties that match any
  [property pattern] in the specified array will be included. All other non-mandatory properties will be excluded.
  Mandatory properties are those which are passed as constructor or factory method arguments. They will never be
  excluded, neither explicitly nor implicitly.
  For an example please see [`InputSourceFactory.java`] and [`InputSourceBuilder.java`].
  Default is `*`.
* **excludeProperties=&lt;String[]&gt;**
  specifies which of the pojo's properties will be excluded from the generated builder. All property that match any
  [property pattern] in the specified array will be excluded, except those that are mandatory. Mandatory properties are
  those which are passed as constructor or factory method arguments. They will never be excluded, neither explicitly
  nor implicitly.
  For an example please see [`CalendarFactory.java`] and [`GregorianCalendarBuilder.java`].
  Default is the empty array.
* **withGenerationGap=&lt;boolean&gt;**
  specifies whether the [generation gap pattern] is used. If enabled, this
  will generate two classes (instead of one), of which one contains the
  ordinary builder code, whereas the other class extends the first one and is an empty template for handwritten code.
  Please move it out of the generated-sources folder to prevent it from being overwritten.
  For examples please see [`Player.java`], [`PlayerBuilder.java`], and [`AbstractPlayerBuilder.java`].
  Default is `false`.
* **withCopyMethod=&lt;boolean&gt;**
  specifies whether a copy method should be generated. Use the copy
  method to initialize the builder's values from a given pojo instance.
  For an example please see [`TextEmail.java`] and [`TextEmailBuilder.java`].
  Default is `false`.
* **withOptionalProperties=&lt;Class&gt;**
  specifies whether the generated builder should define optional-based setter-methods using the
  specified 'Optional' type. Examples are Google Guava's `com.google.common.base.Optional` and
  `java.util.Optional` introduced with Java 8. Default is `Void.class`, which means,
  that no optional-based setter-methods are generated.
* **withSetterNamePattern=&lt;String&gt;**
  specifies the name pattern of the generated setter-methods. An asterisk will be replaced with
  the property's original name. Default is `with*`.
* **withValidator=&lt;Class&gt;**
  specifies the validator class that should be used to validate the created pojo. The class must
  define a `validate` method having one parameter that is compatible with the pojo's
  type. If the validation fails, the method must throw some runtime exception (or one of its
  subclasses).
  For an example please see [`Credentials.java`], [`CredentialsValidator.java`] and [`CredentialsBuilder.java`].
* **withFactoryMethod=&lt;String&gt;**
	specifies the name of a static factory method added to the builder class which creates a builder instance.
	An asterisk will be replaced by the pojos simple name.
	For an example please see [`Task.java`] and [`TaskBuilder.java`].
	Default is `""` meaning not to generate this method.

### Default Configuration and Meta-Annotations ###
Starting with version 3, PojoBuilder supports meta-annotations. You can place [@GeneratePojoBuilder] on another annotation, and it will be inherited.

Benefits:
* Share common PojoBuilder directives in one place.
* Combine directives for other libraries that support meta-annotations.

Example:
```java
@GeneratePojoBuilder(withName = "Fluent*Builder")
@lombok.experimental.Value // class-level annotation from Lombok
@javax.annotation.concurrent.Immutable // class-level annotation from JSR-305
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface AppPojo {
}
```

Apply it to your pojos:
```java
@AppPojo
public class Contact {
  public String name;
}
```
PojoBuilder generates `FluentContactBuilder` based on the directives from `@AppPojo`.

You can override meta-annotation defaults with a local `@GeneratePojoBuilder`:
```java
@AppPojo
@GeneratePojoBuilder(intoPackage = "builder")
public class Contact {
  public String name;
}
```
This generates `FluentContactBuilder` in the package `builder`.

Examples
--------
The [PojoBuilder wiki] offers a [cookbook] on using PojoBuilder to create a domain-specific language for tests.

Find complete code examples in the [src/testdata/java/samples] folder.



Execution
---------

To run the PojoBuilder annotation processor, include it in your compile-time classpath.

At runtime, no libraries are needed because PojoBuilder's annotations have a retention policy of `CLASS`.

Below are brief instructions for running PojoBuilder with:
* [the javac tool](#using-javac)
* [Maven](#using-maven)
* [Gradle](#using-gradle)
* [Eclipse](#using-eclipse).

### Using Javac
The `javac` compiler detects PojoBuilder if `pojobuilder-*.jar` is in the classpath.


Example:

    javac -cp pojobuilder-4.3.1-jar-with-dependencies.jar Contact.java

This generates `ContactBuilder` if `Contact` is annotated with `@GeneratePojoBuilder`.

See the [javac documentation] for more information.

### Using Maven

Add the following to your project's `pom.xml`:

	<dependency>
		<groupId>net.karneim</groupId>
		<artifactId>pojobuilder</artifactId>
		<version>4.3.1</version>
		<!-- 'provided' scope because this is only needed during compilation -->
		<scope>provided</scope>
	</dependency>

Notes:
* The compile phase auto-detects and activates PojoBuilder.
* Generated sources appear at `${project.build.directory}/generated-sources/annotations`.
* To keep generated sources in a different directory, configure the `generatedSourcesDirectory` of the `maven-compiler-plugin`. See the [sample Maven pom].
* For incremental compilation, consider [using the Maven Processor plugin] instead.
* Eclipse users may install [m2e-apt](https://github.com/jbosstools/m2e-apt) for integrated annotation processing.

### Using Gradle
This script shows how to run the PojoBuilder annotation processor with Gradle.
```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor 'net.karneim:pojobuilder:4.3.1'
    compileOnly 'net.karneim:pojobuilder:4.3.1:annotations'
}
```

Generated sources go to the standard `build/classes` directory.

To change the destination:
```groovy
tasks.compileJava {
    options.compilerArgs += ['-s', "$projectDir/src/generated/java"]
}
```

There is [another Gradle script] that enables PojoBuilder for Eclipse IDE.

### Using Eclipse
To run PojoBuilder in Eclipse:

* Open your project's properties dialog
* Navigate to "Java Build Path" tree node
* Open the "Libraries" tab
* Add `pojobuilder-4.3.1-annotations.jar` to your project classpath
* Navigate to "Java Compiler / Annotation Processing" tree node
* Check "Enable project specific settings"
* Check "Enable annotation processing"
* Check "Enable processing in editor"
* Specify the target directory for the generated code in "Generated source directory"
* Navigate to "Java Compiler / Annotation Processing / Factory Path" tree node
* Check "Enable project specific settings"
* Click "Add JARs..."
* Add `pojobuiler-4.3.1-jar-with-dependencies.jar`
* Click "OK"

How To Build
------------
To compile this project's sources, use Gradle (see [build.gradle]).

[Java]: http://www.oracle.com/technetwork/java/
[generation gap pattern]: http://martinfowler.com/dslCatalog/generationGap.html
[JavaWriter]: https://github.com/square/javawriter
[Sonatype OSS Maven Repository]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder
[Maven Central]: http://search.maven.org/#search|ga|1|a%3A%22pojobuilder%22
[`pojobuilder-4.3.1-jar-with-dependencies.jar`]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder/4.3.1/pojobuilder-4.3.1-jar-with-dependencies.jar
[javac documentation]: http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/javac.html#processing
[@ConstructorProperties]: http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html

[release history page]: http://github.com/mkarneim/pojobuilder/releases
[PojoBuilder wiki]: http://github.com/mkarneim/pojobuilder/wiki
[cookbook]: http://github.com/mkarneim/pojobuilder/wiki/Cookbook
[another Gradle script]: https://github.com/mkarneim/pojobuilder/wiki/Enabling-PojoBuilder-for-Eclipse-Using-Gradle

[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.gradle]: http://github.com/mkarneim/pojobuilder/blob/master/build.gradle
[sample Maven pom]: http://github.com/mkarneim/pojobuilder/wiki/Sample-Maven-Pom
[using the Maven Processor plugin]: http://github.com/mkarneim/pojobuilder/wiki/Incremental-Compilation-and-Maven
[sample ANT build script]: http://github.com/mkarneim/pojobuilder/wiki/Sample-Ant-Script

[@GeneratePojoBuilder]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/GeneratePojoBuilder.java
[@FactoryProperties]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/FactoryProperties.java

[property pattern]: http://github.com/mkarneim/pojobuilder/wiki/Property-Pattern-Examples

[samples]: http://github.com/mkarneim/pojobuilder/tree/master/samples
[src/testdata/java/samples/dsl/DslTest.java]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/dsl/DslTest.java
[src/testdata/java/samples]: http://github.com/mkarneim/pojobuilder/tree/master/src/testdata/java/samples
[`ContactBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/ContactBuilder.java
[`UserBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/UserBuilder.java
[`UrlBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/UrlBuilder.java
[`FileBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/FileBuilder.java
[`InputSourceFactory.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/InputSourceFactory.java
[`InputSourceBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/InputSourceBuilder.java
[`CalendarFactory.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/CalendarFactory.java
[`GregorianCalendarBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/GregorianCalendarBuilder.java
[`Player.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Player.java
[`PlayerBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/PlayerBuilder.java
[`AbstractPlayerBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/AbstractPlayerBuilder.java
[`Address.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Address.java
[`AddressBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/AddressBuilder.java
[`Recipient.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Recipient.java
[`RecipientBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/RecipientBuilder.java
[`Builder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Builder.java
[`Recipient.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Recipient.java
[`RecipientBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/RecipientBuilder.java
[`TextEmail.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/TextEmail.java
[`TextEmailBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/TextEmailBuilder.java
[`Credentials.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Credentials.java
[`CredentialsBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/CredentialsBuilder.java
[`CredentialsValidator.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/CredentialsValidator.java
[`Task.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Task.java
[`TaskBuilder.java`]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/TaskBuilder.java
