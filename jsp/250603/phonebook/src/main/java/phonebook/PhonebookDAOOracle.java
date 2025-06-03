package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PhonebookDAOOracle implements PhonebookDAO{

	Connection conn;
	
	public PhonebookDAOOracle() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			
			conn=DriverManager.getConnection(url, "system","light");
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	@Test
	public void test() {
		//System.out.println(conn);
		//System.out.println(findAll());
		Phonebook pb
		=new Phonebook(0, "kim", "010-2222-1234", "kim@my.com", "회사");
		System.out.println("result:"+insert(pb));
		
		System.out.println(findAll());
	}
	@Override
	public int insert(Phonebook pb) {
					
		String sql="insert into phonebook(id,name,hp,email,memo) values(pb_id_seq.nextval,?,?,?,?)";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
					ps.setString(1, pb.getName());
					ps.setString(2, pb.getHp());
					ps.setString(3, pb.getEmail());
					ps.setString(4, pb.getMemo());
					int result=ps.executeUpdate();
					return result;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return 0;
	}

	@Override
	public List<Phonebook> findAll() {
		String sql="select * from phonebook";
		try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
		List<Phonebook> list=new ArrayList<Phonebook>();
		while(rs.next()) {
			Phonebook pb=new Phonebook();
			pb.setId(rs.getInt("id"));
			pb.setName(rs.getString("name"));
			pb.setHp(rs.getString("hp"));
			pb.setEmail(rs.getString("email"));
			pb.setMemo(rs.getString("memo"));
			list.add(pb);
		}
		return list;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Phonebook findById(int id) {
		String sql="select * from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Phonebook pb=new Phonebook();
				pb.setId(id);
				pb.setName(rs.getString("name"));
				pb.setHp(rs.getString("hp"));
				pb.setEmail(rs.getString("email"));
				pb.setMemo(rs.getString("memo"));
				return pb;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateById(Phonebook pb) {
		String sql="update phonebook set name=?,hp=?,email=?,memo=? where id=?";
		//시나리오: pb객체에 수정할 내용만 입력된 상태로 전달될 경우 
		//수정할 sql문만을 별도로 만들어야 하는가?
		//null값일 경우 기존 데이터를 가지고 와서 pb에 셋팅 후 위의 sql문 처리
		Phonebook prepb=findById(pb.getId()); //이전의 값을 가지고 옴
		if(pb.getName()==null) pb.setName(prepb.getName());
		if(pb.getHp()==null) pb.setHp(prepb.getHp());
		if(pb.getEmail()==null) pb.setEmail(prepb.getEmail());
		if(pb.getMemo()==null) pb.setMemo(prepb.getMemo());
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setString(2, pb.getHp());
			ps.setString(3, pb.getEmail());
			ps.setString(4, pb.getMemo());
			ps.setInt(5, pb.getId());
			int result=ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
		//참고사항 값이 "":빈값, null:객체가 생성되지 않은 값
		//빈값은 name.equals(""), name==null
	}

	@Override
	public int deleteById(int id) {
		String sql="delete from phonebook where id=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result=ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
