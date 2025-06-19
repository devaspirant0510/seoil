package service;

import java.util.List;

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

	public List<Phonebook> list() {
		return dao.findAll();
	}

	public Phonebook findById(int id) {
		return dao.findById(id);
	}

	public int update(Phonebook phonebook) {
		return dao.update(phonebook);
	}

	public int delete(int id) {
		return dao.delete(id);
	}

}
