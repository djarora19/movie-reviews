package adj.learn.springboot.moviecatalogue.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import adj.learn.springboot.moviecatalogue.model.Rating;
import adj.learn.springboot.moviecatalogue.model.UserRating;

@Service
public class UserRatingService
{
	@Autowired
	private RestTemplate restTemplate;
	

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId)
	{
		return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(String userId)
	{
		UserRating userRating = new UserRating();
		userRating.setUserName(userId);
		userRating.setRatings(Arrays.asList(
				new Rating("0", 0)));
		return userRating;
	}
}
