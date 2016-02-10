# Documentor
Documentor is a gradle script to generate Javadocs for Android.

## Usage
Reference the master script after the android configuration from your module's build.gradle:

```groovy
android {
  [...]
}

apply from: 'https://raw.githubusercontent.com/thomaspaulmann/Documentor/release-1.0/documentor.gradle'
 ```

You should have a task called `document`. Run it and enjoy your Javadocs. :cat: 

## Configuration
Documentor gives you the possibility to exclude specific files. To ignore a specific path, add `DOCUMENTOR_EXCLUDE` to your gradle.properties:

```
DOCUMENTOR_EXCLUDE=com/google/android/*
 ```
 
It's also possible to exclude multiple paths. Simply separate them with a comma:
 
```
DOCUMENTOR_EXCLUDE=com/google/android/*,com/thomaspaulmann/*
 ```
Furthermore, you can specify the ouptut directory of your generated javadocs:

```
DOCUMENTOR_OUTPUT_DIR=../documentation/
```
