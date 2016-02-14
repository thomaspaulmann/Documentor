[![Build Status](https://travis-ci.org/thomaspaulmann/Documentor.svg?branch=master)](https://travis-ci.org/thomaspaulmann/Documentor) [![License](http://img.shields.io/:license-apache-brightgreen.svg?style=flat)](https://raw.githubusercontent.com/thomaspaulmann/Documentor/master/LICENSE)

# Documentor
Documentor is a gradle script to generate Javadocs for Android.

## Usage
1. Add the following lines to your root build.gradle:

 ``` gradle
 buildscript {
   repositories {
     maven {
       url "https://plugins.gradle.org/m2/"
     }
   }
   dependencies {
     classpath "gradle.plugin.com.thomaspaulmann:Documentor:1.0"
   }
 }
  ```

2. Reference the plugin from your module's build.gradle:

 ``` gradle
 apply plugin: "com.thomaspaulmann.documentor"
 ```

3. Now you're good to go and should have some tasks in the `documentor` gradle group called `documentDebug`, `documentRelease` and `deleteDocs`. Run one of the tasks and enjoy your beautiful Javadocs. :cat: 

 ``` gradle
 $ gradle clean build documentDebug
 ```

Note: The plugin is available as an official Gradle Plugin [Gradle Plugin](https://plugins.gradle.org/plugin/com.thomaspaulmann.documentor).

## Configuration
Documentor gives you the possibility to exclude specific files. To ignore a specific path, reference the `documentor` extension in your module's build.gradle and add your files to the `excludes` option:

``` gradle
documentor {
    excludes = ["**/ExcludeActivity.java"]
}
```
It's also possible to exclude complete paths or multiple files. Simply separate them with a comma:
 
``` gradle
documentor {
    excludes = ["**/exclude/**", "**/ExcludeActivity.java"]
}
```

Furthermore, you can specify the ouptut directory (relative to your project directory) of your generated javadocs:

``` gradle
documentor {
    outputDir = "documentation"
}
```
