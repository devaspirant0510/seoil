package reboard;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReBoardList {
	int id;
	String title;
	String author;
	Date createdate;
	String attatchment;
	int viewcnt;
}
