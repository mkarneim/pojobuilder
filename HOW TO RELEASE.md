# How to release
This document describes the steps that I will follow in order to release
a new version of this project's artifacts to the Maven Central repository.

## One-time Preparation
Edit .m2/settings.xml and add server URLs:

    <servers>
      <server>
        <id>sonatype-nexus-snapshots</id>
        <username>mkarneim</username>
        <password>*****</password>
      </server>
      <server>
        <id>sonatype-nexus-staging</id>
        <username>mkarneim</username>
        <password>*****</password>
      </server>  
    </servers>

Replace the '*****' with Sonatype Nexus password.
  
## To make a release 
For details see http://maven.apache.org/maven-release/maven-release-plugin/usage.html
* Make sure that the artifact version in pom.xml is a SNAPSHOT, e.g. 2.3.2-SNAPSHOT.
* Commit changes to scm
* Run ```mvn release:prepare -DdryRun=true```
* Check for failures, review output and copied POMs.
* If ok, run ```mvn release:prepare``` 
* hit [ENTER] for all prompts
* Run ```mvn release:rollback```
* Run ```mvn release:perform```

## Release it 
For details see https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide
* Login to the Nexus UI at https://oss.sonatype.org/
* Go to Staging Repositories page.
* Select the PojoBuilder's staging repository.
* Click the Close button.
* Do some last checks (e.g. download the artifacts).
* Click on the Release button.
