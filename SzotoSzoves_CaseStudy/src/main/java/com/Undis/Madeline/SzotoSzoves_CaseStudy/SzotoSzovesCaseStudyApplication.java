package com.Undis.Madeline.SzotoSzoves_CaseStudy;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.data.RootWordId;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.PythonAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SzotoSzovesCaseStudyApplication {

	public static void main(String[] args) {

		SpringApplication.run(SzotoSzovesCaseStudyApplication.class, args);

	}
}
