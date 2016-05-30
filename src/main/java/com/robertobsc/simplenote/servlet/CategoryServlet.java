package com.robertobsc.simplenote.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertobsc.simplenote.domain.Category;
import com.robertobsc.simplenote.domain.Entity;

@SuppressWarnings("serial")
@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final String JSON_PROTOCOL = "application/json";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug(">> /categories/get");
		ObjectMapper mapper = new ObjectMapper();		
		mapper.writerFor(new TypeReference<List<Category>>(){}).writeValue(resp.getWriter(), Category.findAll());
		logger.debug("<< /categories/get");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug(">> /categories/post");
		if (!JSON_PROTOCOL.equalsIgnoreCase(req.getContentType())) {
			resp.getWriter().println("Protocol Error");
			return;
		}
		
		String json = req.getReader().lines().collect(Collectors.joining(" "));
		
		Stream.of(json)
			  .map(j -> Entity.fromJson(Category.class, j))
			  .forEach(Entity::save);
		
		resp.getWriter().println("Category saved");
		
		logger.debug("<< /categories/post");
	}
}
