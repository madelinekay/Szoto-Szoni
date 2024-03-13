package com.Undis.Madeline.SzotoSzoves_CaseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SzotoSzovesCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzotoSzovesCaseStudyApplication.class, args);
		org.madelineundis.service.PythonAPIClient.getWord();
	}

}
