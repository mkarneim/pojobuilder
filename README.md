PojoBuilder -  A code generator for POJO builders
================================================= 

Author: Michael Karneim

Project Homepage: http://github.com/mkarneim/pojobuilder

Download Page: http://github.com/mkarneim/pojobuilder/archives/master

About
-----

PojoBuilder is a Java 6 compliant annotation processor that generates a builder class for
each annotated POJO (Plain Old Java Object). 

The generated builder provides 

* a fluent interface for specifying values for the POJO's properties 
* and a "build()" method for creating a new POJO instance with these values.


Among other things you can use builders to build test data. 

For more information on 

* test data builders see http://c2.com/cgi/wiki?TestDataBuilder and http://www.natpryce.com/articles/000714.html.
* the builder pattern see http://en.wikipedia.org/wiki/Builder_pattern. 
* fluent interface see http://www.martinfowler.com/bliki/FluentInterface.html

Example
-------

Let's have look at the following example POJO:

	@GeneratePojoBuilder(intoPackage = "samples.builder")
	public class Contact { 
		private final String name;
		private String email;
	
		@ConstructorProperties({ "name"})
		public Contact(String aName) {
			this.name = aName;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getName() {
			return name;
		}
	}

The @GeneratePojoBuilder annotation tells the annotation processor to create a new Java source file with 
the name "ContactBuilder" in the package "samples.builder". If you want the generated builder to use a specific 
constructor of your POJO then annotate it with @ConstructorProperties and specify the mapping from the 
parameters to the corresponding properties.

Have a look at ["samples/src/generated/java/samples/builder/ContactBuilder.java"] to see the generated source code.

Here is an example of how you can use the generated "ContactBuilder" from your code:


	Contact james = new ContactBuilder()
		.withName("James Bond")
		.withEmail("007@secretservice.org")
		.build();


For more examples please have a look into the [samples] directory.    

License
-------

Read the [COPYING] file.

How To Build
------------

For compiling the sources Java 6 is required.
For building the PojoBuilder library you can use the included Ant build script [build.xml]. 


How To Use
----------

Make sure that the pojobuilder-*.jar is included in your project's classpath during compile time.
During runtime it is not required since the @GeneratePojoBuilder retention policy is SOURCE.

To generate a builder class for a POJO just annotate its class with @GeneratePojoBuilder. 

Then let your project sources be processed by the PojoBuilder annotation processor.

To run the annotation processor you either can

* use the apt tool to compile your project (see http://download.oracle.com/javase/6/docs/technotes/guides/apt/index.html). 
* use the javac ant task with a special configuration (see below)
* or add the PojoBuilder annotation processor to your Eclipse project configuration (see below)


### Using Ant

Here is a code snippet of an ANT build script that runs the PojoBuilder annotation processor within the javac task. 


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

[samples]: http://github.com/mkarneim/pojobuilder/blob/master/samples
[COPYING]: http://github.com/mkarneim/pojobuilder/blob/master/COPYING
[build.xml]: http://github.com/mkarneim/pojobuilder/blob/master/build.xml
["samples/build.xml"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/build.xml
["samples/src/generated/java/samples/builder/ContactBuilder.java"]: http://github.com/mkarneim/pojobuilder/blob/master/samples/src/generated/java/samples/builder/ContactBuilder.java
