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
              - name: git
                image: bitnami/git:latest
                command: 
                - sleep
                args:
                - 99d
              - name: kaniko
                image: gcr.io/kaniko-project/executor:debug
                command:
                - /busybox/cat
                tty: true
                volumeMounts:
                - name: docker-config
                  mountPath: /kaniko/.docker
              volumes:
              - name: docker-config
                secret:
                  secretName: docker-config-secret
                  items:
                    - key: .dockerconfigjson
                      path: config.json
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
            environment {
                PATH = "/busybox:$PATH"
                REGISTRY = 'proflanky/javademo'
            }
            steps {
                // Build the Docker image using Kaniko
                 container(name: 'kaniko', shell: '/busybox/sh') {
                    sh '''#!/busybox/sh
                    /kaniko/executor --dockerfile `pwd`/Dockerfile --cache=true --context /home/jenkins/agent/workspace/ --destination=proflanky/javademo:1.0.${BUILD_NUMBER}
                    '''
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