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
		.build()
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

If you don't use any build automation tool that supports maven repos, you might want to download the "pojobuilder-*-jar-with-dependencies.jar" to get PojoBuilder complete with all dependent libraries included.

Dependencies
------------
PojoBuilder is a pure code generator. It does not add any runtime dependencies to your project.

Anyway, it has the following compile-time dependencies:

* [Java] 6 
* [ANTLR Parser Generator] 3.5 [(download)](http://search.maven.org/#search|ga|1|g%3A%22org.antlr%22%20AND%20a%3A%22antlr%22)
* [StringTemplate Template Engine] 4.0.7 [(download)](http://search.maven.org/#search|ga|1|g%3A%22org.antlr%22%20AND%20a%3A%22ST4%22)


How To Use
----------

The PojoBuilder Generator uses an annotation processor to generate pojo builders for you.
You have the following options to activate it:

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
		public static Contact createContact(String firstname, String surname) {
			return new Contact(surname, firstname);
		}
	}
```
Please note that the factory method must be *public* and *static*. 
The method parameter names must match the names of the pojo's properties exactly.

An optional [@FactoryProperties] annotation can be used to specify the mapping from the factory-method-parameter-names
to the corresponding bean-property-names on the pojo if they differ.
```java
	public class PojoFactory {
		@GeneratePojoBuilder
		@FactoryProperties({"surname", "firstname"})
		public static Contact createContact(String arg1, String arg2) {
			return new Contact(arg1, arg2);
		}
	}
```

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
	
Examples
--------
The [PojoBuilder wiki] provides some [best practices] about how you could use the PojoBuilder Generator.

For some complete code examples please have a look into the [samples] project.


Execution
---------

To execute the PojoBuilder annotation processor you just need to put it into the compile-time classpath.
During runtime no libraries are required since the retention policy of PojoBuilder's annotations is SOURCE.

Here is a list of brief descriptions about how to run PojoBuilder with
* [the javac tool](#using-javac)
* [Maven](#using-maven)
* [Gradle](#using-gradle)
* [Ant's javac task](#using-ant)
* [Eclipse](#using-eclipse).

### Using Javac
The `javac` compiler will auto-detect the presence of PojoBuilder if pojobuilder-*.jar is included in the classpath.

For example:
    
    javac -cp pojobuilder-2.4.1-jar-with-dependencies.jar Contact.java
   
will generate a ContactBuilder if Contact is annotated with @GeneratePojoBuilder.

For more information see the [javac documentation].

### Using Maven

Add the following to your project's pom.xml to configure the PojoBuilder annotation processor.

	<dependency>
		<groupId>net.karneim</groupId>
		<artifactId>pojobuilder</artifactId>
		<version>2.4.1</version>
		<!-- 'provided' scope because this is only needed during compilation -->
		<scope>provided</scope>
	</dependency>

Notes:
* The compile phase will automatically detect and activate PojoBuilder.
* Generated sources will appear in the standard location: ${project.build.directory}/generated-sources/annotations.
* If you need to keep the generated sources in a specific directory outside of the 'target' directory, 
then configure the 'generatedSourcesDirectory' of the 'maven-compiler-plugin'. See ["samples/pom.xml"] for an example.
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
    codeGeneration group: 'net.karneim', name: 'pojobuilder', version: '2.4.1'
}
compileJava.classpath += configurations.codeGeneration
``` 
The generated sources will be placed into the standard 'build/classes' directory.

If you want to put them somewhere else, just specify the destination like this:
```groovy
compileJava.options.compilerArgs += ['-s', 'src/generated/java']
```

This further example completely distinguish between code generation tasks as well as its produced ```*Builder.(java|class)``` files 
and other sources and/or classes. Furthermore it sets up dependencies between ```main```, ```pojobuilder```and ```test``` ```SourceSets```: 

```groovy
apply plugin: 'java'

ext.srcGenDir = file("${projectDir}/src-gen")

configurations {
    codeGeneration
}

sourceSets {
    pojobuilder {
        java {
            srcDirs = [ "${srcGenDir}/${owner.name}/java" ]
        }
        resources {
            srcDirs = [ "${srcGenDir}/${owner.name}/resources" ]
        }
        compileClasspath += main.compileClasspath + main.output
    }
    test {
        compileClasspath += pojobuilder.output
        runtimeClasspath += pojobuilder.output
    }
}

repositories {
    mavenCentral()
}

dependencies {
    codeGeneration 'net.karneim:pojobuilder:2.4.1'

    compile 'net.karneim:pojobuilder:2.4.1:annotations'
    testCompile 'junit:junit:4.11'
}

clean << {
    srcGenDir.deleteDir()
}

task generatePojobuilder(type: JavaCompile, group: 'build', description: 'Generates all pojobuilder types') {
    ext.srcDestinationDir = sourceSets.pojobuilder.java.srcDirs.iterator()[0]
    source = sourceSets.main.java
    destinationDir = temporaryDir
    classpath = configurations.compile + configurations.codeGeneration
    options.compilerArgs = [
            '-proc:only',
            '-Xmaxerrs', '0',
            '-s', srcDestinationDir
        ]
    doFirst {
        srcDestinationDir.exists() || srcDestinationDir.mkdirs()
    }
}
compilePojobuilderJava.dependsOn generatePojobuilder
compileTestJava.dependsOn pojobuilderClasses
```

And if one needs to generate pojobuilders for multiple sources once at a time, this can be done by using this class:
```groovy
task generatePojobuilder(type: GeneratePojobuilder)

class GeneratePojobuilder extends JavaCompile {

    @Input
    def srcDestinationDir = project.file('src-gen/pojobuilder/java')

    def GeneratePojobuilder() {
        group = 'generate'
        description = '''Generates all pojobuilders or 'main' sources'''

        source = project.sourceSets.main.java
        destinationDir = temporaryDir

        classpath = project.configurations.codeGeneration + project.configurations.compile

        options.compilerArgs = [
                '-proc:only',
                '-Xmaxerrs', '0',
                '-s', srcDestinationDir
            ]
    }

    @Override
    void compile() {
        srcDestinationDir.exists() || srcDestinationDir.mkdirs()
        super.compile()
    }
}
```


### Using Ant

Here is a code snippet of an ANT build script that runs the PojoBuilder annotation processor within the javac task. 

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

You find a complete sample build script at ["samples/build.xml"].

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
If you want to compile this project's sources yourself you can use Maven (see [pom.xml]). 


[release history page]: http://github.com/mkarneim/pojobuilder/releases
[Maven Central]: http://search.maven.org/#search|ga|1|a%3A%22pojobuilder%22
[Sonatype OSS Maven Repository]: https://oss.sonatype.org/content/repositories/releases/net/karneim/pojobuilder
[@GeneratePojoBuilder]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/GeneratePojoBuilder.java
[@FactoryProperties]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/FactoryProperties.java
[@ConstructorProperties]: http://docs.oracle.com/javase/6/docs/api/java/beans/ConstructorProperties.html
[samples]: http://github.com/mkarneim/pojobuilder/blob/master/samples
[PojoBuilder wiki]: http://github.com/mkarneim/pojobuilder/wiki
[best practices]: http://github.com/mkarneim/pojobuilder/wiki/Best-practices
[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[pom.xml]: http://github.com/mkarneim/pojobuilder/blob/master/pom.xml
["samples/build.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/build.xml
["samples/pom.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/pom.xml
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

