# Jenkins CI/CD Pipeline for Insure Me Project

**Overview**

This project demonstrates the setup of a Jenkins-based CI/CD pipeline for the "Insure Me" project. The pipeline integrates with GitHub for version control and DockerHub for containerization and image deployment.

---

**Prerequisites**

    EC2 Instance (Ubuntu or compatible)
    GitHub Repository for the "Insure Me" project
    DockerHub account
    SSH access to the EC2 instance
    Basic knowledge of Jenkins, Docker, and Maven

**Terminology & Tools**

    Jenkins: An open-source automation server widely used to implement continuous integration and continuous delivery (CI/CD) pipelines, allowing developers to automate repetitive tasks.
    
    GitHub: A platform for version control and collaboration, enabling teams to host and review code, manage projects, and build software together.
    
    Docker: A containerization tool that packages an application and its dependencies into a standardized unit, allowing it to run consistently across different environments.
    
    DockerHub: A cloud-based repository where Docker images can be stored, shared, and deployed, providing an easy way to distribute applications.
    
    Maven: A build automation tool primarily used for Java projects; it manages project dependencies, builds, and documentation, making development efficient and standardized.
    
    EC2: Elastic Compute Cloud by AWS provides scalable cloud computing resources, allowing users to run applications in virtual environments.
    
    Pipeline: In Jenkins, a pipeline automates the sequence of tasks (such as building, testing, and deploying) for a project, making continuous integration and deployment seamless.
    
    Webhook: A method for enabling communication between services, triggering events like builds in Jenkins whenever there are changes in the GitHub repository.
**Steps**<br>


Step 1: Launch an EC2 Instance

   Create and launch an EC2 instance.
   SSH into the instance:

    ssh -i private.pem user@public_ip

Step 2: Install Jenkins

   Update your system and install Jenkins:

    sudo apt update -y
    sudo apt install fontconfig openjdk-17-jre -y
    sudo wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
    sudo apt-get update -y
    sudo apt-get install jenkins -y
    sudo systemctl start jenkins
    sudo systemctl enable jenkins

![Screenshot from 2024-10-23 22-06-42](https://github.com/user-attachments/assets/ce96727d-f4c6-4ab2-a36e-850d95d7067f)


Step 3: Install Docker and Maven

   Remove any older versions of Docker, install the required Docker components and Maven:

    sudo apt-get update -y
    sudo apt-get install ca-certificates curl -y
    sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
    sudo chmod a+r /etc/apt/keyrings/docker.asc
    echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    sudo apt-get update -y
    sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
    sudo apt install maven -y
    sudo usermod -aG docker jenkins
    sudo systemctl restart jenkins

       

Step 4: Unlock Jenkins

   Navigate to /var/lib/jenkins/secrets to get the initial admin password:

    sudo cat /var/lib/jenkins/secrets/initialAdminPassword

    Open Jenkins in a web browser using the EC2 public IP and port 8080 and paste the password.

Step 5: Install Plugins

    Install the default plugins and create an admin user in Jenkins.

Step 6: Set Up the Pipeline

   Create a new job:
        Go to New Job > Pipeline.
        Write a pipeline script in the Pipeline section.

Step 7: GitHub Integration

   Use the Pipeline Syntax generator in Jenkins to checkout code from GitHub:
   
    checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/your-repo-url.git']]])


Step 8: DockerHub Integration

   Use withCredentials for DockerHub authentication and add a script to build and push images:

    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials-id', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh 'docker login -u $USERNAME -p $PASSWORD'
        sh 'docker build -t your-image-name .'
        sh 'docker push your-image-name'
    }

Step 9: Webhooks

    In the GitHub repository settings, create a Webhook pointing to the Jenkins server.
   ![Screenshot from 2024-10-24 14-52-00](https://github.com/user-attachments/assets/54f0860b-3970-42ca-9a5e-4a2a3ba0a658)


Step 10: Build and Deploy

    Save the pipeline script and click Build Now to trigger the pipeline.
    Verify that Docker images are pushed to DockerHub.
   ![Screenshot from 2024-10-24 15-29-02](https://github.com/user-attachments/assets/1e04f5b2-5bc9-47d2-a700-2180b162f4d6)
   ![Screenshot from 2024-10-24 15-30-31](https://github.com/user-attachments/assets/56b0c6d8-92b8-4fac-8fc8-cba58c9a03e5)
   ![Screenshot from 2024-10-24 15-57-09](https://github.com/user-attachments/assets/6c499879-f153-493d-9e56-c9f083e7f4d3)




    
