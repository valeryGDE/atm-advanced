properties([pipelineTriggers([cron('H H * * *')])])

pipeline {
    agent any
    stages {
        stage('SCM') {
            steps {
                checkout scm
            }
                }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh './gradlew sonar'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh './gradlew clean test -Psuite=testng-suite.xml'
                }
            }
            post {
                always {
                        script {
                             junit '**/build/reports/tests/test/testng-results.xml'
                             updateGitHubCommitStatus(state: currentBuild.currentResult, context: 'CI',
                                message: "$JOB_NAME ${currentBuild.currentResult}: ${currentBuild.fullDisplayName}")
                        }
                }
            }
        }
    }
}
