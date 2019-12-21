package adj.learn.springboot.movieinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import adj.learn.springboot.movieinfo.model.Movie;
import adj.learn.springboot.movieinfo.model.MovieSummary;

@RestController
@RequestMapping ("/movies")
public class MovieInfoController
{
	@Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;
    
	@GetMapping ("/{movieId}")
	public Movie getMovieInfo(@PathVariable ("movieId") String movieId)
	{
		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}
}