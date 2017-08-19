package Util;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import c.Beans.Company;
@XmlRootElement

public class CompanyUtilResponse {

	private String message;
	private ArrayList<Company> companyList;

	public CompanyUtilResponse() {
		super();
	
	}

	public CompanyUtilResponse(String message) {
		super();
		this.message = message;
	}

	public CompanyUtilResponse(String message, ArrayList<Company> companyList) {
		super();
		this.message = message;
		this.companyList = companyList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(ArrayList<Company> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString() {
		return "CompanyUtilResponse [message=" + message + ", companyList=" + companyList + "]";
	}

}
