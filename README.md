[![Build Status](https://travis-ci.org/thomaspaulmann/Documentor.svg?branch=master)](https://travis-ci.org/thomaspaulmann/Documentor) ![Java 7 required](https://img.shields.io/badge/java-7-brightgreen.svg)
 [![License](http://img.shields.io/:license-apache-brightgreen.svg?style=flat)](https://raw.githubusercontent.com/thomaspaulmann/Documentor/master/LICENSE)

# Documentor
Documentor is a Gradle Plugin to generate Javadocs for Android Code.

## Usage

1. Add the following lines to your root build.gradle ([Latest Release](https://github.com/thomaspaulmann/Documentor/releases/latest)):

 ``` gradle
 buildscript {
    repositories {
        maven {
            url 'https://plugins.gradle.org/m2/'
        }
    }

    dependencies {
        classpath 'gradle.plugin.com.thomaspaulmann:Documentor:X.Y'
    }
 }
 ```

2. Reference the plugin from your module's build.gradle:

 ``` gradle
 apply plugin: 'com.thomaspaulmann.documentor'
 ```

3. Now you're good to go and should have some tasks in the `documentor` gradle group called `documentDebug`, `documentRelease` and `deleteDocs`. Run one of the tasks and enjoy your beautiful Javadocs. :cat: 

 ``` gradle
 $ gradle clean build documentDebug
 ```

Note: The plugin is available as an official [Gradle Plugin](https://plugins.gradle.org/plugin/com.thomaspaulmann.documentor).

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
    outputDir = "${project.projectDir}/javadoc"
}
```

## Sample

I attached a [sample gradle file](https://github.com/thomaspaulmann/Documentor/blob/master/sample.gradle) for better understanding. Furthermore, I provide you an [Example Android Project](https://github.com/thomaspaulmann/GradlePluginExamples) to see Documentor in action.
