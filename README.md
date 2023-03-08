# CarbonLib
A library to make plugin development easier

## How to use
Maven:

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>


	<dependency>
	    <groupId>com.github.matrixidot</groupId>
	    <artifactId>CarbonLib</artifactId>
	    <version>v1.1.3-Release</version>
	</dependency>
	
Gradle:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.matrixidot:CarbonLib:v1.1.3-Release'
	}
