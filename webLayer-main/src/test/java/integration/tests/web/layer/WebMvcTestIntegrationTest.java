package integration.tests.web.layer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import integration.tests.web.layer.person.Person;
import integration.tests.web.layer.person.PersonController;
import integration.tests.web.layer.person.PersonRepository;
/*Essa notação é focada nos componentes do spring MVC. Esta inicia um contexto apenas com as
 *configurações relevantes aos testes de aplicações com SPRING MVC. Junto com os componentes
 *do SPRING MVC esta irá configurar um MockMvc. Embora possamos iniciar todos os controllers
 *geralmente esta notação é utilizada para mokar os testes em apenas uma controller. Com a classe
 *de parametro PersonController.class faz com que apenas essa classe seja iniciada.*/
@WebMvcTest(PersonController.class)
class WebMvcTestIntegrationTest 
{	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonRepository personRepository;
 
	@Test
	public void someTest() throws Exception 
	{	Mockito.when(personRepository.findFirstByOrderByIdDesc()).thenReturn(new Person(21, "Tod", 84));
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tod"));
	}
}