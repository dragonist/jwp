2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.
- src/main/java.next.WebServerLauncher.java 에  main method를 실행한다
    String webappDirLocation = "webapp/";
    Tomcat tomcat = new Tomcat(); //import org.apache.catalina.startup.Tomcat; embedded tomcat 인스턴스 생성
    tomcat.setPort(8080); //포트 번호 설정

    tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());//웹엡의 가장 상위 위치를 "webapp/"으로 설정
    logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

    tomcat.start(); //tomcat 시작!
    tomcat.getServer().await();
    - 톰캣이 실행되고 톰캣은 webapp/index.jsp를 실행시킨다.
    - indes.jsp에선 response.sendRedirect("/list.next"); 한다
    - web.xml에서 저런 url은  core.mvc.FrontController로 가라고 한다
    
