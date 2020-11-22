def call(Map body) {
    // pipeline {
    //   agent any
    //   parameters {
    //       string(name: 'Jenkins_CI_Node', defaultValue: 'agent010', description: 'Jenkins Continues Integration Node')
    //       string(name: 'Jenkins_CD_Node', defaultValue: 'agent010', description: 'Jenkins Continues Deployment Node')
    //   }
    //   stages {
    //     stage('Even Stage') {
    //       steps {
    //         echo "The build number is even ${body.packagename}"
    //         echo "The Jenkins_CI_Node is ${params.Jenkins_CI_Node}"
    //       }
    //     }
    //   }
    // }
    // pipeline {
    //   agent none
    //   parameters {
    //       string(name: 'Jenkins_CI_Node', defaultValue: 'agent010', description: 'Jenkins Continues Integration Node')
    //       string(name: 'Jenkins_CD_Node', defaultValue: 'agent010', description: 'Jenkins Continues Deployment Node')
    //   }
    //   stages {
    //     stage('Even Stage') {
    //       agent any
    //       steps {
    //         echo "The build number is even ${body.packagename}"
    //         echo "The Jenkins_CI_Node is ${params.Jenkins_CI_Node}"
    //       }
    //     }
    //     stage('Test stage2'){
    //         agent any
    //         stages{
    //             stage('In Test stage2'){
    //                 steps {
    //                     echo "In Test stage2"
    //                 }

    //             }
    //         }
    //     }
    //   }
    // }

}