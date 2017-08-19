package rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Util.CustomerUtilResponse;
import c.Beans.Coupon;
import e.Exceptions.CouponSystemException;
import f.Facades.ClientType;
import f.Facades.CustomerFacade;
import g.Main.CouponSystem;

@Path("/customer")

public class CustomerService {

	private CustomerUtilResponse custResponse;

	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Path("/purchaseCoupon")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse purchaseCoupon(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("Coupon") int coupID) {

		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		Coupon couponToPurchase;
		try {

			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			couponToPurchase = custf.getCouponByID(coupID);
			custf.purchaseCoupon(couponToPurchase);
			custResponse = new CustomerUtilResponse("Success");
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}

		return custResponse;
	}

	@Path("/getAllCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getAllCoupons(@QueryParam("user") String name, @QueryParam("pass") String pass) {
		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		ArrayList<Coupon> allCoupons = new ArrayList<>();

		try {
			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			allCoupons = (ArrayList<Coupon>) custf.getAllCustomerCoupons();
			System.out.println(allCoupons.toString());
			custResponse = new CustomerUtilResponse(allCoupons, "Success");
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			// return custResponse;
		}
		// return custResponse;
		return custResponse;
	}

	@Path("/getAllByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getAllCouponsByType(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("type") String type) {
		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		ArrayList<Coupon> allCouponsByType = new ArrayList<>();

		try {
			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			allCouponsByType = (ArrayList<Coupon>) custf.getAllCustomerCouponsByType(type);
			System.out.println(allCouponsByType.toString());
			custResponse = new CustomerUtilResponse(allCouponsByType, "Success");
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/getAllByPrice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getAllCouponsByPrice(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("price") int price) {
		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		ArrayList<Coupon> allCouponsByPrice = new ArrayList<>();

		try {
			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			allCouponsByPrice = (ArrayList<Coupon>) custf.getAllCustomerCouponsByPrice(price);
			System.out.println(allCouponsByPrice.toString());
			custResponse = new CustomerUtilResponse(allCouponsByPrice, "Success");
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/getExistingCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getExistingCoupons(@QueryParam("user") String name, @QueryParam("pass") String pass) {
		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		ArrayList<Coupon> allCoupons = new ArrayList<>();

		try {
			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			allCoupons = (ArrayList<Coupon>) custf.getAllRelevantCoupons();
			System.out.println(allCoupons.toString());
			custResponse = new CustomerUtilResponse(allCoupons, "Success");
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/login")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse login(@QueryParam("user") String name, @QueryParam("pass") String pass) {
		CouponSystem cs = CouponSystem.getInstance();
		CustomerFacade custf;
		try {
			custf = (CustomerFacade) cs.login(name, pass, ClientType.CUSTOMER);
			boolean message = custf.loginForAndroid(name, pass);
			if (message) {
				custResponse = new CustomerUtilResponse("Success");
			}

		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

}
