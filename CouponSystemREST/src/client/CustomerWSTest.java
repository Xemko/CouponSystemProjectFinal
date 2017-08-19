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

import Util.CustomerUtilResponse;

public class CustomerWSTest {

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
	public void testPurchaseCoupon() {
		custResponse = service.path("rest").path("customer").path("purchaseCoupon").queryParam("user", "customerE")
				.queryParam("pass", "09876").queryParam("Coupon", "6").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test

	public void testGetAllCoupons() {
		custResponse = service.path("rest").path("customer").path("getAllCoupons").queryParam("user", "customerE")
				.queryParam("pass", "09876").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testGetAllCouponsByType() {
		custResponse = service.path("rest").path("customer").path("getAllByType").queryParam("user", "customerE")
				.queryParam("pass", "09876").queryParam("type", "Study").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}

	@Test
	public void testGetAllCouponsByPrice() {
		custResponse = service.path("rest").path("customer").path("getAllByPrice").queryParam("user", "customerE")
				.queryParam("pass", "09876").queryParam("price", "200").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CustomerUtilResponse>() {
				});
		Assert.assertEquals("Success", custResponse.getMessage());
	}
}
