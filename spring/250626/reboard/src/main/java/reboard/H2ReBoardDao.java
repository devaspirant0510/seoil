package reboard;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("h2dao")
public class H2ReBoardDao implements ReBoardDao{

	@Autowired
	DataSource ds;
	
	@Override
	public int save(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReBoard> findAll(int startnum, int endnum) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int viewcntup(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replaySave(ReBoard board) {
		// TODO Auto-generated method stub
		return 0;
	}

}
