#!groovy
def call(Map config) {
    echo "Hello"
    println config
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