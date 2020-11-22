#!groovy
def call(body) {
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    println pipelineParams.build_image
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