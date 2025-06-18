package d0617_04;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main2 {
	private static final Logger log
	=Logger.getLogger(d0617_04.Main2.class.getName());
	
	public static void main(String[] args) {
		try {
		FileHandler handler=new FileHandler("app.log");
		handler.setFormatter(new SimpleFormatter());
		log.addHandler(handler);
		log.info("info log");
		log.warning("warning log");
		int result=1/0;
		}catch (Exception e) {
			log.severe("severe log");
		}
		

	}

}
