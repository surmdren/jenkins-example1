#!groovy
def call(Map pipelineParams) {
    println pipelineParams
    pipeline{
        agent {
            kubernetes {
            defaultContainer 'jnlp'
            }
        }
        
        triggers {       
         pollSCM(env.BRANCH_NAME == 'master' ? '30 19 * * *' : '') // daily at 19:30 pm  --- QA  
        }

        stages {
            stage('Example') {
                options {
                    timeout(time: 1, unit: 'HOURS')
                }
                steps {
                    echo 'Hello World ! I am in develop branch.'
                    echo env.GIT_BRANCH
                    sh 'printenv'
                }
            }
        }