package de.stedo.databasetest;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DatabaseTestApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@DisplayName("checks for create and findOne Question")
	void test1() {

		Question frage = new Question();
		frage.setQuestion("funzt der Test?");
		frage.setAnswer("ja");

		ResponseEntity<Question> postResponse = restTemplate.postForEntity("/api/questions", frage, Question.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(postResponse.getBody().getId()).isNotNull();
		assertThat(Objects.requireNonNull(postResponse.getBody()).getQuestion()).isEqualTo("funzt der Test?");

		ResponseEntity<Question> getResponse = restTemplate.getForEntity("/api/questions/" + postResponse.getBody().getId(), Question.class);
		assertThat(getResponse.getBody().getQuestion()).isEqualTo("funzt der Test?");

	}

	@Test
	@DisplayName("checks for delete Function")
	void test2() {

		Question frage = new Question();
		frage.setQuestion("funzt der Test?");
		frage.setAnswer("ja");

		ResponseEntity<Question> postResponse = restTemplate.postForEntity("/api/questions", frage, Question.class);

		restTemplate.delete("/api/questions/" + postResponse.getBody().getId());

		ResponseEntity<Question> getResponse = restTemplate.getForEntity("/api/questions/" + postResponse.getBody().getId(), Question.class);

		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(getResponse.getBody()).isNull();
	}

}
