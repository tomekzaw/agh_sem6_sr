package services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import services.rest.model.Todo;

public class TodoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public TodoResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Todo getTodo() {
		Todo todo = TodoDao.getModel().get(id);
		return todo;
	}
	
	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Todo getTodoHTML() {
		Todo todo = TodoDao.getModel().get(id);
		return todo;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML) 
	public Response putTodo(JAXBElement<Todo> todo) {
		Todo c = todo.getValue();
		return putAndGetResponse(c);
	}
	
	@DELETE
	public void deleteTodo() {
		Todo c = TodoDao.getModel().remove(id);
	}
	
	private Response putAndGetResponse(Todo todo) {
		Response res;
		if (TodoDao.getModel().containsKey(todo.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();			
		}
		TodoDao.getModel().put(todo.getId(), todo);
		return res;
	}
}
