pipeline {
    agent any

    environment {
        // Define Docker image name (modify as necessary)
        DOCKER_IMAGE = 'vecfromword/k8s-app' // Replace with your Docker image name
        DOCKER_REGISTRY = '' // Define your Docker registry URL if needed
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Clones the repository
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                // Building the project with Maven
                script {
                    // If Maven is configured as a tool in Jenkins
                    def mvnHome = tool 'Maven' // Replace 'Maven' with the name you configured in Jenkins
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Building the Docker image
                    docker.build("${env.DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master' }
            }
            steps {
                script {
                    // If you need to push the Docker image to a registry
                    docker.withRegistry(DOCKER_REGISTRY) {
                        docker.image("${env.DOCKER_IMAGE}:${env.BUILD_ID}").push()
                    }
                }
            }
        }

        // Additional stages (e.g., testing, deployment) can be added as necessary
    }

    post {
        always {
            // Actions to perform after the pipeline execution
        }
    }
}
