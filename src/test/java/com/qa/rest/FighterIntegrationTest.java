package com.qa.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.Fighter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:fighter-schema.sql",
		"classpath:fighter-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class FighterIntegrationTest {

	@Autowired // inject the MockMVC object into this class
	private MockMvc mvc; // object for sending mock http requests

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		final Fighter testFighter = new Fighter(null, "Henry", "Sword", "Plate", 100, 20, 20);
		String testFighterAsJSON = this.mapper.writeValueAsString(testFighter);

		final Fighter savedFighter = new Fighter(2, "Henry", "Sword", "Plate", 100, 20, 20);
		String savedFighterAsJSON = this.mapper.writeValueAsString(savedFighter);

		// method, path, headers, body
		RequestBuilder request = post("/createFighter").contentType(MediaType.APPLICATION_JSON)
				.content(testFighterAsJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedFighterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);

	}

	@Test
	void testGetAll() throws Exception {
		String savedFighterAsJSON = this.mapper
				.writeValueAsString(List.of(new Fighter(1, "Shaun", "Sword", "Plate-Mail", 100, 20, 20)));

		RequestBuilder request = get("/getAllFighters");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedFighterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testGetById() throws Exception {
		final Fighter savedFighter = new Fighter(1, "Shaun", "Sword", "Plate-Mail", 100, 20, 20);
		String savedFighterAsJSON = this.mapper.writeValueAsString(savedFighter);

		RequestBuilder request = get("/getFighter/" + savedFighter.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedFighterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdate() throws Exception {
		final Fighter testFighter = new Fighter(1, "Henry", "Sword", "Plate", 100, 20, 20);
		final String testFighterAsJSON = this.mapper.writeValueAsString(testFighter);

		RequestBuilder request = put("/updateFighter/1").contentType(MediaType.APPLICATION_JSON)
				.content(testFighterAsJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(testFighterAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/removeFighter/1")).andExpect(status().isNoContent());
	}

	void testCreateAbrdiged() throws Exception {
		final String testFighterAsJSON = this.mapper
				.writeValueAsString(new Fighter(1, "Henry", "Sword", "Plate", 100, 20, 20));
		final String savedFighterAsJSON = this.mapper.writeValueAsString(new Fighter(1, "Henry", "Sword", "Plate", 100, 20, 20));

		this.mvc.perform(post("/createFighter").contentType(MediaType.APPLICATION_JSON).content(testFighterAsJSON))
				.andExpect(status().isCreated()).andExpect(content().json(savedFighterAsJSON));

	}
}