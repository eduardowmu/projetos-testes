package integration.tests.databases;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import integration.tests.databases.product.Product;
import integration.tests.databases.product.ProductRepository;
/*Essa notação contém configurações que inicializam um contexto muito reduzido da
 *aplicação, usando esta notação, apenas o que for relacionado com SPRING DATA JPA
 *vai ser inicializado, a data JPA test esta configurada para ignorar qualquer 
 *configuração de banco de dados que tenha na aplicação e para configurar automaticamente
 *um banco H2 derby ou HSQL. Para não acontecer nenhum erro, devemos ter alguma dessas
 *dependências no pom.xml OU build.gradle.*/
@DataJpaTest
/*Para alterar essa configuração e informar que queremos usar o banco de dados configurado 
 *na aplicação devemos usar a notação abaixo. Na propriedade replace devemos colocar o
 *valor abaixo para que nenhum datasource deve ser substituído. A propriedade replace recebe
 *também os valores N, que é o default e que diz que qualquer datasource deve ser substituído.
 **/
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JPaIntegrationTest 
{	@Autowired
	private ProductRepository productRepository;
	
	/*Essa notação indica que tal método será executado toda vez que
	 *um teste for finalizado*/
	@AfterEach
	public void cleanUp() {productRepository.deleteAll();}

	@Test
	@Sql(statements = "insert into product (name, price) values('Mouse',15)")
	public void findByNameIgnoreCaseReturnAProduct() 
	{	Product product = productRepository.findByNameIgnoreCase("mouse");
		Assertions.assertThat(product.getName()).isEqualTo("Mouse");
		Assertions.assertThat(product.getPrice()).isEqualTo(15);
	}

	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void saveAProductWithNameNullThrowsAnException() 
	{	Product product = new Product("", 60);

		Assertions.assertThatThrownBy(()->{

			productRepository.save(product);
		}).isInstanceOf(ConstraintViolationException.class)
		.hasMessageContaining("Name is mandatory")
		.hasMessageContaining("Price must be lower than fifty");
	}

	@Test
	@Sql(statements = "insert into product (name,price) values('Scanner',40)")
	@Sql(statements = "insert into product (name,price) values('Mouse',15)")
	public void findAllProductsOrderedAscendentByPriceReturnAnOrderedListOfProducts() 
	{	List<Product> products = productRepository.findAllOrderedByPriceAsc();
		Assertions.assertThat(products).extracting(Product::getName).containsExactly("Mouse", "Scanner");
	}
}