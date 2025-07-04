package hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

	@Autowired
	MainDao dao;

	public void insert(Student student) {
		dao.insert(student);		
	}
}
