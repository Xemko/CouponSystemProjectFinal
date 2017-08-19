package client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import Util.CompanyUtilResponse;
import Util.CustomerUtilResponse;

public class AdminWSTest {

	private CompanyUtilResponse compResponse;
	private CustomerUtilResponse custResponse;


	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	URI baseUri = UriBuilder.fromUri("http://localhost:8080/CouponSystemREST").build();
	WebResource service = client.resource(baseUri);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllCompanies() {

		compResponse = service.path("rest").path("admin").path("getAllCompanies").queryParam("user", "admin")
				.queryParam("pass", "1234").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CompanyUtilResponse>() {
				});
		Assert.assertEquals("Success", compResponse.getMessage());
	}

	@Test
	public void testCreateCompany() {
		compResponse = service.path("rest").path("admin").path("createCompany").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("CompanyName", "TestCompany2")
				.queryParam("CompanyPass", "TestPass1").queryParam("CompanyEmail", "TestEmail1")
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<CompanyUtilResponse>() {
				});
		Assert.assertEquals("Success", compResponse.getMessage());
	}

	@Test
	public void testRemoveCompany() {
		compResponse = service.path("rest").path("admin").path("removeCompany").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("compID", "1").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CompanyUtilResponse>() {
				});
		Assert.assertEquals("Success", compResponse.getMessage());
	}

	@Test
	public void testUpdateCompany() {
		compResponse = service.path("rest").path("admin").path("updateCompany").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("CompanyEmail", "TestEmail2")
				.queryParam("CompanyPass", "TestPass2").queryParam("compID", "2").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CompanyUtilResponse>() {
				});
		Assert.assertEquals("Success", compResponse.getMessage());
	}

	@Test
	public void testGetCompanyByID() {
		compResponse = service.path("rest").path("admin").path("getCompany").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("compID", "3").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CompanyUtilResponse>() {
				});
		Assert.assertEquals("Success", compResponse.getMessage());
	}

	@Test
	public void testGetAllCustomers() {
		custResponse = service.path("rest").path("admin").path("getAllCustomers").queryParam("user", "admin")
				.queryParam("pass", "1234").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testCreateCustomer() {
		custResponse = service.path("rest").path("admin").path("createCustomer").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("CustomerName", "NewCustJunit")
				.queryParam("CustomerPass", "JUPass").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testGetCustomerByID() {
		custResponse = service.path("rest").path("admin").path("getCustomer").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("custID", "1").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testUpdateCustomer() {
		custResponse = service.path("rest").path("admin").path("updateCustomer").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("newPass", "UpdPassJU").queryParam("custID", "4")
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testRemoveCustomer() {
		custResponse = service.path("rest").path("admin").path("removeCustomer").queryParam("user", "admin")
				.queryParam("pass", "1234").queryParam("custID", "3").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

}
