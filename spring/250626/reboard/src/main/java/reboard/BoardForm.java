package reboard;

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
//입력을 하는 필드의 name을 확인하고 그에 맞춰 vo객체를 생성한다.
public class BoardForm {
	private String title;
	private String content;
	private String author;
	//private String attachment; //file전송관련 name은 폼에서 제외
}



