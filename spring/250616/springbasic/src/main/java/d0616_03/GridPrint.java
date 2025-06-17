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
public class GridPrint implements Print{
	PrintInfo info;
	public void print() {
		System.out.println("+---------------------------+");
		System.out.println("|학번| 이름 | 국어 | 영어 | 수학 |");
		System.out.println("+---------------------------+");
		System.out.println("|"+ info.getId() +"|"+info.getName()+"|"+info.getSungjuk().getKor()+"|"+info.getSungjuk().getEng()+"|"+info.getSungjuk().getMath()+"|");
		System.out.println("+---------------------------+");		 
	}
	
	public void print(String id, String name, Sungjuk sungjuk) {
		System.out.println("+---------------------------+");
		System.out.println("|학번| 이름 | 국어 | 영어 | 수학 |");
		System.out.println("+---------------------------+");
		System.out.println("|"+ id +"|"+name+"|"+sungjuk.getKor()+"|"+sungjuk.getEng()+"|"+sungjuk.getMath()+"|");
		System.out.println("+---------------------------+");		
	}

}
