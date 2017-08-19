package rest;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Util.CompanyUtilResponse;
import Util.CustomerUtilResponse;
import c.Beans.Company;
import c.Beans.Customer;
import e.Exceptions.CouponSystemException;
import f.Facades.AdminFacade;
import f.Facades.ClientType;
import g.Main.CouponSystem;

@Path("/admin")
public class AdminService {

	private CompanyUtilResponse compResponse;
	private CustomerUtilResponse custResponse;


	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Path("/getAllCompanies")
	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public CompanyUtilResponse getAllCompanies(@QueryParam("user") String name, @QueryParam("pass") String pass) {

		ArrayList<Company> compList = new ArrayList<>();
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		try {
			
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			compList = (ArrayList<Company>) af.getAllCompanies();
			compResponse = new CompanyUtilResponse("Success", compList);
			System.out.println(compList.toString());
		} catch (CouponSystemException e) {
			compResponse = new CompanyUtilResponse("Failure " + e.getMessage());

			return compResponse;
		}
		return compResponse;
	}

	@Path("/createCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyUtilResponse createCompany(@QueryParam("CompanyName") String companyName,
			@QueryParam("CompanyPass") String companyPassword, @QueryParam("CompanyEmail") String companyEmail,
			@QueryParam("user") String name, @QueryParam("pass") String pass) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		int compID;
		Company companyToCreate = new Company();
		ArrayList<Company> utilList = new ArrayList<>();
		companyToCreate.setName(companyName);
		companyToCreate.setPassword(companyPassword);
		companyToCreate.setEmail(companyEmail);

		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			compID = af.createCompany(companyToCreate);
			companyToCreate.setId(compID);
			utilList.add(companyToCreate);

			switch (compID) {
	
			case -2:
				compResponse = new CompanyUtilResponse("Failure - name already exists");
				break;

			default:
				compResponse = new CompanyUtilResponse("Success", utilList);
				break;
			}

		} catch (CouponSystemException e) {
			compResponse = new CompanyUtilResponse("Failure - SQL error " + e.getMessage());
			return compResponse;
		}

		return compResponse;
	}

	@Path("/removeCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyUtilResponse removeCompany(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("compID") int id) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		Company companyToRemove;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			companyToRemove = af.getCompany(id);
			af.removeCompany(companyToRemove);
			compResponse = new CompanyUtilResponse("Success");
		} catch (CouponSystemException e) {
			compResponse = new CompanyUtilResponse("Failure " + e.getMessage());
			return compResponse;
		}
		return compResponse;

	}

	@Path("/updateCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyUtilResponse updateCompany(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("compID") int id, @QueryParam("CompanyPass") String companyPassword,
			@QueryParam("CompanyEmail") String companyEmail) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		Company companyToUpdate;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			companyToUpdate = af.getCompany(id);
			companyToUpdate.setPassword(companyPassword);
			companyToUpdate.setEmail(companyEmail);
			af.updateCompany(companyToUpdate);
			compResponse = new CompanyUtilResponse("Success");
		} catch (CouponSystemException e) {
			compResponse = new CompanyUtilResponse("Failure " + e.getMessage());
			return compResponse;
		}
		return compResponse;
	}

	@Path("/getCompany")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CompanyUtilResponse getCompanyByID(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("compID") int id) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		ArrayList<Company> compList = new ArrayList<>();
		Company companyToGet;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			companyToGet = af.getCompany(id);
			compList.add(companyToGet);
			compResponse = new CompanyUtilResponse("Success", compList);
		} catch (CouponSystemException e) {
			compResponse = new CompanyUtilResponse("Failure " + e.getMessage());
			return compResponse;
		}
		return compResponse;
	}

	@Path("/getAllCustomers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getAllCustomers(@QueryParam("user") String name, @QueryParam("pass") String pass) {
		ArrayList<Customer> custList = new ArrayList<>();
		try {
			CouponSystem cs = CouponSystem.getInstance();
			AdminFacade af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			custList = (ArrayList<Customer>) af.getAllCustomers();
			custResponse = new CustomerUtilResponse("Success", custList);
			System.out.println(custList.toString());
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/createCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse createCustomer(@QueryParam("CustomerName") String custName,
			@QueryParam("CustomerPass") String custPassword, @QueryParam("user") String name,
			@QueryParam("pass") String pass) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		int custID;
		Customer customerToCreate = new Customer();
		ArrayList<Customer> utilList = new ArrayList<>();
		customerToCreate.setName(custName);
		customerToCreate.setPassword(custPassword);

		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			custID = af.createCustomer(customerToCreate);
			customerToCreate.setId(custID);
			utilList.add(customerToCreate);

			switch (custID) {
			// // Probably useless as -1 code means exception on DAOdb level
			// which will be caught by catch in line #217
			// case -1:
			// custResponse = new CustomerUtilResponse("Failure - customer
			// creation failed");
			// break;
			case -2:
				custResponse = new CustomerUtilResponse("Failure - name already exists");
				break;
			default:
				custResponse = new CustomerUtilResponse("Success", utilList);
				break;
			}

		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure - SQL error" + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/getCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse getCustomerByID(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("custID") int custID) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		ArrayList<Customer> custList = new ArrayList<>();
		Customer customerToGet;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			customerToGet = af.getCustomer(custID);
			custList.add(customerToGet);
			custResponse = new CustomerUtilResponse("Success", custList);
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

	@Path("/updateCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse updateCustomer(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("newPass") String custPass, @QueryParam("custID") int custID) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		Customer customerToUpdate;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			customerToUpdate = af.getCustomer(custID);
			customerToUpdate.setPassword(custPass);
			af.updateCustomer(customerToUpdate);
			custResponse = new CustomerUtilResponse("Success");

		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}

		return custResponse;
	}

	@Path("/removeCustomer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public CustomerUtilResponse removeCustomer(@QueryParam("user") String name, @QueryParam("pass") String pass,
			@QueryParam("custID") int custID) {
		CouponSystem cs = CouponSystem.getInstance();
		AdminFacade af;
		Customer customerToRemove;
		try {
			af = (AdminFacade) cs.login(name, pass, ClientType.ADMIN);
			customerToRemove = af.getCustomer(custID);
			af.removeCustomer(customerToRemove);

			custResponse = new CustomerUtilResponse("Success");
		
		} catch (CouponSystemException e) {
			custResponse = new CustomerUtilResponse("Failure " + e.getMessage());
			return custResponse;
		}
		return custResponse;
	}

}
