package com;

import model.Notice;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Notices")
public class NoticeService
{
		Notice noticeObj = new Notice();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readNotices()
		{
			 return noticeObj.readNotices();
		}

		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertNotice(@FormParam("noticeDate") String noticeDate,
							@FormParam("noticeTitle") String noticeTitle,
							@FormParam("noticeArea") String noticeArea,
							@FormParam("noticeDesc") String noticeDesc)
		{
			   String output = noticeObj.insertNotice(noticeDate, noticeTitle, noticeArea, noticeDesc);
			   return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateNotice(String noticeData)
		{
			   //Convert the input string to a JSON object
			   JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject();
		
			   //Read the values from the JSON object
			   String noticeID = noticeObject.get("noticeID").getAsString();
			   String noticeDate = noticeObject.get("noticeDate").getAsString();
			   String noticeTitle = noticeObject.get("noticeTitle").getAsString();
			   String noticeArea = noticeObject.get("noticeArea").getAsString();
			   String noticeDesc = noticeObject.get("noticeDesc").getAsString();
			   
			   String output = noticeObj.updateNotice(noticeID, noticeDate, noticeTitle, noticeArea, noticeDesc);
		
			   return output;
		}
	
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteNotice(String noticeData)
		{
			  //Convert the input string to an XML document
			  Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());
		
			  //Read the value from the element <noticeID>
			  String noticeID = doc.select("noticeID").text();
		
			  String output = noticeObj.deleteNotice(noticeID);
		
			  return output;
		}
		
}

