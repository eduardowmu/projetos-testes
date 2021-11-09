package integration.tests.web.layer;

import java.net.URI;
import java.net.URISyntaxException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import integration.tests.web.layer.person.Person;
import integration.tests.web.layer.person.PersonRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestRestTemplateIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private PersonRepository personRepository;

	@AfterEach
	public void cleanUp() {
		personRepository.deleteAll();
	}

	/*O testeRestTemplate tem 2 métodos específicos para GET:
	 *1 - getForEntity(), que realiza chamada para endereço, converte o resultado
	 *no tipo passado como parametro e vai encapsular a resposta em um ResponseEntity.
	 *Para um endereço podemos passar um objeto URI ou string. Em ambos os casos não
	 *precisamos preencher o endereço completo, basta apenas o complemento do endereço
	 *URI internamente o TestRestTemplate vai preencher um objeto URI com base URL o 
	 *protocolo e o valor passado no parametro, neste caso é a classe Person*/
	@Test
	@Sql(statements = "INSERT INTO person (name, age) VALUES ('Mark', 40)")
	public void getTest() throws URISyntaxException 
	{	ResponseEntity<Person> responseGetForEntity = testRestTemplate.getForEntity(new URI("/"), Person.class);
		Assertions.assertThat(responseGetForEntity.getBody().getName()).isEqualTo("Mark");
		Assertions.assertThat(responseGetForEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

		Person responseGetForObject = testRestTemplate.getForObject("/", Person.class);
		Assertions.assertThat(responseGetForObject.getName()).isEqualTo("Mark");
	}

	/*Para chamadas POST também oferece um PostForEntity e um postForObject() que vão funcionar
	 *da mesma forma que os métodos getForEntity() e getForObject()*/
	@Test
	public void postTest() 
	{	ResponseEntity<Person> responsePostForEntity = testRestTemplate.postForEntity("/",
				new Person("John", 62), Person.class);
		Assertions.assertThat(responsePostForEntity.getBody().getName()).isEqualTo("John");
		Assertions.assertThat(responsePostForEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		Person responsePostForObject = testRestTemplate.postForObject("/",
				new Person("Doe", 45), Person.class);
		Assertions.assertThat(responsePostForObject.getName()).isEqualTo("Doe");
	}
	
	/*O TestRestTemplate também tem métodos PUT justamente para fazermos testes às chamadas
	 *PUT.*/
	@Test
	@Sql(statements = "INSERT INTO person (id, name, age) VALUES (99, 'Mark', 57)")
	public void putTest() {
		testRestTemplate.put("/", new Person(99,"Len", 57));

		Person person = personRepository.findById(99).get();
		Assertions.assertThat(person.getName()).isEqualTo("Len");
	}

	@Test
	@Sql(statements = "INSERT INTO person (id, name, age) VALUES (50, 'Len', 57)")
	public void deleteTest() {
		testRestTemplate.delete("/{id}", 50);

		Long count = personRepository.count();
		Assertions.assertThat(count).isEqualTo(0);
	}

	@Test
	@Sql(statements = "INSERT INTO person (name, age) VALUES ('Mark', 57)")
	public void exchangeTest() {
		ResponseEntity<Person> response = testRestTemplate.exchange("/", HttpMethod.GET,
				HttpEntity.EMPTY, Person.class);
		Assertions.assertThat(response.getBody().getName()).isEqualTo("Mark");
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}