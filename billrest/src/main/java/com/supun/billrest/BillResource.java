/*
 * Author : S.M.Suriyaarachchi
 * Student ID: IT20187514
 * 
 * Usage : ImplimentnIg the HTML end-points for the CRUD operations
 * 			using HTTP methods(GET, POST, PUT, DELETE).
 * */

package com.supun.billrest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bills")
public class BillResource {
	
	BillRepository repo = new BillRepository();
	
	//This method is for getting a build resource
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getBills(){
		System.out.println("getBills called!!");
		return repo.getBills();
	}
	
	
	@GET
	@Path("bill/{billId}")
	@Produces(MediaType.APPLICATION_JSON)
		
	//method to get a single data set
	public Bill getBill(@PathParam("billId") int id) {
		return repo.getBill(id);
	}
	
	//@POST is for creating a resource
	@POST
	@Path("bill")
	@Produces(MediaType.APPLICATION_JSON)
	public Bill createBill(Bill billObj) {
		System.out.println(billObj);
		repo.create(billObj);
		
		return billObj;
	}
	
	
	//@PUT is for updating a resource
	@PUT
	@Path("bill")
	@Produces(MediaType.APPLICATION_JSON)
	public Bill updateBill(Bill billObj) {
		System.out.println(billObj);
		repo.update(billObj);
		
		return billObj;
	}
	
	
	//@DELETE is for deleting a resource
	@DELETE
	@Path("bill/{billId}")
	public Bill deleteBill(@PathParam("billId") int billId) {
		Bill billObj = repo.getBill(billId);
		System.out.println(billObj);
		
		if(billObj.getBillId() != 0) {
			repo.delete(billId);
		}
		
		return billObj;
	}
	
	
}
