#!groovy
def call(Map config) {
    println config
    // evaluate the body block, and collect configuration into the object
    // def pipelineParams= [:]
    // body.resolveStrategy = Closure.DELEGATE_FIRST
    // body.delegate = pipelineParams
    // body()
    pipeline{
        agent {
            kubernetes {
            defaultContainer 'jnlp'
            }
        }
        stages {
            stage('Example') {
                //when {
                //        buildingTag()
                //    }
                options {
                    timeout(time: 1, unit: 'HOURS')
                }
                steps {
                    echo 'Hello World ! I am in develop branch.'
                    echo env.BRANCH_NAME
                    sh 'printenv'
                }
            }
        }
    }
 }