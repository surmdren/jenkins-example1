#!groovy
def call(Map pipelineParams) {
    println pipelineParams
    pipeline{
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
            stage("build image") {
                steps {
                  container('build') {
                    echo 'Hello World ! I am in develop branch.'
                    echo env.GIT_BRANCH
                    sh 'printenv'
                    sh 'sbt sbtVersion'
                    sh """
                    pwd
                    ls /app
                    """
                    //sh 'docker build -t hkappdlv006.asia.pwcinternal.com:443/novus/novus-prod:$BUILD_NUMBER .'
                     sh 'docker build -t pwcdsdevops/$imageName:$BUILD_NUMBER .'
                    // sh 'docker login -u $harboraccount -p $harborpasswd'
                    // sh 'docker push pwcdsdevops/$imageName:$BUILD_NUMBER'      
                  }
                }
            }
            stage("deploy") {
                steps {
                  container('deploy') {
                    echo 'Hello World ! I am in develop branch.'
                    echo env.GIT_BRANCH
                    sh 'kubectl get pods'
                    sh """
                    pwd
                    ls /app
                    """
                }
            }
        }
    }
}