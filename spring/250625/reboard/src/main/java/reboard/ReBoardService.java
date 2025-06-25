package reboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class ReBoardService {
	
	//OracleReBoardDao dao;
	//@Autowired(required = false)
	@Autowired
	//여러개의 빈이 있을 경우 빈 선택 어노테이션
	@Qualifier("oracleReBoardDao") 
	ReBoardDao dao; //유연한 결합을 위해서 인터페이스 사용

	public PageList getPageList(int requestPage) {
		try {
			PageList pageList =new PageList.PageListBuilder()
			.totalCount(dao.count())
			.pagePerCount(10)
			.build();
			
			int totalPage=0;
			if((pageList.getTotalCount()%pageList.getPagePerCount()) == 0){
				totalPage=pageList.getTotalCount()/pageList.getPagePerCount();
			}else{
				totalPage=(pageList.getTotalCount()/pageList.getPagePerCount())+1;
			}
			pageList.setTotalPage(totalPage);
			
			pageList.setCurrentPage(requestPage);
			int startnum=((requestPage-1)*pageList.getPagePerCount())+1;
			int endnum=requestPage*pageList.getPagePerCount();
			
			int startPage=((requestPage-1)/5 * 5) + 1;
			int endPage=startPage + (5-1);
			if(endPage>totalPage) endPage=totalPage;
			pageList.setStartPage(startPage);
			pageList.setEndPage(endPage);
			
			boolean isPre=requestPage>5 ? true : false;
			boolean isNext=endPage<totalPage ? true : false;
			pageList.setPre(isPre);
			pageList.setNext(isNext);
			//db에 있는 필드 모두를 가지고 오는 명령
			List<ReBoard> list=dao.findAll(startnum, endnum);
			
			//페이지에 표시할 데이터는 BoardList
			List<ReBoardList> boardlists=new ArrayList<ReBoardList>();
			for(ReBoard board:list) {
				ReBoardList boardlist
				=new ReBoardList(
						board.getId(), 
						board.getTitle(), 
						board.getAuthor(), 
						board.getCreatedate(), 
						board.getAttachment(), 
						board.getViewcnt());
				boardlists.add(boardlist);
			}
			pageList.setList(boardlists);
			return pageList;//정보를 리턴
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}
	
}
