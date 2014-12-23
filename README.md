2014년 개발 경험 프로젝트
=========

1. 로컬 개발 환경에 Tomcat 서버를 시작한 후 http://localhost:8080으로 접근하면 질문 목록을 확인할 수 있다. http://localhost:8080으로 접근해서 질문 목록이 보이기까지의 소스 코드의 호출 순서 및 흐름을 설명하라.
    src/main/java.next.WebServerLauncher.java 에  main method를 실행한다 @cr
    String webappDirLocation = "webapp/"; @cr
    Tomcat tomcat = new Tomcat(); //import org.apache.catalina.startup.Tomcat; embedded tomcat 인스턴스 생성 @cr
    tomcat.setPort(8080); //포트 번호 설정 @cr
    tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());//웹엡의 가장 상위 위치를 "webapp/"으로 설정 @cr
    logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath()); @cr
    tomcat.start(); //tomcat 시작!@cr
    tomcat.getServer().await();@cr
    
    - 톰캣이 실행되고 톰캣은 webapp/index.jsp를 실행시킨다.
    - indes.jsp에선 response.sendRedirect("/list.next"); 한다
    - web.xml에서 저런 url은  core.mvc.FrontController로 가라고 한다
    - FrontController  init() 메소드가 실행된다.
        - ServletContextLoader 가 ServletContext객체를 이용하여 RequestMapping 인스턴스를 만들어 저장
    - FrontController  service() 메소드가 실행된다.
        String requestUri = req.getRequestURI();@cr
	logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);//메소드와  URL console찍기@cr
	
	Controller controller = rm.findController(urlExceptParameter(req.getRequestURI())); @cr
	                                                    //url로 RequestMapping 한 내용을 Controller에게 전달@cr
	                                                    //여기선 ListController를 담음@cr
	ModelAndView mav;@cr
	try {@cr
		mav = controller.execute(req, resp);    //ListController.execute() 메소드를 실행 (controller-> mav리턴)@cr
		View view = mav.getView();              //view 인스턴스에 mav의 view 담음@cr
		view.render(mav.getModel(), req, resp); //view 에 mav의 model을 담음(여기서 redirect 로 다음 응답@cr 완료 !)@cr
	} catch (Throwable e) {@cr
		logger.error("Exception : {}", e);      //혹시나 에러 생기면 애러 찍기 @cr
		throw new ServletException(e.getMessage());@cr
	}@cr
   

