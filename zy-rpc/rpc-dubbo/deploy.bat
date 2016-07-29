

mvn -Dmaven.home=E:\Software\apache-maven-3.2.5 -s E:\Software\apache-maven-3.2.5\conf\setting.xml -Dmaven.repo.local=E:\MavenRepository -DskipTests=true clean source:jar javadoc:jar install
