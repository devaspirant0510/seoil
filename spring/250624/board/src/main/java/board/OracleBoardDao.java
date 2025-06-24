package board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OracleBoardDao implements BoardDao{

	@Autowired
	DataSource ds;
	
	public OracleBoardDao() {
		System.out.println("oracle dao 생성자 생성!!");
	}
	@Override
	public int save(Board board) {
		String sql="insert into board(id,title,content,author,type,attachment,createdate,viewcnt) "
				+ "valus(board_id_seq,?,?,?,?,?,sysdate,0)";
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql.toString());
		ps.setString(1, board.getTitle());
		ps.setString(2, board.getContent());
		ps.setString(3, board.getAuthor());
		ps.setString(4, board.getType());
		ps.setString(5, board.getAttachment());
		int result=ps.executeUpdate();
		ps.close();
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public List<Board> findAll(int startnum, int endnum) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from ");
		sql.append("(select rownum rid, t1.* from ");
		sql.append("(select * from board order by id asc) t1) ");
		sql.append("where rid>=? and rid<=?");
		try {
		PreparedStatement ps
		=ds.getConnection().prepareStatement(sql.toString());
		ps.setInt(1, startnum);
		ps.setInt(2, endnum);
		ResultSet rs=ps.executeQuery();
		List<Board> list=new ArrayList<Board>();
		while(rs.next()){
			Board board=new Board();
			board.setId(rs.getInt("id"));
			board.setTitle(rs.getString("title"));
			board.setAuthor(rs.getString("author"));
			board.setContent(rs.getString("content"));
			board.setCreatedate(rs.getDate("createdate"));
			board.setViewcnt(rs.getInt("viewcnt"));
			board.setAttachment(rs.getString("attachment"));
			board.setType(rs.getString("type"));
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
	public Board findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		String sql="select count(*) count from board";
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

}
