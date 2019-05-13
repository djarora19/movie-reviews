package adj.learn.springboot.movieinfo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adj.learn.springboot.movieinfo.model.Movie;

@RestController
@RequestMapping ("/movies")
public class MovieInfoController
{
	@GetMapping ("/{movieId}")
	public List<Movie> getMovieInfo(@PathVariable ("movieId") String movieId)
	{
		return Collections.singletonList(new Movie(movieId, "Chal man jitva jaiye"));
	}
}