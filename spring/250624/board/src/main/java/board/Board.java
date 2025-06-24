package board;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Board {
	private int id;
	private String title;
	private String author;
	private Date createdate;
	private String content;
	private String attachment;
	private int viewcnt;
	private String type;
}
