package jspwebsocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class MyWebsocket {
//세션리스트를 무엇으로 관리할 것인가?array,list,(중복o),map,set(중복x)
//map은 key, value로 이루어진 컬렉션 프레임워크
//최종적으로 set은 이용(중복이 되지 않으므로)
	static Set<Session> sessions=new HashSet<Session>();	
	
	@OnOpen
	public void open(Session session) {
		System.out.println("소켓 open!!");
		sessions.add(session);
		System.out.println("현재접속자수:"+sessions.size()); 
		//접속은 종료도 고려, 종료시 session제거(close함수처리)
	}
	
	@OnMessage
	public void message(String message,Session session) {
		System.out.println("소켓 메시지 수신!!");	
		System.out.println("client에서 온 메시지:"+message);
		//메시지 보내는 방법
		try {
			//여러명의 사용자에게 메시지 보내기
			for(Session s:sessions) {
			
			//if(s.isOpen() && session!=s)	
			if(s.isOpen())
				s.getBasicRemote().sendText(message);
			}
			//추가사항:자신에게는 메시지를 전송하지 않도록 처리해 볼것
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//보내온 클라이언트의 정보 확인
		System.out.println(session);
		//여러세션을 관리(함수안에서 리스트를 선언하면 한번 실행 후 정보가 삭제가 되므로 전역변수로 선언)
		
		
	}
	
	@OnError
	public void error(Throwable throwable) {
		System.out.println("소켓 error 발생!!");	
	}
	@OnClose
	public void close(Session session) {
		System.out.println("소켓 close!!");
		sessions.remove(session);
		System.out.println("현재접속자수:"+sessions.size());
	}
	
}
