#!groovy
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    pipeline{
        agent {
            kubernetes {
            defaultContainer 'kube'
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
                    echo 'Hello World ! I am in develop branch, container kube'
                    echo env.BRANCH_NAME
                    sh 'printenv'
                }
            }
        }
    }
 }