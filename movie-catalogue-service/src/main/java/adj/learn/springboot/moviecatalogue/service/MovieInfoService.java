package adj.learn.springboot.moviecatalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import adj.learn.springboot.moviecatalogue.model.CatalogItem;
import adj.learn.springboot.moviecatalogue.model.Movie;
import adj.learn.springboot.moviecatalogue.model.Rating;
import adj.learn.springboot.moviecatalogue.model.UserRating;

@Service
public class MovieInfoService
{
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackMovieInformation")
	public CatalogItem getMovieInformation(Rating rating)
	{
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItem getFallbackMovieInformation(Rating rating)
	{
		return new CatalogItem("Movie not found", "", rating.getRating());
	}
	
}