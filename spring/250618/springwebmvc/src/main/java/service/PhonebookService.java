package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.PhonebookInter;
import vo.Phonebook;

@Service
public class PhonebookService {

	@Autowired
	PhonebookInter dao;
	
	public int save(Phonebook phonebook) {
		return dao.save(phonebook);
	}

}
