pipeline{
    /* === 1. Where to run the build === */
    agent { label 'linux' }     // name/tag of the shared agent VM

    /* == 2. Tools to auto-install on that agent == */
    tools{
        jdk     'jdk21'         // find the jdk name on managejenkins -> tools
        maven   'maven-3.9'
    }

    /* === 3. Build Options === */

   options{
        timestamps()    // prefix console lines with HH:mm::ss
   }

   /* === 4. Environment Variables (opt.) === */
   environment{
       BROWSER = 'chrome'
   }

   /* === 5. The life-cycle === */

    stages{
        stage('Checkout'){
            steps{
                git url: 'https://github.com/babanutaion1996/OrangeHRMWebAutomation.git',
                branch: 'main',
                credentialsId: 'ion-pat'
            }
        }
        stage('UI Tests'){
            steps{
                sh 'mvn clean test'     // runs default lifecycle up to **test**
            }

        }

        stage('Publish HTML Report'){
            steps{
                publishHTML target: [
                    reportDir: 'target',
                    reportFiles: 'HRM-AutomationReport.html',
                    reportName: 'Extent - Orange HRM'
                ]
                }
            }
        }

        /* post jenkins action after run */

        post{
            always {

                emailext body: 'Summary', subject: 'Pipeline Status', to: 'babanuta.ion1996@gmail.com'
            }

        }

    }

