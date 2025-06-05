package phonebook;

public class PhonebookService {
	PhonebookDAO dao;
	
	public PhonebookService() {
		dao=new PhonebookDAOH2();
	}
}
