package com.Undis.Madeline.SzotoSzoves_CaseStudy;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.exceptions.NoRootsFoundException;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.RootC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.User;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.UserWord;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.WordC;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.RootCService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.UserWordService;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.service.WordCService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"chatgpt.api.key=${OPENAI_API_KEY}"})
class SzotoSzovesCaseStudyApplicationTests {
	@Autowired
	private WordCService wordService;
	@Autowired
	private RootCService rootService;
	@Autowired
	private UserService userService;
	@Autowired
	UserWordService userWordService;

	@Test
	 void wordNotNull() {
		assertNotNull(wordService.getWord("Turkish"), "word should not be null");
	}

	@ParameterizedTest
	@CsvSource({
			"madeline@madeline.com, 1"
	})
	void userIsFound (ArgumentsAccessor arguments) {
		User user = userService.findUserByEmail(arguments.getString(0));
		int csvID = Integer.parseInt(arguments.getString(1));
		assertEquals(user.getId(), csvID);
	}

	@Test
	void wordsAreFlagged() {
		List<UserWord> words = userWordService.getFlaggedWords(1);
		words.forEach(word -> assertTrue(word.isFlagged(), "word is flagged"));
	}
//	test driven development
	@Test
	void rootsAreRenderedInOrder() {
		List<String> sequence = new ArrayList<>(Arrays.asList("szó", "kapcsol", "a", "t"));
		Optional<WordC> optionalWord = wordService.getWordById(7);
		optionalWord.ifPresent(word -> {
			List<RootC> roots = null;
			try {
				roots = rootService.getRootsInOrder(word.getId());
			} catch (NoRootsFoundException e) {
				throw new RuntimeException(e);
			}
			System.out.println("roots");
			assertEquals(sequence.size(), roots.size());
			for (int i=0; i<roots.size(); i++) {
				assertTrue(roots.get(i).getName().equals(sequence.get(i)));
			}
		});
	}
}
