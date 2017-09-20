package data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;

import db.entity.Contact;

/**
 * Examples:</br>
 * <ul>
 * <li>Serialize/Deserealize a object</li>
 * <li>Serialize/Deserealize a list of objects</li>
 * <li>JSON XPath</li>
 * </ul>
 * 
 * @author henbreda
 * @since 2019-09-14
 * @version 1.00
 *
 */
public class JsonTest
{
	
	private List<Contact> contactList;
	private Contact contactObject;
	
	private Gson gson;
	
	@Before
	public void setUp() {
		contactList = new ArrayList<Contact>();
		contactList.add(new Contact(1, "Contacto 001", "contacto.001@email.com", "221234001"));
		contactList.add(new Contact(2, "Contacto 002", "contacto.002@email.com", "221234002"));
		contactList.add(new Contact(3, "Contacto 003", "contacto.003@email.com", "221234003"));
		contactList.add(new Contact(4, "Contacto 004", "contacto.004@email.com", "221234004"));
		contactList.add(new Contact(5, "Contacto 005", "contacto.005@email.com", "221234005"));
		
		contactObject = new Contact(1, "Contacto 001", "contacto.001@email.com", "221234001");
		
		gson = new Gson();
	}
	
	/**
	 * Serialize the Object
	 */
	@Test
	public void serealizeObject() {
		// Convert to JSON
		String objectToJson = gson.toJson(contactObject);
		// Convert to OBJECT
		assertSame(contactObject.getId(), JsonPath.read(objectToJson, "$.id"));
		assertEquals(contactObject.getName(), JsonPath.read(objectToJson, "$.name"));
		assertEquals(contactObject.getEmail(), JsonPath.read(objectToJson, "$.email"));
		assertEquals(contactObject.getCellphone(), JsonPath.read(objectToJson, "$.cellphone"));
	}
	
	/**
	 * Example: Serialize List of the Objects
	 */
	@Test
	public void serealizeList() {
		List<String> jsonList;
		// Convert to JSON
		String objectToJson = gson.toJson(contactList);
		// Convert to OBJECT
		jsonList = JsonPath.read(objectToJson, "$..id");
		assertEquals(contactList.size(), jsonList.size());
		assertEquals(contactList.get(0).getId(), jsonList.get(0));
		
		jsonList = JsonPath.read(objectToJson, "$..name");
		assertEquals(contactList.size(), jsonList.size());
		assertEquals(contactList.get(0).getName(), jsonList.get(0));
		
		jsonList = JsonPath.read(objectToJson, "$..email");
		assertEquals(contactList.size(), jsonList.size());
		assertEquals(contactList.get(0).getEmail(), jsonList.get(0));
		
		jsonList = JsonPath.read(objectToJson, "$..cellphone");
		assertEquals(contactList.size(), jsonList.size());
		assertEquals(contactList.get(0).getCellphone(), jsonList.get(0));
	}
	
	/**
	 * Deserialize the Object
	 */
	@Test
	public void deserealizeObject() {
		// Convert to JSON
		String objectToJson = gson.toJson(contactObject);
		// Convert to OBJECT
		Contact jsonToObject = gson.fromJson(objectToJson, Contact.class);
		// Validation
		assertEquals(contactObject, jsonToObject);
	}
	
	/**
	 * Example: Deserialize List of the Objects
	 */
	@Test
	public void deserealizeList() {
		// Convert to JSON
		String json = gson.toJson(contactList);
		// Convert to LIST
		Type collection = new TypeToken<Collection<Contact>>() {
		}.getType();
		Collection<Contact> list = gson.fromJson(json, collection);
		// Validation
		assertEquals(contactList, list);
	}
}
