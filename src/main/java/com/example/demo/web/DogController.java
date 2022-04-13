package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Dog;
import com.example.demo.service.DogService;

@RestController
public class DogController {

	private DogService service;

	@Autowired
	public DogController(DogService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Dog> createDog(@RequestBody Dog d) {
		Dog created = this.service.create(d);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(created, HttpStatus.CREATED);
		return response;

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Dog>> getAllDoggy() {
		return ResponseEntity.ok(this.service.getAll());

	}

	@GetMapping("/get/{id}")
	public Dog getDog(@PathVariable Integer id) {
		return this.service.getOne(id);
	}

//update
	@PutMapping("/replace/{id}")
	public ResponseEntity<Dog> replaceDog(@PathVariable Integer id, @RequestBody Dog newDog) {
		Dog body = this.service.replace(id, newDog);
		ResponseEntity<Dog> response = new ResponseEntity<Dog>(body, HttpStatus.ACCEPTED);
		return response;
	}

//delete
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeDog(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Dog>> getDogByName(@PathVariable String name) {
		List<Dog> found = this.service.getDogByName(name);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Dog>> getDogByAge(@PathVariable Integer age) {
		List<Dog> found = this.service.getDogByAge(age);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByBreed/{breed}")
	public ResponseEntity<List<Dog>> getDogByBreed(@PathVariable String breed) {
		List<Dog> found = this.service.getDogByBreed(breed);
		return ResponseEntity.ok(found);
	}

}
