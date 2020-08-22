В закладке Gradle :
  1.запустить HW17-MessageServer/grpclibrary/Tasks/build/build<br>
  2.апустить HW17-MessageServer/dbserver/Tasks/build/bootJar<br>
  2.апустить HW17-MessageServer/frontend/Tasks/build/bootJar<br>
  2.апустить HW17-MessageServer/messagesystem/Tasks/build/bootJar<br>
Далее в порядке очереди из каталога HW17-MessageServer:<br>
  1.java -jar messagesystem/build/libs/mssystem.jar<br>
  2.java -jar dbserver/build/libs/dbservice.jar --dbinit.action=yes<br>
  3.java -jar dbserver/build/libs/dbservice.jar<br> 
  4.java -jar frontend/build/libs/frontend.jar --server.port=8080<br>
  5.java -jar frontend/build/libs/frontend.jar --server.port=8081<br>