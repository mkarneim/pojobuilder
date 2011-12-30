PojoBuilder -  A code generator for POJO builders
================================================= 

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

About
-----

PojoBuilder is a Java 6 compliant annotation processor that generates a builder class for POJOs (Plain Old Java Object). 

The generated builder provides 

* a fluent interface for specifying values for the POJO's properties 
* and a "build()" method for creating a new POJO instance with these values.


Among other things you can use builders to build test data. 

For more information on 

* test data builders see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html.
* the builder pattern see http://en.wikipedia.org/wiki/Builder_pattern. 
* fluent interface see http://www.martinfowler.com/bliki/FluentInterface.html

Download
--------

For downloading a binary distribution please visit the [download page].

Examples
--------
For some examples please have a look into the [samples] directory.

### Annotating the POJO ###

Let's have a look at the following example POJO:

	@GeneratePojoBuilder(intoPackage = "samples.builder")
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

The [@GeneratePojoBuilder] annotation tells the annotation processor to create a new Java source file with 
the name "ContactBuilder" into the package "samples.builder". If the POJO has no default constructor or if 
you want the generated builder to use a specific constructor then annotate it with @ConstructorProperties 
and specify the mapping from the parameters to the corresponding properties.

Have a look at ["samples/src/generated/java/samples/builder/ContactBuilder.java"] to see the generated source code.

Here is an example of how you can use the generated "ContactBuilder" from your code:


	Contact james = new ContactBuilder()
		.withSurname("Bond")
		.withFirstname("James")
		.withEmail("007@secretservice.org")
		.build()

### Annotating a Factory Method ###

Alternatively, if you can't modify the POJO's source code or if you don't 
like annotating a POJO, you can annotate a factory method. 

	public class PojoFactory {
	
		@GeneratePojoBuilder
		@PropertyNames({ "firstname", "surname" })
		public static Contact createContact(String aFirstname, String aSurname) {
			Contact result = new Contact(aSurname, aFirstname);
			return result;
		}
	}

Please note that the factory method must be *public* and *static*.
For a factory method with parameters you have to specify a mapping from the parameters to the corresponding POJO properties
by using the @PropertyNames annotation.

Have a look at ["samples/src/generated/java/samples/with/factory/ContactBuilder.java"] to see the generated source code.

License
-------

Read the [COPYING] file.

Dependencies
------------

* [Java] 6 
* [ANTLR Parser Generator] 3.3 
* [StringTemplate Template Engine] 4.0.4 

How To Build
------------

If you want to compile the project sources yourself you can use the included Ant build script [build.xml]. 


How To Use
----------

Make sure that the pojobuilder-annotation-*.jar is included in your project's classpath during compile time.
During runtime it is not required since the @GeneratePojoBuilder retention policy is SOURCE.

To generate a builder class for a POJO just annotate its class or a factory method with @GeneratePojoBuilder.

Then let your project sources be processed by the PojoBuilder annotation processor.

To run the annotation processor you either can

* use the apt tool to compile your project (see http://download.oracle.com/javase/6/docs/technotes/guides/apt/index.html). 
* use the javac ant task with a special configuration (see below)
* or add the PojoBuilder annotation processor to your Eclipse project configuration (see below)


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

### Using Eclipse

Do the following to configure the PojoBuilder annotation processor for your project in Eclipse:

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

[download page]: http://github.com/mkarneim/pojobuilder/archives/master
[@GeneratePojoBuilder]: http://github.com/mkarneim/pojobuilder/blob/master/src/main/java/net/karneim/pojobuilder/GeneratePojoBuilder.java
[samples]: http://github.com/mkarneim/pojobuilder/blob/master/samples
[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.xml]: http://github.com/mkarneim/pojobuilder/blob/master/build.xml
["samples/build.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/build.xml
["samples/src/generated/java/samples/builder/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/builder/ContactBuilder.java
["samples/src/generated/java/samples/with/factory/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/with/factory/ContactBuilder.java
[Java]: http://www.oracle.com/technetwork/java/
[ANTLR Parser Generator]: http://www.antlr.org/
[StringTemplate Template Engine]: http://www.stringtemplate.org/
