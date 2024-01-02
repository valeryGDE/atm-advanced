properties([pipelineTriggers([cron('H/5 * * * *')])])

pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                script {
                    sh './gradlew clean test -Psuite=testng-suite.xml'
                }
            }
            post {
                always {
                    step([$class: 'Publisher', reportFilenamePattern: '**/build/reports/tests/test/testng-results.xml'])
                }
            }
        }
    }
}