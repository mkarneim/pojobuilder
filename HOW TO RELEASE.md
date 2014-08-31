# How to Release
This document describes the steps that I will follow in order to release
a new version of this project's artifacts to the Maven Central repository.

For more information please see http://central.sonatype.org/pages/gradle.html

## One-time Preparation
Edit ```<home>/.gradle/gradle.properties``` and provide GPG key path, id and passphrase, and Sonatype credentials:
```
signing.keyId=YourKeyId
signing.password=YourPublicKeyPassword
signing.secretKeyRingFile=PathToYourKeyRingFile
ossrhUsername=your-jira-id
ossrhPassword=your-jira-password
```

## To Build and Upload a Release 
* Make sure that the artifact version in ```build.gradle``` is NOT a SNAPSHOT.
* Run ```./gradlew clean build uploadArchives```

## To Release It 
For details see https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide
* Login to the Nexus UI at https://oss.sonatype.org/
* Go to Staging Repositories page.
* Select the PojoBuilder's staging repository.
* Click the Close button.
* Do some last checks (e.g. download the artifacts).
* Click on the Release button.

## Post Actions
* Commit changes to GitHub master
* Create release label on GitHub master
* Change the artifact version in ```build.gradle``` to a SNAPSHOT, e.g. 3.0.0-SNAPSHOT.
* Commit changes to GitHub master
