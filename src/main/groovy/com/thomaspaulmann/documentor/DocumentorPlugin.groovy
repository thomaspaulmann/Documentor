/*
 * Copyright 2016 Thomas Paul Mann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thomaspaulmann.documentor

import com.android.build.gradle.api.BaseVariant;
import org.gradle.api.*
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.JavadocMemberLevel;

class DocumentorPlugin implements Plugin<Project> {

    /* Setup */

    def void apply(Project project) {
        if (project == null || !project.hasProperty("android")) {
            // No Android project found
            throw new UnsupportedOperationException("Documentor is specialized for Android and you're not using an Android Project. What's wrong with you? ;-)")
        }

        // Add extension
        project.extensions.create("documentor", DocumentorExtension)

        // Add documentation tasks
        if (project.android.hasProperty('applicationVariants')) {
            // Add tasks for Andorid Applications
            addDocumentationTasks(project, (DomainObjectCollection<BaseVariant>) project.android.applicationVariants)
        } else if (project.android.hasProperty('libraryVariants')) {
            // Add tasks for Andorid Libraries
            addDocumentationTasks(project, (DomainObjectCollection<BaseVariant>) project.android.libraryVariants)
        } else {
            // No App or Lib detected
            throw new UnsupportedOperationException("Once again... Documentor is specialized for Android. Why you're not using an Android App or Lib?")
        }

        // Add deletion task
        addDeletionTask(project);
    }

    /* Documentation Tasks */

    def private static Task addDocumentationTasks(Project project, DomainObjectCollection<BaseVariant> variants) {
        variants.all { variant ->
            project.task("document${variant.name.capitalize()}", type: Javadoc) {
                description = "Generates Documentation for ${project.name.capitalize()} [${variant.name.capitalize()}]."
                group       = 'Documentor'

                destinationDir = new File(getOutputDirectory(project), variant.baseName)
                source         = variant.getJavaCompiler().source

                // Setup classpaths
                classpath += project.files(variant.getJavaCompiler().classpath.files)
                classpath += project.files(project.android.getBootClasspath().join(File.pathSeparator))
                classpath += project.files("${project.android.sdkDirectory}/platforms/${project.android.compileSdkVersion}/android.jar")

                // Limit Member Level
                options.memberLevel = JavadocMemberLevel.PROTECTED

                // Link JDK and Android References
                options.links("http://docs.oracle.com/javase/7/docs/api/")
                options.links("http://developer.android.com/reference/")

                // Disable doclint
                if (JavaVersion.current().isJava8Compatible()) {
                    options.addStringOption('Xdoclint:none', '-quiet')
                }

                // Exclude Android specific files
                exclude '**/BuildConfig.java'
                exclude '**/R.java'

                // Exclude optional files
                exclude getExcludes(project)
            }
        }
    }

    /* Deletion Task */

    def private static void addDeletionTask(Project project) {
        project.clean.dependsOn project.task("deleteDocs", type: Delete) {
            description = "Deletes all generated Documentations."
            group       = 'Documentor'

            delete getOutputDirectory(project)
        }
    }

    /* Get Extensions */

    def private static String[] getExcludes(Project project){
        return project.documentor.excludes == null ? "" : project.documentor.excludes
    }

    def private static String getOutputDirectory(Project project) {
        return project.documentor.outputDir == null ? "${project.getProjectDir()}/docs/" : project.documentor.outputDir
    }
}
