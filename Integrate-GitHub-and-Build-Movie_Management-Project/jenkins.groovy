pipeline {
    
     agent any

    stages {
        stage('Code-Checkout') {
            steps {
               git branch: 'main', changelog: false, poll: false, url: 'https://github.com/Gaurav1251/test.git'
            }
        }
        
        stage('Code-Build') {
            steps {
               
                   
                    
                    sh 'cd fe && jar -cvf ../ticketbooking2.war *'

                    sh 'cp ./ticketbooking2.war fe/'
                    
            }
        }
        
        stage('Deploy'){
            steps {
                script {
                    dir('be') {
                        sh 'docker build -t be .'
                        sh 'docker run -d -p 3306:3306 be'
                    }
                }

               
                script {
                    dir('fe') {
                        sh 'docker build -t fe .'
                        sh 'docker run -d -p 8089:8080 fe'
                    }
                }
            
            }
           
        }
    
        
        
        
    
      
     
   }
}
