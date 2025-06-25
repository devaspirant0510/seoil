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
	public int save(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(ReBoard reboard) {
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
	@Override
	public int viewcntup(int id) {
		String sql="update board set viewcnt=viewcnt+1 where id=?";
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


}
