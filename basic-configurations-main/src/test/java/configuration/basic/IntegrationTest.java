package configuration.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
/*@RunWith(SpringRunner.class)
 *Essa notação foi depreciada pela notação acima*/
/*@SpringBootTest
 *Já esta notação foi depreciada para notação
 *abaixo*/
/*Iniciando o contexto de testes do spring*/
@ContextConfiguration(classes = {TextService.class})
class IntegrationTest 
{	@Autowired
	private TextService textService;

	@Test
	void test() {textService.someMethod();}
}
