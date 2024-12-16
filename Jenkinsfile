// pipeline {
//     agent any
//     environment {
//         MAVEN_HOME = tool 'Maven'
//     }
//     stages {
//         stage('Checkout') {
//             steps {
//                 git 'https://github.com/MARYAM12334/GestionBibliotheque'
//             }
//         }
//         stage('Build') {
//             steps {
//                 sh '${MAVEN_HOME}/bin/mvn clean compile'
//             }
//         }
//         stage('Test') {
//             steps {
//                 sh '${MAVEN_HOME}/bin/mvn test'
//             }
//         }
//         stage('Quality Analysis') {
//             steps {
//                 withSonarQubeEnv('SonarQube') {
//                     sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
//                 }
//             }
//         }
//         stage('Deploy') {
//             steps {
//                 echo 'Déploiement simulé réussi'
//             }
//         }
//     }
//     post {
//         success {
//             emailext to: 'votre-email@example.com',
//                 subject: 'Build Success',
//                 body: 'Le build a été complété avec succès.'
//         }
//         failure {
//             emailext to: 'votre-email@example.com',
//                 subject: 'Build Failed',
//                 body: 'Le build a échoué.'
//         }
//     }
// }

pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK11'


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
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
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
            emailext to: 'maryamouhdan@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
        }
        failure {
            emailext to: 'maryamouhdan@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
        }
    }
}