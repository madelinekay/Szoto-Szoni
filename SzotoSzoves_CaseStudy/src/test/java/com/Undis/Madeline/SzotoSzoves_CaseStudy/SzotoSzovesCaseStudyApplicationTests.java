package com.Undis.Madeline.SzotoSzoves_CaseStudy;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Word;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.RootService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserWordService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SzotoSzovesCaseStudyApplicationTests {
	@Autowired
	private WordService wordService;
	@Autowired
	private RootService rootService;
	@Autowired
	private UserService userService;
	@Autowired
	UserWordService userWordService;

	@Test
	 void wordNotNull() {
		assertNotNull(wordService.getWord(), "word should not be null");
	}

	@ParameterizedTest
	@CsvSource({
			"madeline@madeline.com, 2"
	})
	@Test
	void userIsFound (ArgumentsAccessor arguments) {
		User user = userService.findUserByEmail(arguments.getString(0));
		assertEquals(user.getId(), Integer.parseInt(arguments.getString(1)));
	}
//	test driven development
	@Test
	void rootsAreRenderedInOrder() {
		List<String> sequence = new ArrayList<>(Arrays.asList("szó", "kapcsol", "a", "t"));
		Word word = wordService.getWord();
//		call root service getting roots and sorting in order
		List<Root> roots = rootService.getRootsInOrder(word.getId());
		assertEquals(sequence.size(), roots.size());
		for (int i=0; i<roots.size(); i++) {
			assertTrue(roots.get(i).equals(sequence.get(i)));
		}
	}
}
