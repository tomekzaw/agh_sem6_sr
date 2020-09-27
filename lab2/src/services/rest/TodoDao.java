package services.rest;

import java.util.HashMap;
import java.util.Map;

import services.rest.model.Todo;

public class TodoDao {
	private static Map<String, Todo> contentProvider = new HashMap<String, Todo>();
	
	static {
		Todo todo = new Todo("1", "Learn REST theory");
		todo.setDescription("Attend the REST lecture on the TAI course");
		contentProvider.put("1", todo);
		todo = new Todo("2", "Learn REST practice");
		todo.setDescription("Attend the REST laboratory on the TAI course");
		contentProvider.put("2", todo);
	}
	
	public static Map<String, Todo> getModel() {
		return contentProvider;
	}
}