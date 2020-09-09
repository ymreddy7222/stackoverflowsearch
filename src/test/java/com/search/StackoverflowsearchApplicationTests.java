package com.search;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.search.bean.SearchResponse;
import com.search.service.SearchService;
import com.search.service.impl.SearchServiceImpl;

@SpringBootTest
public class StackoverflowsearchApplicationTests {

	@Test
	public void testStackoverFlowSearch() {
		
		Logger logger = LoggerFactory.getLogger(StackoverflowsearchApplicationTests.class);
		String query = "add element to list";
		SearchService searchService = new SearchServiceImpl();
		SearchResponse search = searchService.search(query);
		logger.info("Status : " + search.getStatus());
	}

}
