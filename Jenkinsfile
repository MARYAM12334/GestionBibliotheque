pipeline {
    agent any
    environment {
        SONAR_PROJECT_KEY = 'gestionBib'
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner'

    }
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }
    stages {
        stage('Checkout') {
            steps {

               checkout scm

            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

         stage('Quality Analysis') {


                     steps {
                                         withCredentials([string(credentialsId: 'gestionBib_token', variable: 'SONAR_TOKEN')]) {

                                                 withSonarQubeEnv('SonarQube') {
                                                         sh """
                                      mvn sonar:sonar \
                                     -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                     -Dsonar.login=${SONAR_TOKEN}
                                     """
                                                 }
                                         }
                                 }
                 }

        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
            success {
                emailext (
                    subject: "✅ Build Réussi #${env.BUILD_NUMBER}",
                    body: """Le build ${env.JOB_NAME} #${env.BUILD_NUMBER} a réussi!

                        Voir les détails: ${env.BUILD_URL}

                        Tests: ${currentBuild.tests}
                        SonarQube: [URL SonarQube]
                        """,
                    to: "maryamouhdan@email.com"
                )
            }
            failure {
                emailext (
                    subject: "❌ Build Échoué #${env.BUILD_NUMBER}",
                    body: """Le build ${env.JOB_NAME} #${env.BUILD_NUMBER} a échoué.

                        Voir les logs: ${env.BUILD_URL}console
                        """,
                    to: "maryamouhdan@email.com"
                )
            }
        }
}