// org.foo.BuildUtils
package org.foo
// def timedGradleBuild(tasks) {
//     timestamps {
//         sh "${tool 'gradle6.7'}/bin/gradle ${tasks}"
//     }
// }

class buildUtils implements Serializable {
   def steps
   buildUtils(steps) { this.steps = steps}
   def timedGradleBuild(tasks) {
      def gradleHome = steps.tool 'gradle6.7'
      steps.timestamps {
         steps.sh "${gradleHome}/bin/gradle ${tasks}"
      }
   }
   
}