package board;

import java.util.List;

public interface BoardDao {

	public int save(Board board);
	public List<Board> findAll(int startnum, int endnum);
	public Board findById(int id);
	public int update(Board board);
	public int delete(int id); 
	public int count();
	public int viewcntup(int id);
}
