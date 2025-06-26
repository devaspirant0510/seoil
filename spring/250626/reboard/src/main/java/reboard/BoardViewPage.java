package reboard;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
public class BoardViewPage {
	private int id;
	private String title;
	private String author;
	//수정일시 해당 내용을 날짜로(updatedate값이 null인경우 createdate사용하고 그렇지 않은 경우 updatedate)
	private Date date;
	private int viewcnt;
	private String content;
	private String attachment;
	private int tab;
	//아래코드가 필요한가?
	//댓글쓰기를 누를 때 폼이 생성되는데 
	//이때 아이디를 전달하여 parentid,tab내용 획득 후 사용(o)
	//아니면 아래의 코드를 전달받아 폼으로 전송해서 사용하는 방법
	//private int parentid;
	//private int tab;

	
	
	
	
	
	
	
}
