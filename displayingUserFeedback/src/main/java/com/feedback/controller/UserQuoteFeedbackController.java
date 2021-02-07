package com.feedback.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.feedback.repository.entity.QuoteEntity;
import com.feedback.repository.entity.QuoteFeedbackCrudRepository;

@RestController
@RequestMapping("feedback")
public class UserQuoteFeedbackController {
	Logger logger = LoggerFactory.getLogger(UserQuoteFeedbackController.class);

	@Autowired
	QuoteFeedbackCrudRepository repo;

	@PostMapping("/submit")
	public String save(String permalink, String quote, String id, String author) {
		logger.info("Saving data from the quotes link");
		initialFeedback();
		int i = 0;
		try {
			System.out.println(Integer.parseInt(id));
			i = Integer.parseInt(id);
		} catch (Exception e) {
			logger.info("Returning incorrect id entered in input");
			return "Incorrect id is given! Go back to previous page!";
		}
		QuoteEntity feedbackQuote = new QuoteEntity();
		logger.info("Finding data if exists");
		Optional<QuoteEntity> feedback = repo.findById(i);
		if (feedback.isPresent()) {
			feedbackQuote.setId(i);
			feedbackQuote.setAuthor(author);
			feedbackQuote.setPermalink(permalink);
			feedbackQuote.setQuote(quote);
			logger.info("Returning data is saved");
			return "Feedback Saved to db! : " + repo.save(feedbackQuote);
		} else {
			feedbackQuote.setId(i);
			feedbackQuote.setAuthor(author);
			feedbackQuote.setPermalink(permalink);
			feedbackQuote.setQuote(quote);
			logger.info("Returning incorrect id entered in input but data is saved");
			return "Quote Feedback Id is not found but new feedback is saved! : " + repo.save(feedbackQuote);
		}
	}

	public void initialFeedback() {
		logger.info("Using quote link as input");
		RestTemplate restTemplate = new RestTemplate();
		QuoteEntity feedback = restTemplate.getForObject("http://quotes.stormconsultancy.co.uk/random.json",
				QuoteEntity.class);
		repo.save(feedback);
		logger.info("Data from the quotes link is saved");
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable String id) {
		int j = 0;
		try {
			logger.info("Converting id string to integer");
			j = Integer.parseInt(id);
			logger.info("Finding if data exists");
			Optional<QuoteEntity> feedbackQuote = repo.findById(j);
			if (feedbackQuote.isPresent()) {
				logger.info("Saving to db if present");
				return "Feedback Quote is : " + feedbackQuote;
			} else {
				logger.info("Returning incorrect id entered in link");
				return "ID not found";
			}
		} catch (Exception e) {
			logger.info("Returnng not found if data not present to show");
			return "Incorrect id is given! Please enter correct id in link!";
		}
	}
}
