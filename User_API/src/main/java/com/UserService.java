package com;
import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Users")
public class UserService
{
 User userObj = new User();
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readUsers() 
  { 
  return userObj.readUsers(); 
 }
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertUsers(@FormParam("UseraccountNo") String UseraccountNo, 
  @FormParam("Username ") String Username , 
  @FormParam("Usernic") String Usernic, 
  @FormParam("Useraddress") String Useraddress) 
 { 
  String output = userObj.insertUsers(UseraccountNo,Username , Usernic, Useraddress); 
 return output; 
 }
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateUsers(String userData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
 //Read the values from the JSON object
  String UseraccountNo = userObject.get("UseraccountNo").getAsString(); 
  String Username = userObject.get("Username").getAsString(); 

  String Usernic = userObject.get("Usernic").getAsString(); 
  String Useraddress = userObject.get("Useraddress").getAsString(); 
  String Userstatus = userObject.get("Userstatus").getAsString(); 
  String output = userObj.updateUsers(UseraccountNo, Username, Usernic, Useraddress, Userstatus); 
 return output; 
 }
 @DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteUsers(String userData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(userData, "", Parser.xmlParser()); 
 
  
 //Read the value from the element <itemID>
  String UseraccountNo = doc.select("UseraccountNo").text(); 
  String output = userObj.deleteUsers(UseraccountNo); 
 return output; 
 }

}
