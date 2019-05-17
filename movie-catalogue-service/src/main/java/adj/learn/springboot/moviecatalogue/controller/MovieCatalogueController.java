package adj.learn.springboot.moviecatalogue.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import adj.learn.springboot.moviecatalogue.model.CatalogItem;
import adj.learn.springboot.moviecatalogue.model.Movie;
import adj.learn.springboot.moviecatalogue.model.Rating;
import adj.learn.springboot.moviecatalogue.model.UserRating;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogueController
{
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder builder;

	@GetMapping ("/{userId}")
	public List<CatalogItem> getCatalogs(@PathVariable ("userId") String userId)
	{
		UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId,
				UserRating.class);

		return userRating.getRatings().stream().map(rating ->
			{
				Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
						Movie.class);

				return new CatalogItem(movie.getName(), "Inspirational movie", rating.getRating());
			}).collect(Collectors.toList());
	}

	@GetMapping ("webclient/{userId}")
	public List<CatalogItem> getCatalogsWebClient(@PathVariable ("userId") String userId)
	{
		List<Rating> ratingList = Arrays.asList(new Rating("m1", 5), new Rating("m2", 4));

		return ratingList.stream().map(rating ->
			{
				// Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" +
				// rating.getMovieId(),Movie.class);

				Movie movie = builder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
						.retrieve().bodyToMono(Movie.class).block();

				return new CatalogItem(movie.getName(), "Inspirational movie", rating.getRating());
			}).collect(Collectors.toList());
	}
}