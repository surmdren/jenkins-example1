#!/usr/bin/env groovy
def call(String prompt1 = 'Please enter your data', String prompt2 = 'Please enter your data') {
    def resp = input message: '<message>', 
        parameters: [string(defaultValue: '',description: prompt1, name: 'RESPONSE1'), string(defaultValue: '', description: prompt2, name: 'RESPONSE2')]
    echo "${resp.RESPONSE1}"
    echo "${resp.RESPONSE2}"
    // do something with the input
}