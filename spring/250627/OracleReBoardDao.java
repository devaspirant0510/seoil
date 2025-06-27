package reboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
//빈생성 이름이 별도로 없을 경우 객체명의 첫글자만 소문자 oracleReBoardDao
public class OracleReBoardDao implements ReBoardDao{

	@Autowired
	DataSource ds;
	
	@Override
	public int save(ReBoard board) {
		//아래의 sql문에서 전달받은 데이터와 전달받지 않고 사용하는 데이터 분리
		String sql="insert into "
		+ "reboard(title,content,author,attachment,"
		+ "id,createdate,viewcnt,type,"
		+ "isdel,updatedate,parentid,tab)"
		+ " values(?,?,?,?,"
		+ "reboard_id_seq.nextval,sysdate,0,'일반게시판',"
		+ "0,null,0,0)";
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql.toString());
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getAuthor());
			ps.setString(4, board.getAttachment());
			int result=ps.executeUpdate();
			ps.close();
			return result;
			}catch (Exception e) {
				e.printStackTrace();
				return 0;
			}	
	}

	@Override
	public List<ReBoard> findAll(int startnum, int endnum) {
		String sql="select * from"
				+ " (select rownum rid, t1.* from "
				+ " (select * from reboard where isdel=0"
				+ " start with parentid=0 connect by prior id=parentid"
				+ " order siblings by id desc) t1)"
				+ " where rid>=? and rid<=?";
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql.toString());
		ps.setInt(1, startnum);
		ps.setInt(2, endnum);
		ResultSet rs=ps.executeQuery();
		List<ReBoard> list=new ArrayList<ReBoard>();
		while(rs.next()){
			//builder형식으로 변환
			ReBoard board=new ReBoard.ReBoardBuilder()
			.id(rs.getInt("id"))
			.title(rs.getString("title"))
			.author(rs.getString("author"))
			.content(rs.getString("content"))
			.createdate(rs.getDate("createdate"))
			.viewcnt(rs.getInt("viewcnt"))
			.attachment(rs.getString("attachment"))
			.type(rs.getString("type"))
			.parentid(rs.getInt("parentid"))
			.tab(rs.getInt("tab"))
			.updatedate(rs.getDate("updatedate"))
			.build();
			
			list.add(board);
		}
		rs.close(); ps.close();
		return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ReBoard findById(int id) {
		String sql="select * from reboard where id=?";
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql.toString());
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			ReBoard board=new ReBoard.ReBoardBuilder().build(); //빈객체생성
			board.setId(rs.getInt("id"));
			board.setTitle(rs.getString("title"));
			board.setAuthor(rs.getString("author"));
			board.setContent(rs.getString("content"));
			board.setCreatedate(rs.getDate("createdate"));
			board.setViewcnt(rs.getInt("viewcnt"));
			board.setAttachment(rs.getString("attachment"));
			board.setType(rs.getString("type"));
			board.setUpdatedate(rs.getDate("updatedate"));
			board.setParentid(rs.getInt("parentid"));
			board.setTab(rs.getInt("tab"));
			rs.close(); ps.close();
			return board;
		}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		String sql="delete from reboard where id=?";
		try {
			PreparedStatement ps
			=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, id);			
			int result=ps.executeUpdate();
			ps.close();
			return result;
			}catch (Exception e) {
				e.printStackTrace();
				return 0;
			}	
	}
	
	@Override
	public int count() {
		String sql="select count(*) count from reboard";
		try {
		PreparedStatement ps=ds.getConnection().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			int totalCount=rs.getInt("count");
			rs.close();
			ps.close();
			return totalCount;
		}
		
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return 0;
	}
	@Override
	public int viewcntup(int id) {
		String sql="update reboard set viewcnt=viewcnt+1 where id=?";
		try {
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			ps.close();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int replaySave(ReBoard board) {
		String sql="insert into reboard(id,title,content,author,createdate,type,parentid,tab)"
				+ " values(reboard_id_seq.nextval,?,?,?,sysdate,'일반게시판',?,?)";
				try {
					PreparedStatement ps
					=ds.getConnection().prepareStatement(sql.toString());
					ps.setString(1, board.getTitle());
					ps.setString(2, board.getContent());
					ps.setString(3, board.getAuthor());
					ps.setInt(4, board.getParentid());
					ps.setInt(5, board.getTab());
					int result=ps.executeUpdate();
					ps.close();
					return result;
					}catch (Exception e) {
						e.printStackTrace();
						return 0;
					}	
	}

	@Override
	public int searchCount(String searchfield, String search) {
		String sql="select count(*) count from reboard"
				+ " where isdel=0 and "
				+ searchfield+ " like '%"+search+"%'";				
		try {
		PreparedStatement ps=ds.getConnection().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			int totalCount=rs.getInt("count");
			rs.close();
			ps.close();
			return totalCount;
		}
		
		}catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<ReBoard> searchFindAll(int startnum, int endnum, String searchfield, String search) {
		String sql=" select * from (select rownum rid, t1.* from "
				+ "(select * from reboard "
				+ " where isdel=0 and "
				+ searchfield + " like '%"+search+"%'"
				+ " start with parentid=0 "
				+ " connect by prior id=parentid"
				+ " order siblings by id desc) t1)"
				+ " where rid>=? and rid<=?";
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql);
		ps.setInt(1, startnum);
		ps.setInt(2, endnum);
		ResultSet rs=ps.executeQuery();
		List<ReBoard> list=new ArrayList<ReBoard>();
		while(rs.next()){
			//builder형식으로 변환
			ReBoard board=new ReBoard.ReBoardBuilder()
			.id(rs.getInt("id"))
			.title(rs.getString("title"))
			.author(rs.getString("author"))
			.content(rs.getString("content"))
			.createdate(rs.getDate("createdate"))
			.viewcnt(rs.getInt("viewcnt"))
			.attachment(rs.getString("attachment"))
			.type(rs.getString("type"))
			.parentid(rs.getInt("parentid"))
			.tab(rs.getInt("tab"))
			.updatedate(rs.getDate("updatedate"))
			.build();
			
			list.add(board);
		}
		rs.close(); ps.close();
		return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
