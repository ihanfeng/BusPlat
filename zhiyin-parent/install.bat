@echo off
echo [INFO] Install parent pom.xml to local repository.

cd %~dp0
%call mvn clean install  -Dmaven.home=E:\Software\apache-maven-3.3.1 -s E:\Software\apache-maven-3.3.1\conf\settings.xml -Dmaven.repo.local=E:\Software\MavenLocalRepository -DskipTests=true
call mvn clean install  -Dmaven.home=E:\Software\apache-maven-3.2.5 -s E:\Software\apache-maven-3.2.5\conf\setting.xml -Dmaven.repo.local=E:\MavenRepository -DskipTests=true
pause