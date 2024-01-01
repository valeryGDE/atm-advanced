pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                script {
                    sh 'gradle test -Psuite=testng-suite.xml'
                }
            }
            post {
                always {
                    step([$class: 'Publisher', reportFilenamePattern: '**/test-output/testng-results.xml'])
                }
            }
        }
    }
}