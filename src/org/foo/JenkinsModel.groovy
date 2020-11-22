package org.foo

import com.cloudbees.groovy.cps.NonCPS
import hudson.Functions
import hudson.tasks.junit.TestResultAction

import jenkins.model.Jenkins

import org.apache.commons.io.IOUtils
import org.jenkinsci.plugins.workflow.libs.LibrariesAction
import org.jenkinsci.plugins.workflow.steps.MissingContextVariableException

class JenkinsModel implements Serializable {
    def steps
    JenkinsModel(steps) { this.steps = steps}
    def getActiveJenkinsInstance() {
        return Jenkins.getActiveInstance()
    }
}