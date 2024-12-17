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
    environment {
        SONAR_PROJECT_KEY = 'gestionBib'
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner'

    }
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
//          stage('Quality Analysis') {
//                     environment {
//                         SONAR_TOKEN = credentials('squ_9f462f190371d3d22fa6d943ad90ed9115372b0b')  // Ajouté pour l'authentification SonarQube
//                     }
//                     steps {
//                         withSonarQubeEnv('SonarQube') {
//                             sh '''
//                             `   echo "Starting SonarQube Analysis..."
//                                 mvn sonar:sonar \
//                                 -Dsonar.host.url=http://localhost:9000 \
//                                 -Dsonar.login=$SONAR_TOKEN
//                                 echo "SonarQube Analysis completed"
//                             '''
//                         }
//                     }
//                 }
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