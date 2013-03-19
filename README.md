PojoBuilder -  A Code Generator for Pojo Builders
================================================= 

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

About
-----

The PojoBuilder Generator is a Java 6 compliant annotation processor that generates a fluent builder class for POJOs (Plain Old Java Object). 

The generated builder provides 

* a fluent interface for specifying values for the pojo's properties 
* and a "build()" method for creating a new pojo instance with these values.

Here is an example of how you could use a generated pojo builder from your code:
```java
	Contact james = new ContactBuilder()
		.withSurname("Bond")
		.withFirstname("James")
		.withEmail("007@secretservice.org")
		.build()
```
Builders are quite useful, for example, to build test data, where you only want to set the relevant data properties.

For more information on 

* test data builders see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html.
* the builder pattern see http://en.wikipedia.org/wiki/Builder_pattern. 
* fluent interface see http://www.martinfowler.com/bliki/FluentInterface.html

Download
--------

You can download the [PojoBuilder at Maven Central].

License
-------

The source code located in the "src" directory is in the PUBLIC DOMAIN. 
Read the [COPYING] file.

Dependencies
------------

* [Java] 6 
* [ANTLR Parser Generator] 3.5 [(download)](http://search.maven.org/#search|ga|1|g%3A%22org.antlr%22%20AND%20a%3A%22antlr%22)
* [StringTemplate Template Engine] 4.0.7 [(download)](http://search.maven.org/#search|ga|1|g%3A%22org.antlr%22%20AND%20a%3A%22stringtemplate%22)


Examples
--------
The wiki provides some [best practices] about how you could use the PojoBuilder Generator.

For some complete code examples please have a look into the [samples] directory.

How To Use
----------

The PojoBuilder Generator uses an annotation processor to generate pojo builders for you.
You have two options to activate it:

* by [annotating the pojo class](#annotating-the-pojo)
* by [annotating a factory method](#annotating-a-factory-method)

### Annotating the Pojo ###

To generate a builder class for a pojo you can annotate its class with @GeneratePojoBuilder.

Let's have a look at the following example pojo:
```java
	@GeneratePojoBuilder
	public class Contact {
		private final String surname;
		private final String firstname;
		private String email;
	    
		@ConstructorProperties({"surname","firstname"})
		public Contact(String aSurname, String aFirstname) {
			super();
			this.surname = aSurname;
			this.firstname = aFirstname;
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
the name "ContactBuilder".

Use the [@ConstructorProperties] annotation if the pojo has no default constructor 
or if you want the generated builder to use a specific constructor.
The value array specifies the mapping from the parameters to the corresponding property names.

Have a look at ["samples/src/generated/java/samples/builder/ContactBuilder.java"] to see the generated source code.

### Annotating a Factory Method ###

Alternatively, if you don't have access to the pojo's source code, or if you are no fan of annotating a pojo,
you can annotate a factory method:
 
```java
	public class PojoFactory {
	
		@GeneratePojoBuilder
		@PropertyNames({ "firstname", "surname" })
		public static Contact createContact(String aFirstname, String aSurname) {
			Contact result = new Contact(aSurname, aFirstname);
			return result;
		}
	}
```
Please note that the factory method must be *public* and *static*. 

Use the [@PropertyNames] annotation if the factory method requires parameters. 
The value array specifies the mapping from the parameters to the corresponding property names.

Have a look at ["samples/src/generated/java/samples/with/factory/ContactBuilder.java"] to see the generated source code.

### Directives ###
The following attributes of the @GeneratePojoBuilder annotation can be used to influence the code generation process.

* `withBaseclass=<Class>`
    specifies the base class of the generated builder. The default class is `Object.class`.
* `withName=<String>`
    specifies the pattern of the builder's name. Any asterisk will be
	replaced with the pojos simple name. For example, the result of the pattern "Fluent*Builder" will become 
        "FluentContactBuilder" if the pojo's name is "Contact". The default pattern is `"*Builder"`.
* `intoPackage=<String>`
    specifies the package of the generated builder. Any asterisk will be
	replaced with the pojos package. For example, the result of the pattern "*.util" will become
        "com.example.util" if the pojo's package is "com.example". The default pattern is `"*"`.
* `withGenerationGap=<boolean>`
    specifies whether the [generation gap pattern] is used. If enabled, this
	will generate two classes (instead of one), of which one contains the
	ordinary builder code, whereas the other class extends the first one and is an empty template for handwritten code. 
        Please move it out of the generated-sources folder to prevent it from being overwritten. 
 	For examples please see ["samples/src/main/java/samples/with/generationgap"] 
        and ["samples/src/generated/java/samples/with/generationgap"].
        Default is `false`.
* `withCopyMethod=<boolean>`
    specifies whether a copy method should be generated. Use the copy
	method to initialize the builder's values from a given pojo instance.
	For an example please see ["samples/src/generated/java/samples/with/copy/AddressDTOBuilder.java"].
	Default is `false`.
	
Execution
---------

To execute the annotation processor you either can

* [use the javac tool](#using-javac)
* [use Ant's javac task](#using-ant)
* [use the maven-processor-plugin](#using-maven)
* or [use Eclipse](#using-eclipse).

In any case make sure that the pojobuilder-*.jar and it's dependend libraries are included in your project's classpath
during compile time.
During runtime no libraries are required since the retention policy of PojoBuilder's annotations is SOURCE.

### Using Javac
The `javac` compiler will auto-detect the presence of PojoBuilder if pojobuilder-*.jar is included in the classpath.

For example:
    
    javac -cp pojobuilder-2.3.0.jar;ST4-4.0.7.jar;antlr-runtime-3.5.jar Contact.java
   
will generate a ContactBuilder if Contact is annotated with @GeneratePojoBuilder.

For more information see the [javac documentation].

### Using Ant

Here is a code snippet of an ANT build script that runs the PojoBuilder annotation processor within the javac task. 

    <!-- Filesets and Classpaths  -->
    <fileset id="libs.fileset" dir="${lib.dir}">
        <include name="antlr-*.jar" />
        <include name="ST-*.jar" />
        <include name="pojobuilder-*.jar" />
        <!-- include any project specific libs here -->
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


You can find a complete sample build script at ["samples/build.xml"].

### Using Maven

Add the following to your project's pom.xml to configure the PojoBuilder annotation processor.

	<dependency>
		<!-- Provided scope because this is only needed during compilation -->
		<groupId>net.karneim</groupId>
		<artifactId>pojobuilder</artifactId>
		<version>2.3.0</version>
		<scope>provided</scope>
	</dependency>

Notes:
* The compile phase will automatically detect and activate PojoBuilder.
* Generated sources will appear in the standard location: ${project.build.directory}/generated-sources/annotations.
* If using Eclipse you should install [m2e-apt](https://github.com/jbosstools/m2e-apt) to have integrated support for APT-generated sources.

### Using Eclipse

Do the following to configure the PojoBuilder annotation processor manually for your project in Eclipse:

* Place the PojoBuilder libraries (antlr-*.jar, ST-*.jar pojobuilder-*.jar) into your project library directory 
* Open your project's properties dialog
* Navigate to "Java Build Path" tree node
* Open the "Libraries" tab
* Add the library pojobuilder-*.jar to your project classpath
* Navigate to "Java Compiler / Annotation Processing" tree node
* Check "Enable project specific settings"
* Check "Enable annotation processing"
* Check "Enable processing in editor"
* Specify the target directory for the generated code in "Generated source directory"
* Navigate to "Java Compiler / Annotation Processing / Factory Path" tree node
* Check "Enable project specific settings"
* Click "Add JARs..."
* Add antlr-*.jar
* Add ST-*.jar
* Add pojobuilder-*.jar

Now the annotation processor will be automatically invoked during the Eclipse build cycle.

How To Build
------------
If you want to compile this project's sources yourself you can use Maven (see [pom.xml]) or Ant  (see [build.xml]). 

[PojoBuilder at Maven Central]: http://search.maven.org/#search|ga|1|a%3A%22pojobuilder%22
[@GeneratePojoBuilder]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/GeneratePojoBuilder.java
[@PropertyNames]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/PropertyNames.java
[@ConstructorProperties]: http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html
[samples]: http://github.com/mkarneim/pojobuilder/blob/master/samples
[best practices]: http://github.com/mkarneim/pojobuilder/wiki/Best-practices
[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.xml]: http://github.com/mkarneim/pojobuilder/blob/master/build.xml
[pom.xml]: http://github.com/mkarneim/pojobuilder/blob/master/pom.xml
["samples/build.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/build.xml
["samples/src/generated/java/samples/builder/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/builder/ContactBuilder.java
["samples/src/generated/java/samples/with/factory/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/with/factory/ContactBuilder.java
[generation gap pattern]: http://martinfowler.com/dslCatalog/generationGap.html
["samples/src/main/java/samples/with/generationgap"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/main/java/samples/with/generationgap/
["samples/src/generated/java/samples/with/generationgap"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/with/generationgap/
["samples/src/generated/java/samples/with/copy/AddressDTOBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/with/copy/AddressDTOBuilder.java
[javac documentation]: http://docs.oracle.com/javase/6/docs/technotes/tools/solaris/javac.html#processing
[Java]: http://www.oracle.com/technetwork/java/
[ANTLR Parser Generator]: http://www.antlr.org/
[StringTemplate Template Engine]: http://www.stringtemplate.org/
