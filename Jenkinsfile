pipeline {
    agent {
        // Use a Kubernetes agent or any other supported agent that has access to Kaniko
        kubernetes {
            label 'jenkinsrun'
            defaultContainer 'kaniko'
            yaml """
            apiVersion: v1
            kind: Pod
            spec:
              containers:
              - name : git
                image: bitnami/git:latest
              - name: kaniko
                image: gcr.io/kaniko-project/executor:latest
                args: ["--context=dir://workspace/", "--dockerfile=Dockerfile", "--destination=docker.io/yourusername/your-image:latest"]
                volumeMounts:
                - name: docker-config
                  mountPath: /kaniko/.docker
              volumes:
              - name: docker-config
                secret:
                  secretName: docker-config-secret
            """
        }
    }
    environment {
        DOCKER_CONFIG = '/kaniko/.docker/' // Path to the Docker configuration
    }
    stages {
        stage('Checkout') {
            steps {
                container('git'){
                // Checkout the code from the repository
                checkout scm
            }
        }
    }
        stage('Build and Push') {
            steps {
                // Build the Docker image using Kaniko
                container('kaniko') {
                    sh 'echo "{\"auths\":{\"https://index.docker.io/v1/\":{\"username\":\"$DOCKER_USERNAME\",\"password\":\"$DOCKER_PASSWORD\"}}}" > /kaniko/.docker/config.json'
                    // The Kaniko build step will be handled by the args specified in the container YAML above
                }
            }
        }
    }
    post {
        always {
            // Clean up resources or notify upon completion
            echo 'Pipeline completed.'
        }
    }
}