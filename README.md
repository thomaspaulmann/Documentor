# Documentor
Documentor is a gradle script to generate Javadocs for android code.

## Usage
Reference the master script after the android configuration from your module's build.gradle:

```groovy
android {
  [...]
}

apply from: 'https://raw.githubusercontent.com/thomaspaulmann/Documentor/release-1.0/documentor.gradle'
 ```
