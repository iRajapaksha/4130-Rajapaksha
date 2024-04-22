// pipeline {
//     agent any

//     tools {
//         nodejs "node"
//     }

//     environment {
//         CI = 'true'
//     }

//     stages {
//         stage('Clone repository') {
//             steps {
//                 git 'https://github.com/iRajapaksha/4130-Rajapaksha'
//             }
//         }

//         stage('Install dependencies') {
//             steps {
//                 sh 'npm install'
//             }
//         }

//         stage('Test') {
//             steps {
//                 // Add commands to run your tests here
//                 echo 'Running tests...'
//                 // For example: sh 'npm test'
//             }
//         }

//         stage('Build') {
//             steps {
//                 sh 'npm run build' // This line is hypothetical unless you have a build script in your package.json
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 // Ensure Docker is installed and Jenkins has appropriate permissions
//                 script {
//                     docker.build("4130-Rajapaksha:${env.BUILD_ID}")
//                 }
//             }
//         }

//         stage('Deploy') {
//             steps {
//                 // Add deployment steps here
//                 echo 'Deploying application...'
//                 // For example, you might use Docker or SSH to deploy
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Build and Deployment Success!'
//         }
//         failure {
//             echo 'Build or Deployment Failed.'
//         }
//         always {
//             // Clean up actions, such as sending notifications
//             echo 'Pipeline execution complete.'
//         }
//     }
// }



pipeline {
  agent any
  triggers{
    githubPush()
  }
  stages {
    stage('Docker Build'){
      steps {
        sh 'docker build -t irajapaksha/4130-rajapaksha .'
      }
    }
    stage('Docker Run'){
      steps{
        sh 'docker run -d -p 5000:3000 irajapaksha/4130-rajapaksha'
      }
    }
    stage('Final'){
      steps{
        sh 'docker ps'
      }
    }
    stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }
}