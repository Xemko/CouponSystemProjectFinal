package Util;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import c.Beans.Coupon;
import c.Beans.Customer;

@XmlRootElement

public class CustomerUtilResponse {

	private String message;
	private ArrayList<Coupon> custCouponList;
	private ArrayList<Customer> custList;

	public CustomerUtilResponse() {
		super();
	}

	public CustomerUtilResponse(String message) {
		super();
		this.message = message;
	}

	public CustomerUtilResponse(ArrayList<Coupon> custCouponList, String message) {
		this.message = message;
		this.custCouponList = custCouponList;
	}

	

	public CustomerUtilResponse(String message, ArrayList<Customer> custList) {
		super();
		this.message = message;
		this.custList = custList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Coupon> getCoupons() {
		return custCouponList;
	}

	public void setCoupons(ArrayList<Coupon> custCouponList) {
		this.custCouponList = custCouponList;
	}
	

	public ArrayList<Customer> getCustList() {
		return custList;
	}

	public void setCustList(ArrayList<Customer> custList) {
		this.custList = custList;
	}



}
