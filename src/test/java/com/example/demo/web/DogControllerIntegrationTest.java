package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:dog-schema.sql", "classpath:dog-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
public class DogControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Dog testDog = new Dog(null, "Staff", "Owen", 4);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog testCreatedDog = new Dog(3, "Staff", "Owen", 4);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<Dog> testDoggy = List.of(new Dog(1, "Owen", "Staff", 5), new Dog(2, "Dave", "Staff", 8));
		String json = this.mapper.writeValueAsString(testDoggy);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void toStringTest() throws Exception {
		Dog doggy = new Dog(1, "Owen", "Staff", 18);
		doggy.toString();
	}
	@Test
	void getIdTest() throws Exception {
		RequestBuilder req = get("/get/1");
		Dog testCreatedDog = new Dog(1, "Owen", "Staff", 5);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(testCreatedDog);
				
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void replaceTest() throws Exception {
		Dog testDog = new Dog(null, "Staff", "Dave", 4);
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);
		
		Dog createDog = new Dog(1, "Staff", "Dave", 4);
		String testCreatedDogAsJSON = this.mapper.writeValueAsString(createDog);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedDogAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void removeTest() throws Exception {
		RequestBuilder req = delete("/remove/1");
		
		
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}
	@Test
	void getNameTest() throws Exception {
		RequestBuilder req = get("/getByName/Owen");
		
		List<Dog> testDoggy = List.of(new Dog(1, "Owen", "Staff", 5));
		String json = this.mapper.writeValueAsString(testDoggy);
		
				
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test 
	void getBreedTest() throws Exception {
		RequestBuilder req = get("/getByBreed/Staff");
		
		List<Dog> testDoggy = List.of(new Dog(1, "Owen", "Staff", 5), new Dog(2, "Dave", "Staff", 8));
		String json = this.mapper.writeValueAsString(testDoggy);
		
				
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void getAgeTest() throws Exception {
		RequestBuilder req = get("/getByAge/5");
		
		List<Dog> testDoggy = List.of(new Dog(1, "Owen", "Staff", 5));
		String json = this.mapper.writeValueAsString(testDoggy);
		
				
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
}
