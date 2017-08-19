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

import Util.CouponUtilResponse;

public class CompanyWSTest {

	private CouponUtilResponse coupResponse;

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
	public void testGetAllCoupons() {
		coupResponse = service.path("rest").path("company").path("getAllCoupons").queryParam("user", "companyC")
				.queryParam("pass", "345").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());
	}

	@Test
	public void testCreateCoupon() {
		coupResponse = service.path("rest").path("company").path("createCoupon").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("CouponTitle", "JUCoupon5")
				.queryParam("CouponStarts", "2017-04-12").queryParam("CouponEnds", "2019-05-30")
				.queryParam("CouponType", "PCHardware").queryParam("CouponMessage", "YourMessage3")
				.queryParam("CouponPrice", "1203").queryParam("CouponStatus", "no")
				.queryParam("CouponImage", "AddImage3").queryParam("CouponAmount", "23")
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());
	}

	@Test
	public void testGetCouponsByType() {
		coupResponse = service.path("rest").path("company").path("getCouponsByType").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("type", "Games").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());

	}

	@Test
	public void testGetCouponsByPrice() {
		coupResponse = service.path("rest").path("company").path("getCouponsByPrice").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("Price", "250").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());

	}

	@Test
	public void testGetCouponsByDate() {
		coupResponse = service.path("rest").path("company").path("getCouponsByDate").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("Date", "2020-06-30").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());

	}

	@Test
	public void testUpdateCoupon() {
		coupResponse = service.path("rest").path("company").path("updateCoupon").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("CouponNewEnd", "2020-07-21").queryParam("CouponNewPrice", "222")
				.queryParam("CouponID", "4").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());
	}

	@Test
	public void testRemoveCoupon() {
		coupResponse = service.path("rest").path("company").path("removeCoupon").queryParam("user", "companyC")
				.queryParam("pass", "345").queryParam("CouponID", "6").accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<CouponUtilResponse>() {
				});
		Assert.assertEquals("Success", coupResponse.getMessage());
	 }
}
