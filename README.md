PojoBuilder -  A Code Generator for Pojo Builders
================================================= 

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

License
-------

The source code located in the "src" and "samples" directory is in the PUBLIC DOMAIN. 
For more information please read the [COPYING] file.

Download
--------
PojoBuilder is Open Source. The *source code* is available at http://github.com/mkarneim/pojobuilder.
For older versions and a *change log* please see the [release history page].

PojoBuilder *binaries* are available for download at [Sonatype OSS Maven Repository] and [Maven Central].

If you don't use any build automation tool that supports maven repos,
you might want to download the "pojobuilder-*-jar-with-dependencies.jar" to get PojoBuilder complete with all dependent libraries included.

Dependencies
------------
PojoBuilder is a pure code generator. It does not add any runtime dependencies to your project.

However, PojoBuilder adds the following *compile-time* dependencies to your project:

* [Java] 6 
* [JavaWriter] 2.5.0 

How To Use
----------

The PojoBuilder Generator uses an annotation processor to generate pojo builders for you.
You have the following options to trigger the code generation:

* by [annotating a pojo's constructor](#annotating-a-constructor)
* by [annotating the pojo class](#annotating-the-pojo)
* by [annotating a factory method](#annotating-a-factory-method)

### Annotating a Constructor ###

To generate a builder class for a pojo you can annotate *one of its constructors* with @GeneratePojoBuilder.

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
the name "ContactBuilder". Have a look at ["src/testdata/java/samples/ContactBuilder.java"] to see the generated source code.

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

If your Pojo has no constructor (or a public default constructor), you can annotate its *class* with @GeneratePojoBuilder.

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

Have a look at ["src/testdata/java/samples/UserBuilder.java"] to see the generated source code.

### Annotating a Factory Method ###

Alternatively, if you don't have access to the pojo's source code, or if you are no fan of annotating a pojo,
you can annotate a factory method:
 
```java
public class UrlFactory {

  @GeneratePojoBuilder(withName="UrlBuilder", intoPackage = "samples")
  public static URL createUrl(String protocol, String host, int port, String file, URLStreamHandler handler)
      throws MalformedURLException {
    return new URL(protocol, host, port, file, handler);
  }
}
```
Have a look at ["src/testdata/java/samples/UrlBuilder.java"] to see the generated source code.

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
Have a look at ["src/testdata/java/samples/FileBuilder.java"] to see the generated source code.

### Directives ###
The following elements of @GeneratePojoBuilder can be used to configure the output of the code generation process.

* `withName=<String>`
    specifies the pattern of the builder's name. Any asterisk will be
	replaced with the pojos simple name. For example, the result of the pattern "Fluent*Builder" will become 
        "FluentContactBuilder" if the pojo's name is "Contact". The default pattern is `"*Builder"`.
* `intoPackage=<String>`
    specifies the package of the generated builder. Any asterisk will be
	replaced with the pojos package. For example, the result of the pattern "*.util" will become
        "com.example.util" if the pojo's package is "com.example". The default pattern is `"*"`.
* `withBaseclass=<Class>`
    specifies the base class of the generated builder. The default class is `Object.class`.
* `withBuilderInterface=<Class>`
    specifies the interface of the generated builder. The interface must declare exactly one type parameter
    and a build method with this type as return type.    
	For an example please see ["src/testdata/java/samples/Address.java"], ["src/testdata/java/samples/Builder.java"] and ["src/testdata/java/samples/AddressBuilder.java"].
	Default is `Void.class`, which means, that no interface should be implemented.
* `withBuilderProperties=<boolean>`
    specifies whether the generated builder should define builder-based with-methods using the builder interface (see above).
	For an example please see ["src/testdata/java/samples/Invoice.java"], ["src/testdata/java/samples/Builder.java"] and ["src/testdata/java/samples/InvoiceBuilder.java"].
	Default is `false`.	
* `withGenerationGap=<boolean>`
    specifies whether the [generation gap pattern] is used. If enabled, this
	will generate two classes (instead of one), of which one contains the
	ordinary builder code, whereas the other class extends the first one and is an empty template for handwritten code. 
        Please move it out of the generated-sources folder to prevent it from being overwritten. 
 	For examples please see ["src/testdata/java/samples/Player.java"], ["src/testdata/java/samples/PlayerBuilder.java"], and ["src/testdata/java/samples/AbstractPlayerBuilder.java"].
        Default is `false`.
* `withCopyMethod=<boolean>`
    specifies whether a copy method should be generated. Use the copy
	method to initialize the builder's values from a given pojo instance.
	For an example please see ["src/testdata/java/samples/TextEmail.java"] and ["src/testdata/java/samples/TextEmailBuilder.java"].
	Default is `false`.	


### Default Configuration Annotation ###
Beginning with version 3 the PojoBuilder Generator supports *default configuration annotations*.

A default configuration annotation is a custom annotation used to define common code generation directives at one single place.
It must be part of your source tree and be annotated with [@GeneratePojoBuilder].

Let's have a look at the following example:
```java
@GeneratePojoBuilder(withName = "Fluent*Builder")
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
public @interface MyDefaultDirectives {
}
```
@MyDefaultDirectives is annotated by @GeneratePojoBuilder, which declares it as an default configuration annotation
for @GeneratePojoBuilder.
In this case a value for the element 'withName' is specified, which overrides the default name pattern.

Please note that your alias annotation *must be part of the source tree* where your annotated pojos exists.
Otherwise the PojoBuilder Generator will not use it for configuring the code generation.

In order to use your default configuration annotation you have to place it on any Java element
next to the standard annotation @GeneratePojoBuilder:
```java
@MyDefaultDirectives
@GeneratePojoBuilder
public class Contact {
  public String name;
}
```
When the PojoBuilder Generator reads the pojo above, it will generate a builder class with the name "FluentContactBuilder",
because the default name pattern has been overridden in @MyDefaultDirectives.

Of course you also can amend or override any of the defaults defined by the @MyDefaultDirectives elements, 
by defining element values in @GeneratePojoBuilder:
```java
@MyDefaultDirectives
@GeneratePojoBuilder(intoPackage = "builder")
public class Contact {
  public String name;
}
```
This will generate a builder class with the name "FluentContactBuilder" into the package "builder".

You even can use a combination of multiple default configuration annotations:
```java
@MyFirstDefaults
@MySecondDefaults
@GeneratePojoBuilder
public class Contact {
  public String name;
}
```
Please note that *the order of these multiple annotations is relevant*,
since the element values are applied in top-down order.

In this example this means, that the values specified in @MyFirstDefaults are read first,
then overridden by values specified in @MySecondDefaults,
and last overridden by values specified in @GeneratePojoBuilder.

Examples
--------
The [PojoBuilder wiki] provides some [best practices] about how you could use the PojoBuilder Generator.

For some complete code examples please have a look into the [src/testdata/java/samples] folder.


Execution
---------
 
To execute the PojoBuilder annotation processor you just need to put it into the compile-time classpath.
During runtime no libraries are required since the retention policy of PojoBuilder's annotations is CLASS.

Here is a list of brief descriptions about how to run PojoBuilder with
* [the javac tool](#using-javac)
* [Maven](#using-maven)
* [Gradle](#using-gradle)
* [Ant's javac task](#using-ant)
* [Eclipse](#using-eclipse).

### Using Javac
The `javac` compiler will auto-detect the presence of PojoBuilder if pojobuilder-*.jar is included in the classpath.

For example:
    
    javac -cp pojobuilder-3.0.0-jar-with-dependencies.jar Contact.java
   
will generate a ContactBuilder if Contact is annotated with @GeneratePojoBuilder.

For more information see the [javac documentation].

### Using Maven

Add the following to your project's pom.xml to configure the PojoBuilder annotation processor.

	<dependency>
		<groupId>net.karneim</groupId>
		<artifactId>pojobuilder</artifactId>
		<version>3.0.0</version>
		<!-- 'provided' scope because this is only needed during compilation -->
		<scope>provided</scope>
	</dependency>

Notes:
* The compile phase will automatically detect and activate PojoBuilder.
* Generated sources will appear in the standard location: ${project.build.directory}/generated-sources/annotations.
* If you need to keep the generated sources in a specific directory outside of the 'target' directory, then configure the 'generatedSourcesDirectory' of the 'maven-compiler-plugin'. See ["docs/example_maven_pom.xml"] for an example.
* Eclipse users might want to install [m2e-apt](https://github.com/jbosstools/m2e-apt) to have integrated support for APT-generated sources.

### Using Gradle
This is a small build script that shows how to run the PojoBuilder annotation processor with Gradle.
```groovy
apply plugin: 'java'

repositories {
    mavenCentral()
}

configurations {
    codeGeneration
}

dependencies {
    codeGeneration group: 'net.karneim', name: 'pojobuilder', version: '3.0.0'
}
compileJava.classpath += configurations.codeGeneration
``` 
The generated sources will be placed into the standard 'build/classes' directory.

If you want to put them somewhere else, just specify the destination like this:
```groovy
compileJava.options.compilerArgs += ['-s', 'src/generated/java']
```

The wiki contains an [extended Gradle script] that distinguishes completely between code generation and compilation tasks.

### Using Ant

Here is a code snippet of an ANT build script that runs the PojoBuilder annotation processor within the javac task. 
```xml
    <!-- Add the required libraries into this directory. -->
    <fileset id="libs.fileset" dir="${basedir}/lib">
        <include name="*.jar" />
    </fileset>
    	
    <path id="class.path">
        <fileset refid="libs.fileset" />
    </path>
    
    <target name="compile" depends="init" description="Compile java sources and run annotation processor">
    	<mkdir dir="${src.gen.java.dir}" />
    	<mkdir dir="${build.classes.dir}" />
    	<javac classpathref="class.path" destdir="${build.classes.dir}">
    		<src path="${src.main.java.dir}" />
    		<!-- This tells the compiler where to place the generated source files -->
    		<compilerarg line="-s ${src.gen.java.dir}"/>
    	</javac>
    </target>
```
You find a complete sample build script at ["docs/example_ant_build.xml"].

### Using Eclipse
You could also configure Eclipse to run the PojoBuilder annotation processor during the build cycle.
It will be invoked whenever you save files that contain sources annotated with @GeneratePojoBuilder.

Do the following to enable PojoBuilder for your Eclipse project:

* Place the PojoBuilder library and the dependencies (e.g. pojobuilder-*-jar-with-dependencies.jar) into your project library directory 
* Open your project's properties dialog
* Navigate to "Java Build Path" tree node
* Open the "Libraries" tab
* Add PojoBuilder to your project classpath
* Navigate to "Java Compiler / Annotation Processing" tree node
* Check "Enable project specific settings"
* Check "Enable annotation processing"
* Check "Enable processing in editor"
* Specify the target directory for the generated code in "Generated source directory"
* Navigate to "Java Compiler / Annotation Processing / Factory Path" tree node
* Check "Enable project specific settings"
* Click "Add JARs..."
* Add pojobuiler-*-jar-with-dependencies.jar
* Click "OK"

How To Build
------------
If you want to compile this project's sources yourself you can use Gradle (see [build.gradle]). 

[Java]: http://www.oracle.com/technetwork/java/
[generation gap pattern]: http://martinfowler.com/dslCatalog/generationGap.html
[JavaWriter]: https://github.com/square/javawriter
[Sonatype OSS Maven Repository]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder
[Maven Central]: http://search.maven.org/#search|ga|1|a%3A%22pojobuilder%22
[javac documentation]: http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/javac.html#processing
[@ConstructorProperties]: http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html

[release history page]: http://github.com/mkarneim/pojobuilder/releases
[PojoBuilder wiki]: http://github.com/mkarneim/pojobuilder/wiki
[best practices]: http://github.com/mkarneim/pojobuilder/wiki/Best-practices
[extended Gradle script]: https://github.com/mkarneim/pojobuilder/wiki/Extended-Gradle-Build-Script

[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.gradle]: http://github.com/mkarneim/pojobuilder/blob/master/build.gradle
["docs/example_maven_pom.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/docs/example_maven_pom.xml
["docs/example_ant_build.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/docs/example_ant_build.xml

[@GeneratePojoBuilder]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/GeneratePojoBuilder.java
[@FactoryProperties]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/FactoryProperties.java

[samples]: http://github.com/mkarneim/pojobuilder/tree/master/samples
[src/testdata/java/samples]: http://github.com/mkarneim/pojobuilder/tree/master/src/testdata/java/samples
["src/testdata/java/samples/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/ContactBuilder.java
["src/testdata/java/samples/UserBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/UserBuilder.java
["src/testdata/java/samples/UrlBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/UrlBuilder.java
["src/testdata/java/samples/FileBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/FileBuilder.java
["src/testdata/java/samples/Player.java"] http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Player.java
["src/testdata/java/samples/PlayerBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/PlayerBuilder.java
["src/testdata/java/samples/AbstractPlayerBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/AbstractPlayerBuilder.java
["src/testdata/java/samples/Address.java"] http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Address.java
["src/testdata/java/samples/AddressBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/AddressBuilder.java
["src/testdata/java/samples/Builder.java"] http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Builder.java
["src/testdata/java/samples/Recipient.java"] http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/Recipient.java
["src/testdata/java/samples/RecipientBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/RecipientBuilder.java
["src/testdata/java/samples/TextEmail.java"] http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/TextEmail.java
["src/testdata/java/samples/TextEmailBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/src/testdata/java/samples/TextEmailBuilder.java
