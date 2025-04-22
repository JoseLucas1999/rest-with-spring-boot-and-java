package br.com.lucas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/v1")
public class TestLogController {
	
	private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());
	
	@GetMapping
	public String testLog() {
		logger.debug("this is an DEBUG log");
		logger.info("this is an INFO log");
		logger.warn("this is an WARN log");
		logger.error("this is an ERROR log");
		
		return "Logs generated successfully";
	}
}
