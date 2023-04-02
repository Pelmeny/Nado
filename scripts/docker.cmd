docker run --rm `
    --name tomcat `
    --network demo-network `
    -p 8080:8080 `
    -p 9000:8000 `
    -e JPDA_ADDRESS="*:8000" `
    -e JPDA_TRANSPORT=dt_socket `
    -v "C:\Users\User\IdeaProjects\social-network\target\social-network-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/social.war" `
    tomcat:9.0.53-jdk16-openjdk `
    /usr/local/tomcat/bin/catalina.sh jpda run

docker run --rm `
    --name demo-postgres `
    --network demo-network `
    -e POSTGRES_USER=postgres `
    -e POSTGRES_PASSWORD=postgres `
    -p 35432:5432 `
    -v "C:\Users\User\IdeaProjects\social-network\dev-env\postgres\init.sql:/docker-entrypoint-initdb.d/1-init.sql" `
    postgres:13.4-alpine
