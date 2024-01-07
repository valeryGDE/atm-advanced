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
                    step([$class: 'Publisher', reportFilenamePattern: '**/build/reports/tests/test/testng-results.xml'])
                    script {
                        updateGitHubCommitStatus(state: currentBuild.currentResult, context: 'CI',
                            message: "$JOB_NAME ${currentBuild.currentResult}: ${currentBuild.fullDisplayName}")
                    }
                }
            }
        }
    }
}
