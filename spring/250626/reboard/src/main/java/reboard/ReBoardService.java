package reboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public void write(BoardForm form, MultipartFile file) {
		//파일저장시 파일 저장 위치
		String path="D:\\storage\\";
		String filename=file.getOriginalFilename();
		String fullname=path+filename;
		
		try {
		file.transferTo(new File(fullname));
		}catch (Exception e) {
			e.printStackTrace();
		}
		//폼에서 전송된 값과, 파일의 이름을 데이터베이스 객체로 전달하여 저장
		ReBoard board=new ReBoard.ReBoardBuilder()
				.title(form.getTitle())
				.content(form.getContent())
				.author(form.getAuthor())
				.attachment(filename)
				.build();
		dao.save(board);
	}

	public BoardViewPage getViewPage(int id) {
		
		//조회수 1증가 코드작성
		dao.viewcntup(id);
		//뷰정보 요청하는 코드 작성
		ReBoard board=dao.findById(id);
		//생성날짜로 할지, 업데이트로 할지 결정
		BoardViewPage page=new BoardViewPage.BoardViewPageBuilder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.author(board.getAuthor())
				.attachment(board.getAttachment())
				.viewcnt(board.getViewcnt())
				.tab(board.getTab())
				.build();
		if(board.getUpdatedate()==null) {
			page.setDate(board.getCreatedate());
		}else {
			page.setDate(board.getUpdatedate());
		}
		return page;
	}
	
}
