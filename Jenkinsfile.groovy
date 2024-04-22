
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