package d0617_04;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main1 {

	/*
	private static final Logger log
	=Logger.getLogger(d0617_04.Main1.class.getName());
	*/
	private static final Logger log
	=Logger.getLogger("d0617_04.Main1");
	
	public static void main(String[] args) {
		//System.out.println(d0617_04.Main1.class.getName());
		//System.out.println(d0617_04.Main1.class.getSimpleName());
		//System.out.println(log);
		//log의 기본 단계: info(정보제공), warnning(경고), severe(심각)
		log.info("정보제공 로그");
		log.warning("경고 로그");
		log.severe("심각 로그");
		System.out.println("-----------------------");
		log.setLevel(Level.ALL);
		log.info("정보제공 로그");
		log.warning("경고 로그");
		log.severe("심각 로그");
	}

}
