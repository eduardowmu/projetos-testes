package configuration.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import configuration.basic.world.HelloService;
import configuration.basic.world.WorldService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HelloService.class})
class MockIntegrationTest 
{	@Autowired
	private HelloService helloService;
	
	/*Devido ao alto consumo de processamento ou que este serviço 
	 *tenha um tempo de inicialização muito alta, usamos esta
	 *notação. Essa notação vai criar um objeto mock e colocá-la
	 *no contexto da aplicação.*/
	@MockBean
	private WorldService worldService;

	@Test
	public void someTest() 
	{	Mockito.when(worldService.getWorld()).thenReturn("You");
		String result = helloService.getHelloWorld();
		Assertions.assertTrue(result.equals("Hello You"));
	}
}