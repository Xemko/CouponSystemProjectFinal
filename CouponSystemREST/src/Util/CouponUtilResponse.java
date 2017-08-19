package Util;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import c.Beans.Coupon;
@XmlRootElement

public class CouponUtilResponse {

	private String message;
	private ArrayList<Coupon> couponList;

	public CouponUtilResponse() {
		super();
		
	}

	public CouponUtilResponse(String message) {
		super();
		this.message = message;
	}

	public CouponUtilResponse(String message, ArrayList<Coupon> couponList) {
		super();
		this.message = message;
		this.couponList = couponList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<Coupon> couponList) {
		this.couponList = couponList;
	}

	@Override
	public String toString() {
		return "CouponUtilResponse [message=" + message + ", couponList=" + couponList + "]";
	}

}
