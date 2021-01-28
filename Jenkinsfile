pipeline {
  agent any
  stages {
    stage('git pull') {
      steps {
        git(url: 'https://github.com/RicciLiao/bsm.git', branch: 'pipe1')
      }
    }

    stage('mvn clean') {
      steps {
        sh 'mvn clean'
      }
    }

    stage('mvn package') {
      steps {
        sh 'mvn package'
      }
    }

  }
}