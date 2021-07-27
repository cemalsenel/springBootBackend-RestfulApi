package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import model.Person;
import repository.PersonRepository;

@Service
public class PeopleService {

	
	public static PersonRepository personRepository;
	
	//Dependency Injection 
	@Autowired
	public PeopleService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public List<Person> allPeople(){
		return personRepository.findAll();
	}
	
	
	//adding data to database 
	public Person addPeople(Person person) {
		return personRepository.save(person);
	}
	
	//getting client by id
	public Optional<Person> getPeopleById(Integer id) {
		return personRepository.findById(id);
	}
	
	//delete 
	public String deletePeopleById(Integer id) {
		if(personRepository.findById(id) == null) {
			throw new IllegalStateException("The person who has "+ id +" number cannot be found.");
		}
		personRepository.deleteById(id);
		return "Person was deleted whose id is : "+id;
	}
	
	//update
	public Person updatePeopleById(Integer id,@RequestBody Person updatedPerson) {
		Person oldPerson = personRepository
							.findById(id)
							.orElseThrow(() -> new IllegalStateException("The person cannot be found"));
		
		return personRepository.save(updatedPerson);
	}
}
