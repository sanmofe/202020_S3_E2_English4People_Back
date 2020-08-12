pipeline {
   agent any 
   environment {
      GIT_REPO = '202020_S3_E2_English4People_Back'
      SONARQUBE_URL = 'http://172.24.101.209:8082'
      MAVEN_CONFIG = '/home/estudiante/DATA/maven'
      GIT_CREDENTIAL_ID = '692cb316-0794-4522-9cf0-83c2618a09e5'
      ARCHID_TOKEN = credentials('041703df-dd96-47c3-97b1-b7fbf12069d5')
   }
   stages {
      stage('Checkout') { 
         steps {
            scmSkip(deleteBuild: true, skipPattern:'.*\\[ci-skip\\].*')

            git branch: 'master', 
               credentialsId: env.GIT_CREDENTIAL_ID,
               url: 'https://github.com/Uniandes-isis2603/' + env.GIT_REPO
            
         }
      }
      stage('Build') {
         // Build artifacts
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v ${MAVEN_CONFIG}:/root/.m2 -u root') {
                  sh '''
                     java -version
                     mvn -v
                     node -v
                     npm -v
                     newman -v

                     mvn clean package
                  '''
               }
            }
         }
      }
      stage('Testing') {
         // Run unit tests and integration tests
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v ${MAVEN_CONFIG}:/root/.m2 -u root') {                  
                  sh '''
                     mvn clean test integration-test
                  '''
               }
            }
         }
      }
      stage('Static Analysis') {
         // Run static analysis
         steps {
            script {
               docker.image('citools-isis2603:latest').inside('-v ${MAVEN_CONFIG}:/root/.m2 -u root') {
                  sh '''
                     mvn clean verify sonar:sonar -Dsonar.host.url=${SONARQUBE_URL}
                  '''
               }
            }
         }
      }
      stage('Git Analysis') {
         // Run git analysis
         steps {
            script {
               docker.image('gitinspector-isis2603').inside('--entrypoint=""') {
                  sh '''
                     mkdir -p ./reports/
                     datetime=$(date +'%Y-%m-%d_%H%M%S')
                     gitinspector --file-types="java" --format=html --AxU -w -T > ./reports/gitinspector${datetime}.html
                  '''
               }
            }
            withCredentials([usernamePassword(credentialsId: env.GIT_CREDENTIAL_ID, passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
               sh('git config --global user.email "ci-isis2603@uniandes.edu.co"')
               sh('git config --global user.name "ci-isis2603"')
               sh('git add -A')
               sh('git commit -m "[ci-skip] GitInspector report added"')
               sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/Uniandes-isis2603/${GIT_REPO} master')
            }
         }
      }
   }
   post { 
      always { 
         // Clean workspace
         cleanWs deleteDirs: true
      }
   }
}
