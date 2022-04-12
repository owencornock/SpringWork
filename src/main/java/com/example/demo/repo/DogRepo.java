package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Dog;

@Repository
public interface DogRepo extends JpaRepository<Dog, Integer> {

	List<Dog> findByNameIgnoreCase(String name);

	List<Dog> findByAge(Integer age);

	List<Dog> findByBreedIgnoreCase(String breed);
}
