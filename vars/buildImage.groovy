#!groovy
def call(Map pipelineParams) {
    println pipelineParams
    pipeline{
        agent none
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
          string(name: 'Jenkins_CI_Node', defaultValue: 'agent010', description: 'Jenkins Continues Integration Node')
          string(name: 'Jenkins_CD_Node', defaultValue: 'agent010', description: 'Jenkins Continues Deployment Node')
          string(name: 'Build_Image', defaultValue: 'hkappdlv006.asia.pwcinternal.com:443/novus/novus-sbt:v2.3', description: 'Jenkins Build Image')
        }

        stages {
            stage("sbt dist") {
                agent {
                    docker {
                        image "${params.Build_Image}"
                        label "${params.Jenkins_CI_Node}"
                        //label 'agent007'
                        args '-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock -e harboraccount='+"${harboraccount}"+' -e harborpasswd='+"${harborpasswd}"+' -e AES_PASSWORD='+"${AES_PASSWORD}"+' -e AES_SALT='+"${AES_SALT}"
                    }
                }
                options {
                    timeout(time: 1, unit: 'HOURS')
                }
                steps {
                    echo 'Hello World ! I am in develop branch.'
                    echo env.GIT_BRANCH
                    sh 'printenv'
                    sh 'sbt sbtVersion'
                    sh "git clone http://$gitlabaccount:$gitlabtoken@172.17.0.3/surmdren/web.git"
                    sh 'cd web'
                    sh 'ls'
                    sh 'docker build -t hkappdlv006.asia.pwcinternal.com:443/novus/novus-prod:env.BUILD_NUMBER .'
                }
            }
        }
    }
}