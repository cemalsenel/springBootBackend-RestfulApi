package controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Person;
import service.PeopleService;

@RestController
@RequestMapping("/people")

//Front-end yayın portu
@CrossOrigin(origins = "http://localhost:8081")
public class PersonController {

	public PeopleService peopleService;
	
	@Autowired
	public PersonController(PeopleService peopleService) {
		this.peopleService = peopleService;
	}
	
	@GetMapping
	public List<Person> getAllPeople() {
		return peopleService.allPeople();
	}
	
	@PostMapping("/add")
	public Person addNewPerson(@RequestBody Person person) {
		return peopleService.addPeople(person);
	}
	
	@GetMapping(path = "/search/{id}")
	public Optional<Person> getAllPeopleById(@PathVariable Integer id) {
		return peopleService.getPeopleById(id);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public String deletePeopleById(@PathVariable Integer id) {
		return peopleService.deletePeopleById(id);
	}
	
	@PutMapping(path="/update/{id}")
	public Person updatePeopleById(@PathVariable Integer id, @RequestBody Person newPerson) {
		return peopleService.updatePeopleById(id, newPerson);
	}
	
	@PatchMapping(path="/updatePatch/{id}")
	public Person updatePeopleByIdPatch(@PathVariable Integer id,@Validated @RequestBody Person newPerson) {
		return peopleService.updatePeopleByIdPatch(id, newPerson);
	}
	
}
