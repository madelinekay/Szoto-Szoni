package com.Undis.Madeline.SzotoSzoves_CaseStudy;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.PythonAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SzotoSzovesCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzotoSzovesCaseStudyApplication.class, args);
		PythonAPIClient.getWord();
	}

}
