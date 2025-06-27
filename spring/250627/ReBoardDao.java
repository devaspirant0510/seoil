package reboard;

import java.util.List;

public interface ReBoardDao {
	public int save(ReBoard reboard);
	public List<ReBoard> findAll(int startnum, int endnum);
	public ReBoard findById(int id); 
	public int update(ReBoard reboard);
	public int delete(int id);
	public int count();
	public int viewcntup(int id);
	public int replaySave(ReBoard board);
	public int searchCount(String searchfield, String search);
	public List<ReBoard> searchFindAll(int startnum, int endnum, String searchfield, String search);
}
