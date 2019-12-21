package adj.learn.springboot.ratingdata.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adj.learn.springboot.ratingdata.model.Rating;
import adj.learn.springboot.ratingdata.model.UserRating;

@RestController
@RequestMapping ("/ratingsdata")
public class RatingDataController
{
	@GetMapping ("/{movieId}")
	public Rating getMovieInfo(@PathVariable ("movieId") String movieId)
	{
		return new Rating(movieId, 5);
	}

	@GetMapping ("/users/{userId}")
	public UserRating getUserRating(@PathVariable ("userId") String movieId)
	{
		UserRating userRating = new UserRating();
		userRating.setRatings(Arrays.asList(new Rating("m1", 100), new Rating("m2", 200)));
		userRating.setUserName("Test User");

		return userRating;
	}
}