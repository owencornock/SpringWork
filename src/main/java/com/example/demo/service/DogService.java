package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Dog;
import com.example.demo.repo.DogRepo;

@Service

public class DogService implements ServiceIF<Dog>{

	private DogRepo repo;

	@Autowired
	public DogService(DogRepo repo) {
		super();
		this.repo = repo;
	}

	public Dog create(Dog d) {
		Dog created = this.repo.save(d);
		return created;
	}

	public List<Dog> getAll() {
		return this.repo.findAll();
	}

	public Dog getOne(Integer id) {
		Optional<Dog> found = this.repo.findById(id);
		return found.get();
	}

	public Dog replace(Integer id, Dog newDog) {
		Dog existing = this.repo.findById(id).get();
		existing.setAge(newDog.getAge());
		existing.setBreed(newDog.getBreed());
		existing.setName(newDog.getName());
		Dog updated = this.repo.save(existing);
		return updated;
	}

	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}

	public List<Dog> getDogByName(String name) {
		List<Dog> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}

	public List<Dog> getDogByAge(Integer age) {
		List<Dog> found = this.repo.findByAge(age);
		return found;
	}

	public List<Dog> getDogByBreed(String breed) {
		List<Dog> found = this.repo.findByBreedIgnoreCase(breed);
		return found;
	}
}