setup datasource:
1) import data into mysql
2) in netbeans, select project, expand, right click on "Server Resources" -> New -> Other -> JDBC Connection Pool -> select mysql db connection
3) right click on "Server Resources" -> New -> Other -> JDBC Resource -> select existing JDBC Connection Pool
4) add glassfish-web.xml in WEB-INF

http://nickebbitt.wordpress.com/2013/11/06/glassfish-server-open-source-edition-4-0-jndi-resources-need-to-be-defined-in-glassfish-web-xml/