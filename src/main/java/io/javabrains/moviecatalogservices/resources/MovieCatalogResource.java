package io.javabrains.moviecatalogservices.resources;

import io.javabrains.moviecatalogservices.models.CatalogItem;
import io.javabrains.moviecatalogservices.models.Movie;
import io.javabrains.moviecatalogservices.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog (@PathVariable("userId") String userId){


        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating("5667", 5)
        );

        return ratings.stream().map(rating -> {
           Movie movie = restTemplate.getForObject("http://localhost:9090/movies/foo", Movie.class);
            return  new CatalogItem(movie.getName(),"Test", rating.getRating());

        }).collect(Collectors.toList());


        //get all rated movie IDs

        //for each movie ID, call movie info service and get details

        //put them all together


    }
}
