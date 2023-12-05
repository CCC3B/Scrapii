package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrapiiApplication {
		public void m()
		{}
	public static void main(String[] args) {
		SpringApplication.run(ScrapiiApplication.class, args);
		Scraper scrape = new Scraper("Mozilla", "LongHallWay", "SomeoneSaidStop", "V0.0.1","https://www.reddit.com/r/PublicFreakout/");
		scrape.Ouath2("https://oauth.reddit.com/r/PuplicFreakout/hot", null);
		System.exit(-3);
	}

}
