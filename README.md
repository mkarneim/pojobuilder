PojoBuilder -  A Code Generator for Pojo Builders
=================================================
[![Build Status](https://travis-ci.org/mkarneim/pojobuilder.svg?branch=master)](https://travis-ci.org/mkarneim/pojobuilder)
[![Coverage Status](https://coveralls.io/repos/mkarneim/pojobuilder/badge.png?branch=master)](https://coveralls.io/r/mkarneim/pojobuilder?branch=master)

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

About
-----

The PojoBuilder Generator is a Java 6 compliant annotation processor that generates a fluent builder class for POJOs (Plain Old Java Object).

The generated builder provides

* a fluent interface for specifying values for the pojo's properties in a DSL like manner
* and a "build()" method for creating a new pojo instance with these values.

Here is an example of how you could use a generated pojo builder from your code:
```java
	Contact james = new ContactBuilder()
		.withSurname("Bond")
		.withFirstname("James")
		.withEmail("007@secretservice.org")
		.build();
```
Builders are quite useful, for example, to build test data, where you only want to set the relevant data properties.

For more information on

* test data builders see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html.
* the builder pattern see http://en.wikipedia.org/wiki/Builder_pattern.
* fluent interface see http://www.martinfowler.com/bliki/FluentInterface.html

License and Dependencies
------------------------
The source code located in the "src" directory is in the PUBLIC DOMAIN.
For more information please read the [COPYING] file.

PojoBuilder is a pure code generator. It does not add any runtime dependencies to your project.

However, PojoBuilder adds the following *compile-time* dependency to your project, which has its own license:

* [JavaWriter] 2.5.0 (see [license](http://github.com/mkarneim/pojobuilder/blob/master/license/javawriter-apache-license.txt))


Download
--------
PojoBuilder is Open Source. The *source code* is available at http://github.com/mkarneim/pojobuilder.
For older versions and a *change log* please see the [release history page].

PojoBuilder *binaries* are available for download at [Sonatype OSS Maven Repository] and [Maven Central].

If you don't use any build automation tool that supports maven repos,
you might want to download the [`pojobuilder-4.2.3-jar-with-dependencies.jar`] to get PojoBuilder complete with all dependent libraries included.

How To Use
----------

The PojoBuilder generator uses an annotation processor to generate pojo builders for you.
You have the following options to trigger the code generation:

* by [annotating a pojo's constructor](#annotating-a-constructor)
* by [annotating the pojo class](#annotating-the-pojo)
* by [annotating a factory method](#annotating-a-factory-method)

### Annotating a Constructor ###

To generate a builder class for a pojo you can annotate *one of its constructors* with `@GeneratePojoBuilder`.

Let's have a look at the following example pojo:
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
The [@GeneratePojoBuilder] annotation tells the annotation processor to create a new Java source file with
the name `ContactBuilder`. Have a look at [`ContactBuilder.java`] to see the generated source code.

Please note that the constructor must be *public* or otherwise accessible for the generated builder,
e.g. if it's *protected* the generated builder must reside in the same package.

And also note that the constructor parameter names must match the names of the pojo's properties *exactly*.

An optional [@ConstructorProperties] annotation can be used to specify the mapping from the constructor-parameter-names
to the corresponding bean-property-names on the pojo if they differ.
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

If your pojo has no constructor (or a public default constructor), you can annotate its *class* with `@GeneratePojoBuilder`.

Let's have a look at the following example pojo:
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

Have a look at [`UserBuilder.java`] to see the generated source code.

### Annotating a Factory Method ###

Alternatively, if you don't have access to the pojo's source code, or if you are no fan of annotating a pojo,
you can annotate a factory method:

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
Have a look at [`UrlBuilder.java`] to see the generated source code.

Please note that the factory method must be *public* and *static*.
The method parameter names must match the names of the pojo's properties exactly.

An optional [@FactoryProperties] annotation can be used to specify the mapping from the factory-method-parameter-names
to the corresponding bean-property-names on the pojo if they differ.
```java
public class FileFactory {

  @GeneratePojoBuilder(intoPackage = "samples")
  @FactoryProperties({"path"})
  public static File createFile(String arg1) {
    return new File(arg1);
  }
}
```
Have a look at [`FileBuilder.java`] to see the generated source code.

### Directives ###
The following elements of [@GeneratePojoBuilder] can be used to configure the output of the code generation process.

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
Beginning with version 3, PojoBuilder supports *meta-annotations*. That is, you can place [@GeneratePojoBuilder] onto
another annotation and it will be inherited.

Advantages are:
* Common PojoBuilder directives can be shared in one place
* The annotation can also hold directives for other libraries that support meta annotations,
binding them all up into one meaningful annotation for use in your application.

The following example defines `@AppPojo` which can applied at a class level and encapsulates the annotations from three
different sources (PojoBuilder, Lombok and JSR-305).
```java
@GeneratePojoBuilder(withName = "Fluent*Builder")
@lombok.experimental.Value // class-level annotation from Lombok
@javax.annotation.concurrent.Immutable // class-level annotation from JSR-305
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface AppPojo {
}
```

This can be placed onto each of your pojos:
```java
@AppPojo
public class Contact {
  public String name;
}
```
PojoBuilder will generate `FluentContactBuilder` based on the directives inherited from the `@AppPojo` annotation.

Defaults inherited from meta-annotations can be overriden by more 'local' `@GeneratePojoBuilder` annotations:
```java
@AppPojo
@GeneratePojoBuilder(intoPackage = "builder")
public class Contact {
  public String name;
}
```
This will generate `FluentContactBuilder` as before but into the package `builder`.

Examples
--------
The [PojoBuilder wiki] provides a [cookbook] about using the PojoBuilder Generator, e.g for building a domain-specific language for automated tests.

For some complete code examples please have a look into the [src/testdata/java/samples] folder.



Execution
---------

To execute the PojoBuilder annotation processor you just need to put it into the compile-time classpath.
During runtime no libraries are required since the retention policy of PojoBuilder's annotations is `CLASS`.

Here is a list of brief descriptions about how to run PojoBuilder with
* [the javac tool](#using-javac)
* [Maven](#using-maven)
* [Gradle](#using-gradle)
* [Ant's javac task](#using-ant)
* [Eclipse](#using-eclipse).

### Using Javac
The `javac` compiler will auto-detect the presence of PojoBuilder if `pojobuilder-*.jar` is included in the classpath.

For example:

    javac -cp pojobuilder-4.2.3-jar-with-dependencies.jar Contact.java

will generate a `ContactBuilder` if `Contact` is annotated with `@GeneratePojoBuilder`.

For more information see the [javac documentation].

### Using Maven

Add the following to your project's `pom.xml` to configure the PojoBuilder annotation processor.

	<dependency>
		<groupId>net.karneim</groupId>
		<artifactId>pojobuilder</artifactId>
		<version>4.2.3</version>
		<!-- 'provided' scope because this is only needed during compilation -->
		<scope>provided</scope>
	</dependency>

Notes:
* The compile phase will automatically detect and activate PojoBuilder.
* Generated sources will appear in the standard location: `${project.build.directory}/generated-sources/annotations`.
* If you need to keep the generated sources in a specific directory outside of the `target` directory, then configure the `generatedSourcesDirectory` of the `maven-compiler-plugin`. See the [sample Maven pom] for an example.
* If you rely on incremental compilation then you might want to [use the maven processor plugin] instead.
* Eclipse users might want to install [m2e-apt](https://github.com/jbosstools/m2e-apt) to have integrated support for APT-generated sources.

### Using Gradle
This is a small build script that shows how to run the PojoBuilder annotation processor with Gradle.
```groovy
apply plugin: 'java'

repositories {
  mavenCentral()
}

dependencies {
  compile 'net.karneim:pojobuilder:4.2.3'
}
```
Please note that this not only adds the PojoBuilder and its dependencies to your compile-time class path but also to your run-time class path.

Alternatively you can use the following script to add PojoBuilder only to the compile-time class path:
```groovy
apply plugin: 'java'

repositories {
  mavenCentral()
}

configurations {
  codeGeneration
}

dependencies {
  codeGeneration 'net.karneim:pojobuilder:4.2.3'
  compileOnly 'net.karneim:pojobuilder:4.2.3:annotations'
}
compileJava.classpath += configurations.codeGeneration
compileTestJava.classpath += configurations.codeGeneration
```

In both cases the generated sources will be placed into the standard `build/classes` directory.

If you want to put them somewhere else, just specify the destination like this:
```groovy
compileJava.options.compilerArgs += ['-s', 'src/generated/java']
```

The wiki contains an [extended Gradle script] that distinguishes completely between code generation and compilation tasks.

There is [another Gradle script] that enables PojoBuilder for Eclipse IDE.

#### Gradle 5.0

With Gradle 5.0 any annotation processor in the classpath don't get executed anymore.
To make pojobuilder work again, replace the used dependency scope with `annotationProcessor`

```groovy
dependencies {
  annotationProcessor 'net.karneim:pojobuilder:4.2.3'
}
```

### Using Ant

Here is a code snippet of some [sample ANT build script] that runs the PojoBuilder annotation processor within the `javac` task.
```xml
    <!-- Add the required libraries into this directory. -->
    <fileset id="libs.fileset" dir="${basedir}/lib">
        <include name="*.jar" />
    </fileset>

    <path id="class.path">
        <fileset refid="libs.fileset" />
    </path>

    <target name="compile" depends="init"
            description="Compile java sources and run annotation processor">
    	<mkdir dir="${src.gen.java.dir}" />
    	<mkdir dir="${build.classes.dir}" />
    	<javac classpathref="class.path" destdir="${build.classes.dir}">
    		<src path="${src.main.java.dir}" />
    		<!-- This tells the compiler where to place the generated source files -->
    		<compilerarg line="-s ${src.gen.java.dir}"/>
    	</javac>
    </target>
```

### Using Eclipse
You could also configure Eclipse to run the PojoBuilder annotation processor during the build cycle.
It will be invoked whenever you save files that contain sources annotated with `@GeneratePojoBuilder`.

Do the following to enable PojoBuilder for your Eclipse project:

* Open your project's properties dialog
* Navigate to "Java Build Path" tree node
* Open the "Libraries" tab
* Add `pojobuilder-4.2.3-annotations.jar` to your project classpath
* Navigate to "Java Compiler / Annotation Processing" tree node
* Check "Enable project specific settings"
* Check "Enable annotation processing"
* Check "Enable processing in editor"
* Specify the target directory for the generated code in "Generated source directory"
* Navigate to "Java Compiler / Annotation Processing / Factory Path" tree node
* Check "Enable project specific settings"
* Click "Add JARs..."
* Add `pojobuiler-4.2.3-jar-with-dependencies.jar`
* Click "OK"

How To Build
------------
If you want to compile this project's sources yourself you can use Gradle (see [build.gradle]).

[Java]: http://www.oracle.com/technetwork/java/
[generation gap pattern]: http://martinfowler.com/dslCatalog/generationGap.html
[JavaWriter]: https://github.com/square/javawriter
[Sonatype OSS Maven Repository]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder
[Maven Central]: http://search.maven.org/#search|ga|1|a%3A%22pojobuilder%22
[`pojobuilder-4.2.3-jar-with-dependencies.jar`]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder/4.2.3/pojobuilder-4.2.3-jar-with-dependencies.jar
[javac documentation]: http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/javac.html#processing
[@ConstructorProperties]: http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html

[release history page]: http://github.com/mkarneim/pojobuilder/releases
[PojoBuilder wiki]: http://github.com/mkarneim/pojobuilder/wiki
[cookbook]: http://github.com/mkarneim/pojobuilder/wiki/Cookbook
[extended Gradle script]: https://github.com/mkarneim/pojobuilder/wiki/Extended-Gradle-Build-Script
[another Gradle script]: https://github.com/mkarneim/pojobuilder/wiki/Enabling-PojoBuilder-for-Eclipse-Using-Gradle

[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.gradle]: http://github.com/mkarneim/pojobuilder/blob/master/build.gradle
[sample Maven pom]: http://github.com/mkarneim/pojobuilder/wiki/Sample-Maven-Pom
[use the maven processor plugin]: http://github.com/mkarneim/pojobuilder/wiki/Incremental-Compilation-and-Maven
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
