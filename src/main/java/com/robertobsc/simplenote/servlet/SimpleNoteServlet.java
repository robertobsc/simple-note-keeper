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
import com.robertobsc.simplenote.domain.Entity;
import com.robertobsc.simplenote.domain.Note;

@WebServlet("/notes")
public class SimpleNoteServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String JSON_PROTOCOL = "application/json";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug(">> /notes/get");
		resp.setContentType(JSON_PROTOCOL);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerFor(new TypeReference<List<Note>>(){}).writeValue(resp.getWriter(), Note.findAll());
		logger.debug("<< /notes/get");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug(">> /notes/post");
		if (!JSON_PROTOCOL.equalsIgnoreCase(req.getContentType())) {
			resp.getWriter().println("Protocol Error");
			return;
		}
		
		String json = req.getReader().lines().collect(Collectors.joining(" "));
		
		Stream.of(json)
			  .map(Note::fromJson)
			  .forEach(Note::save);
		
		resp.getWriter().println("Item saved");
		logger.debug("<< /notes/post");
	}
}
