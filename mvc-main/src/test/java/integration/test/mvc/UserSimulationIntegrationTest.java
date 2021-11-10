package integration.test.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import integration.test.mvc.config.SecConfiguration;

@SpringBootTest(classes = {SecConfiguration.class})
@AutoConfigureMockMvc
class UserSimulationIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test/*Essa notação abaixo cria um contexto de segurança a parte para os testes*/
	@WithUserDetails("seth")//por conta disso devemos incluir os usuáruios no conbtexto
	public void withUserDetails() throws Exception 
	{	mockMvc.perform(get("/"))
		.andExpect(content().string(Matchers.containsString(
				"<button type=\"button\" id=\"logout-btn\" "
				+ "class=\"btn btn-danger\">Logout</button>")));
	}

	@Test/*Este notação abaixo pode tanto ser usado nos metodos de teste ou na classe,*/
	@WithUserDetails(userDetailsServiceBeanName = //para o caso dos testes, utilizar o mesmo usuário
		"customUserDetailsService", value = "admin")
	public void customUserDetailsService() throws Exception 
	{	mockMvc.perform(get("/"))
		.andExpect(content().string(Matchers.containsString(
				"<button type=\"button\" id=\"logout-btn\" "
				+ "class=\"btn btn-danger\">Logout</button>")));
	}
	
	/*Outra forma de simular um usuário é usando a notação @WithMockUser. Que assim como
	 *a @WithUserDetails, essa vai criar um contexto de segurança novo para os testes, mas
	 *a diferença é que devemos configurar o usuário através da notação.*/
	@Test 
	@WithMockUser(username = "withMockUser", password = "pass", roles = {"ADMIN"})
	public void withMockUser() throws Exception 
	{	mockMvc.perform(get("/"))
		.andExpect(content().string(Matchers.containsString(
				"<button type=\"button\" id=\"logout-btn\" "
				+ "class=\"btn btn-danger\">Logout</button>")));
	}

	@Test
	public void withMockMvc() throws Exception 
	{	mockMvc.perform(get("/")
				.with(SecurityMockMvcRequestPostProcessors
						.user("mockMvcUser")
						.password("pass")
						.roles("ADMIN")))
		.andExpect(content().string(Matchers.containsString(
				"<button type=\"button\" id=\"logout-btn\" "
				+ "class=\"btn btn-danger\">Logout</button>")));
	}
}