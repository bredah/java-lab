package rest.spark.java8;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.SparkBase.port;

import com.google.gson.Gson;

import db.entity.Contact;

/**
 * Example: Spark REST</br>
 * http://sparkjava.com/documentation#getting-started
 * 
 * @version 1.00
 * @author henbreda
 * @sinve 2017-09-15
 */
public class Server
{
	
	private Gson gson;
	
	public void establishRoutes() {
		// Be declare before ROUTES and FILTERS
		port(8888);
		
		// Example - AFTER REQUESTs: Add header(s)
		after((request, response) -> response.header("foo", "xpto"));
		
		// Example - GET: Add headers
		get("/api", (request, response) ->
		{
			response.header("header01", "001");
			response.header("header02", "002");
			
			Contact contact = new Contact(1, "XPTO", "xpto@email.com", "1234567890");
			gson = new Gson();
			
			return gson.toJson(contact);
		});
		
		// Example - GET: Using parameter(s)
		get("/get/:name", (request, response) -> "Hello " + request.params(":name"));
		
		// Example - POST
		post("/api", (request, response) -> "POST");
		
		// Example - POST: With body
		post("/post", (request, response) ->
		{
			gson = new Gson();
			Contact contact = gson.fromJson(request.body(), Contact.class);
			return contact.toString();
		});
		
		// Example - PUT
		put("/api", (request, response) -> gson.toJson(new RequestResponse(true)));
		
		// Example - DELETE
		delete("/api", (request, response) -> gson.toJson(new RequestResponse("failed")));
	}
	
	public class RequestResponse
	{
		private Object response;
		
		public RequestResponse(Object response)
		{
			this.response = response;
		}
		
		public Object getResponse() {
			return response;
		}
		
		public void setResponse(Object response) {
			this.response = response;
		}
	}
}
