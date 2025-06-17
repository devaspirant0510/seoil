package d0616_03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ConsolePrint implements Print {

	PrintInfo info;
	
	public void print() {
		System.out.println("학번:"+info.getId());
		System.out.println("이름:"+info.getName());
		System.out.println("성적:"+info.getSungjuk());		
	}
	public void print(String id, String name, Sungjuk sungjuk) {
		System.out.println("학번:"+id);
		System.out.println("이름:"+name);
		System.out.println("국어:"+sungjuk.getKor());
		System.out.println("영어:"+sungjuk.getEng());
		System.out.println("수학:"+sungjuk.getMath());
	}

}
