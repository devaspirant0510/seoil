package d0617_04;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main3 {
	private static final Logger log
	=Logger.getLogger(d0617_04.Main3.class.getName());
	
	public static void main(String[] args) {
		try {
			FileHandler handler=new FileHandler("login.log");
			handler.setFormatter(new SimpleFormatter());
			log.addHandler(handler);
			
			String id=null;
			String password="1234";
			
			if(id.equals("admin") && password.equals("1234")) {
				log.info("login success!!");
			}else {
				log.warning("login fail!!:"+id+","+password);
			}
			}catch (Exception e) {
				log.severe(e.getMessage());
			}
	}
	
}
