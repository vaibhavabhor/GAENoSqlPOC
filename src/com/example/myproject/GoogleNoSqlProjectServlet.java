package com.example.myproject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;


@SuppressWarnings("serial")
public class GoogleNoSqlProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		// Creating single entity in Google data store.		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity e = new Entity("Person");
		e.setProperty("FirstName", "Vaibhav");
		e.setProperty("LastName", "Bhor");		// Update operation
		ds.put(e);				// Write Operation
		
		Entity user = new Entity("User");
		user.setProperty("UserId", "1001");
		user.setProperty("UserName", "VaibhavB");
		ds.put(user);
		
		//ds.delete(e);			// Delete operation
		// Create batch of entities 
		Entity e1 = new Entity("Student");
		Entity e2 = new Entity("Teacher");
		Entity e3 = new Entity("Parents");
		List<Entity> listEntity = Arrays.asList(e1,e2,e3);
		ds.put(listEntity);
		
		// Creating a key
		com.google.appengine.api.datastore.Key key = KeyFactory.createKey("User", 415);
		System.out.println("Key is"+ key);
		
		// Querying : Simple as well as with operators
		//Query q = new Query("User");
		Query q = new Query("User").addFilter("UserId", FilterOperator.GREATER_THAN, 1000);
		PreparedQuery pq = ds.prepare(q);
		for(Entity entity: pq.asIterable())
		{
			String userId = entity.getProperty("UserId").toString();
			String userName = entity.getProperty("UserName").toString();
			
			System.out.println("UserId is"+ userId + " and userName is" + userName);
		}
		
		//Transaction: Set of operations on an entity. 
		//				Best for synchronization, partial transactions dose not happen		
		com.google.appengine.api.datastore.Transaction tx = ds.beginTransaction();
		Entity user1 = new Entity("User");
		user.setProperty("UserId", "1002");
		user.setProperty("UserName", "SumantB");
		ds.put(user1);
		tx.commit();
		
		// Update the application name from https://appengine.google.com/ to appengine-web.xml
		// Right click on project -> Deploy!
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
	}
}
