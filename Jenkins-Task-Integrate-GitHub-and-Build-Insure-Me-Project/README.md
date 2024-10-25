# Integrate GitHub and Build "Insure Me" Project

**Overview**

This guide outlines the process for integrating GitHub with Jenkins and automating the build of the "Insure Me" project. It uses Docker for containerization and Jenkins for Continuous Integration.<br>

---
**Terminology & Tools**

    Jenkins: Automates parts of software development related to building, testing, and deploying, making it a fundamental part of DevOps pipelines.
    
    GitHub: A popular platform for code collaboration and version control, simplifying team workflows and ensuring code quality.
    
    Docker: Packages software into containers, providing a reliable, lightweight environment for applications across different systems.
    
    Maven: An effective tool for managing dependencies and automating build processes, often used in Java projects.
    
    EC2: AWS’s cloud computing service for running applications on virtual machines, commonly used for deploying and scaling cloud applications.
    
    Pipeline: A series of automated tasks executed in Jenkins, enabling the automated building, testing, and deployment of applications.

**Steps**
Step 1: Launch an EC2 Instance

   Launch and connect to an EC2 instance:


    ssh -i private.pem user@public_ip

Step 2: Install Jenkins

   Update the system and install Jenkins using the following commands:

    sudo apt update -y
    sudo apt install fontconfig openjdk-17-jre -y
    sudo wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
    sudo apt-get update -y
    sudo apt-get install jenkins -y
    sudo systemctl start jenkins
    sudo systemctl enable jenkins

Step 3: Install Docker and Maven

   Install Docker and Maven:

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

Step 4: Configure Jenkins

    Unlock Jenkins by retrieving the password from /var/lib/jenkins/secrets/initialAdminPassword and accessing Jenkins via http://<instance-public-ip>:8080.

Step 5: Create a Jenkins Pipeline

    Create a new Pipeline job in Jenkins.
    Use the Pipeline Syntax generator to configure GitHub repository cloning.

Step 6: Trigger Build

    Click Build Now to start the build process. Once successful, Jenkins will indicate the status with a green checkmark.
   ![Screenshot from 2024-10-23 22-21-10](https://github.com/user-attachments/assets/9737cb53-ce7d-40fa-b952-738e7824f25c)


   ![Screenshot from 2024-10-24 15-30-42](https://github.com/user-attachments/assets/17758482-033c-4f2a-b53e-926afaef9260)


