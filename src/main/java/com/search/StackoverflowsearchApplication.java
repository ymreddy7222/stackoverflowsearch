package com.search;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.search.bean.SearchResponse;
import com.search.bean.SearchResult;
import com.search.service.SearchService;
import com.search.service.impl.SearchServiceImpl;

@SpringBootApplication
public class StackoverflowsearchApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(StackoverflowsearchApplication.class, args);
		
		Logger logger = LoggerFactory.getLogger(StackoverflowsearchApplication.class);
		
		SearchService searchService = new SearchServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			
			System.out.println("Please Enter Your Search Query : ");
			String query = scanner.nextLine();
			SearchResponse search = searchService.search(query);
			
			int count = 0;
			logger.info("Here are the top-5 results for your search " + query);
			if (search.getStatus() == 200) {

				for (SearchResult result : search.getItems()) {

					if (count < 5) {

						logger.info("title : " + result.getTitle());
						logger.info("\033[ URL : " + result.getLink() + "\033[0m");
						logger.info("Author Display Name : " + result.getOwner().getDisplay_name());
						count++;
					}
				}

			}
			else {
				
				logger.info("No results found for your search " + query);
			}
		}
		
		scanner.close();
	}

}
