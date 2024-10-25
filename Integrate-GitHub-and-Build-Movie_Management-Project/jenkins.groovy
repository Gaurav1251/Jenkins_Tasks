pipeline {
    agent any
     
    
    
    stages {
        stage('Code-Checkout') {
            steps {
               git branch: 'main', changelog: false, credentialsId: 'movie', poll: false, url: 'https://github.com/Gaurav1251/Jenkins_Tasks'
            }
        }
        
        stage('Code-Build') {
            steps {
               
                   
                    
                    sh 'cd Integrate-GitHub-and-Build-Movie_Management-Project/fe/ && jar -cvf ../ticketbooking2.war *'

                    sh 'cp ./Integrate-GitHub-and-Build-Movie_Management-Project/ticketbooking2.war Integrate-GitHub-and-Build-Movie_Management-Project/fe/'
                    sh 'pwd'
            }
        }
        
        stage('Deploy'){
            steps {
                script {
                    dir('Integrate-GitHub-and-Build-Movie_Management-Project/be') {
                        sh 'docker build -t be .'
                        sh 'docker run -d -p 3306:3306 be'
                    }
                }

               
                script {
                    dir('Integrate-GitHub-and-Build-Movie_Management-Project/fe') {
                        sh 'docker build -t fe .'
                        sh 'docker run -d -p 8089:8080 fe'
                    }
                }
            
            }
           
        }
    
        
        
        
    
      
     
   }
}
