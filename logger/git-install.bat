@echo off
echo [INFO] Install parent pom.xml to local repository.

cd %~dp0

call mvn -DaltDeploymentRepository=zhiyin-mvn-repo::default::file:E:/Github/MavenRepository/repository/ -Dmaven.test.skip=true clean source:jar javadoc:jar deploy

pause