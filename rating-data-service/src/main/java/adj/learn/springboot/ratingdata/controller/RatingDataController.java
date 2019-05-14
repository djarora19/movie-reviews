package adj.learn.springboot.ratingdata.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adj.learn.springboot.ratingdata.model.Rating;

@RestController
@RequestMapping ("/ratingsdata")
public class RatingDataController
{
	@GetMapping ("/{movieId}")
	public Rating getMovieInfo(@PathVariable ("movieId") String movieId)
	{
		return new Rating(movieId, 5);
	}
}