package rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Util.CouponUtilResponse;
import c.Beans.Coupon;
import e.Exceptions.CouponSystemException;
import f.Facades.ClientType;
import f.Facades.CompanyFacade;
import g.Main.CouponSystem;

@Path("/company")
public class CompanyService {

	private CouponUtilResponse coupResponse;

	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Path("/getAllCoupons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse getAllCoupons(@QueryParam("user") String name, @QueryParam("pass") String pass) {
		ArrayList<Coupon> couponsList = new ArrayList<>();
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		try {
			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			couponsList = (ArrayList<Coupon>) cf.getAllCompanyCoupons();
			System.out.println(couponsList.toString());
			coupResponse = new CouponUtilResponse("Success", couponsList);
		} catch (CouponSystemException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}

	@Path("/createCoupon")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse createCoupon(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("CouponTitle") String couponTitle, @QueryParam("CouponStarts") String receivedStartDate,
			@QueryParam("CouponEnds") String receivedEndDate, @QueryParam("CouponType") String type,
			@QueryParam("CouponMessage") String message, @QueryParam("CouponPrice") int price,
			@QueryParam("CouponStatus") String expStatus, @QueryParam("CouponImage") String image,
			@QueryParam("CouponAmount") int amount) {
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		Date startDate;
		Date endDate;
		Coupon couponToCreate = new Coupon();
		ArrayList<Coupon> utilList = new ArrayList<>();

		// String dateString = "1999-03-14";

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");

		try {
			startDate = sf1.parse(receivedStartDate);
			endDate = sf2.parse(receivedEndDate);

			java.sql.Date sqlStart = new java.sql.Date(startDate.getTime());
			java.sql.Date sqlEnd = new java.sql.Date(endDate.getTime());

			couponToCreate.setStart_date(sqlStart);
			couponToCreate.setEnd_date(sqlEnd);

		} catch (ParseException e) {
			System.out.println("Date parsing error!!!");
		}

		couponToCreate.setTitle(couponTitle);
		couponToCreate.setType(type);
		couponToCreate.setMessage(message);
		couponToCreate.setPrice(price);
		couponToCreate.setExpired(expStatus);
		couponToCreate.setImage(image);

		try {

			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			int coupID = cf.createCoupon(couponToCreate, amount);
			couponToCreate.setId(coupID);
			utilList.add(couponToCreate);
			coupResponse = new CouponUtilResponse("Success", utilList);

		} catch (CouponSystemException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}

	@Path("/getCouponsByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse getCouponsByType(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("type") String type) {
		ArrayList<Coupon> coupList = new ArrayList<>();
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		try {
			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			coupList = (ArrayList<Coupon>) cf.getAllCouponsByType(type);
			coupResponse = new CouponUtilResponse("Success", coupList);
			System.out.println(coupList.toString());

		} catch (CouponSystemException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}

	@Path("/getCouponsByPrice")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse getCouponsByPrice(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("Price") int price) {
		ArrayList<Coupon> coupList = new ArrayList<>();
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		try {
			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			coupList = (ArrayList<Coupon>) cf.getAllCouponsByPrice(price);
			coupResponse = new CouponUtilResponse("Success", coupList);
			System.out.println(coupList.toString());

		} catch (CouponSystemException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}

	@Path("/getCouponsByDate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse getCouponsByDate(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("Date") String date) {
		ArrayList<Coupon> coupList = new ArrayList<>();
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date javaDate;
		try {
			
			javaDate = sf1.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
			
			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);

			coupList = (ArrayList<Coupon>) cf.getAllCouponsByDate(sqlDate);
			coupResponse = new CouponUtilResponse("Success", coupList);
			
			System.out.println(coupList.toString());

		} catch (CouponSystemException | ParseException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}

	@Path("/updateCoupon")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse updateCoupon(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("CouponNewEnd") String updateEndDate, @QueryParam("CouponNewPrice") int price,
			@QueryParam("CouponID") int coupID) {
		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		Coupon couponToUpdate;
		Date endDate;

		try {

			SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd");

			endDate = sf3.parse(updateEndDate);

			java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			couponToUpdate = cf.getCoupon(coupID);
			cf.updateCoupon(couponToUpdate, sqlEndDate, price);
			coupResponse = new CouponUtilResponse("Success");

		} catch (CouponSystemException | ParseException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;

	}

	@Path("/removeCoupon")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CouponUtilResponse removeCoupon(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("CouponID") int coupID) {

		CouponSystem cs = CouponSystem.getInstance();
		CompanyFacade cf;
		Coupon couponToRemove;
		try {

			cf = (CompanyFacade) cs.login(name, pass, ClientType.COMPANY);
			couponToRemove = cf.getCoupon(coupID);
			cf.removeCoupon(couponToRemove);
			coupResponse = new CouponUtilResponse("Success");

		} catch (CouponSystemException e) {
			coupResponse = new CouponUtilResponse("Failure " + e.getMessage());
			return coupResponse;
		}
		return coupResponse;
	}
}
