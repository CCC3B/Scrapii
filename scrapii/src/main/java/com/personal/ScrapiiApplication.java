package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrapiiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ScrapiiApplication.class, args);
		Scraper scrape = new Scraper("Mozilla", "LongHallWay", "SomeoneSaidStop", "V0.0.1");
		scrape.Ouath2();
		scrape.Home("https://oauth.reddit.com/r/PublicFreakout/hot", 3, "car");
		
	}

}
