package adj.learn.springboot.moviecatalogue.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adj.learn.springboot.moviecatalogue.model.CatalogItem;

@RestController
@RequestMapping ("/catalog")
public class MovieCatalogueController
{

	@GetMapping ("/{userId}")
	public List<CatalogItem> getCatalogs(@PathVariable ("userId") String userId)
	{
		return Collections.singletonList(new CatalogItem("Chal man jitva jaiye", "Inspirational movie", 5));
	}

}