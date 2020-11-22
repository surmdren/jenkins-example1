package org.foo

class GetEnv implements Serializable {
   def steps
   GetEnv(steps) { this.steps = steps}
   def getEnvValue(message) {
         steps.sh "echo ${message}"
      }
   static boolean isEnabled(script) {
        if (script.env.ON_K8S) {
            return false
        }
    }
    static String networkName() {
        return System.getenv('DL_CACHE_NETWORK')
    }
    static String hostname() {
        return System.getenv('DL_CACHE_HOSTNAME')
    }
}