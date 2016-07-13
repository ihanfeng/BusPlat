
mvn -DaltDeploymentRepository=zhiyin-mvn-repo::default::file:E:/Github/MavenRepository/repository/ -Dmaven.test.skip=true clean source:jar javadoc:jar deploy
