package integration.tests.mockmvc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class MockMvcIntegrationTest 
{	@Autowired
	private MockMvc mockMvc;

	/*Os testes usando o MockMvc podem ser divididos em duas etapas:
	 *1 - Configurar as chamadas;
	 *2 - Configurar as ações.*/
	@Test
	public void getExample() throws Exception{
		/*1 - Configurando chamadas, cujo metodo abaixo perform()
	 	que recebe um RequestBuilder, e o MockMvc possui uma classe 
	 	MockRequestBuilder, que é basicamente um Factory para recuperar
	 	requests. Podemos usar o método Request, que é um método genérico.
	 	ou os métodos específicos de cada tipo de chamadas HTTP. O método
	 	perform() vai simular uma chamada da request e como resultado
	 	vai devolver um objeto do tipo ResultActions, que é um objeto que 
	 	vamos utilizar para configurar as ações que serão executadas. As
	 	ações que podemos usar nos testes são exibir um resultado em um log
	 	ou no console, executar verificações ou recuperar um resultado da
	 	chamada. A ação de exibir um resultado no log ou no console é feita
	 	através do método andDo() do ResultActions.*/
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andDo(MockMvcResultHandlers.print())
				//2 - Configurando ações
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		Assertions.assertThat(mvcResult.getModelAndView()
				.getModelMap().getAttribute("greeting")).isEqualTo("Hello");
	}
}