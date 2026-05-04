pipeline {
    agent any

    environment {
        MS_NAME = 'ReportSchedueling_Service'        // ← change this
        IMAGE_NAME = 'brahimbk/ReportSchedueling_Service'  // ← change this
        IMAGE_TAG = 'latest'
        DOCKER_CREDS = credentials('docker-hub-credentials')
    }

    stages {

        stage('1 — Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/pediNephro/ReportSchedueling_Service.git',
                    
            }
        }

        stage('2 — Build Maven') {
            steps {
                dir("${MS_NAME}") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('3 — Tests') {
            steps {
                dir("${MS_NAME}") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit "${MS_NAME}/target/surefire-reports/*.xml"
                }
                failure {
                    error 'Tests failed — stopping pipeline'
                }
            }
        }

        stage('4 — Docker Build') {
            steps {
                dir("${MS_NAME}") {
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage('5 — Docker Push') {
            steps {
                sh "echo ${DOCKER_CREDS_PSW} | docker login -u ${DOCKER_CREDS_USR} --password-stdin"
                sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }

    post {
        success {
            echo "Pipeline done — image ${IMAGE_NAME}:${IMAGE_TAG} is on Docker Hub"
        }
        failure {
            echo "Pipeline failed — check logs above"
        }
        always {
            sh "docker rmi ${IMAGE_NAME}:${IMAGE_TAG} || true"
        }
    }
}