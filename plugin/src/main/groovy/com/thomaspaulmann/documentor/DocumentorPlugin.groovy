package com.thomaspaulmann.documentor;

import org.gradle.api.*;

class DocumentorPlugin implements Plugin<Project> {
    def void apply(Project project) {
        project.task('document') << {
            println "Hi, let's document something!"
        }
    }
}
