#!groovy
def call(Map pipelineParams) {
    println pipelineParams.repo
    def repo=pipelineParams.repo.toString()
    println repo
    pipeline {
        agent {
            kubernetes {
                //customWorkspace 'some/other/path'
                defaultContainer 'build'
                yamlFile 'KubernetesPod.yaml'
            }
        }
        options {
            timeout(time: 1, unit: 'HOURS')
        }
        environment {
            AES_PASSWORD = credentials('AES_PASSWORD')
            AES_SALT = credentials('AES_SALT')
            harboraccount = credentials('harboraccount')
            harborpasswd = credentials('harborpasswd')
            gitlabaccount = credentials('gitlabaccount')
            gitlabtoken = credentials('gitlabtoken')
        }
        triggers {
            pollSCM(env.BRANCH_NAME == 'master' ? '30 19 * * *' : '') // daily at 19:30 pm  --- QA
        }
        parameters {
            string(name: 'imageName', defaultValue: 'web', description: 'Jenkins Build Image Name')
        }

        stages {
            stage('build image') {
                steps {
                    container('build') {
                        echo 'Hello World ! I am in develop branch.'
                        echo env.GIT_BRANCH
                        echo env.repo
                        sh 'printenv'
                        sh 'sbt sbtVersion'
                        sh '''
                            docker build -t pwcdsdevops/$pipelineParams.repo/$imageName:$GIT_BRANCH .
                            docker login -u $harboraccount -p $harborpasswd
                        '''
                        
                    }
                }
            }
            // stage('deploy') {
            //     steps {
            //         container('deploy') {
            //             echo 'Hello World ! I am in develop branch.'
            //             echo env.GIT_BRANCH
            //             sh 'kubectl get pods'
            //             sh 'pwd'
            //         }
            //     }
            // }
        }
    }
}
