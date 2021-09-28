package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {


  private final MovieRepository MOVIE_REPOSITORY;

  @Autowired
  public MovieService(MovieRepository movie_repository) {
    MOVIE_REPOSITORY = movie_repository;
  }

  public String getFirstMovie() {
    return MOVIE_REPOSITORY.getMovies().get(0).toString();
  }

  public String getRandomMovie() {
    return MOVIE_REPOSITORY.getRandomMovie().toString();
  }

  public String getTopTen() {
    String result = "";
    List<Movie> movies = MOVIE_REPOSITORY.topTenMovies();

    for (int i = 0; i < movies.size(); i++) {
      result += (i + 1) + ". " +
          movies.get(i).toString() + "<br>";
    }
    return result;
  }

  public String getAwardsCount() {
    return "This many Movies has won an award: " + MOVIE_REPOSITORY.getWonAward();
  }

  public String filteredList(String c, String n) {
    char y = c.charAt(0);
    int x = Integer.parseInt(n);
    String result = "";
    List<Movie> movies = MOVIE_REPOSITORY.filterOnCharNTimes(y, x);
    for (int i = 0; i < movies.size(); i++) {
      result += (i + 1) + ". " +
          movies.get(i).getTitle() + "<br>";
    }
    return result;
  }

  public String getListComedy() {
    String result = "";
    List<Movie> movies = MOVIE_REPOSITORY.getListComedy();

    for (int i = 0; i < movies.size(); i++) {
      result += (i + 1) + ". " +
          movies.get(i).toString() + "<br>";
    }
    return result;
  }

  public String getLongestAvgByInput(String g1, String g2) {
    return MOVIE_REPOSITORY.getLongestAvgByGenre(g1, g2);
  }
}
