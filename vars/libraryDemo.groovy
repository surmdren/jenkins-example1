#!groovy
def call(Map pipelineParams) {
    echo "Hello"
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