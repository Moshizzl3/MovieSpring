package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MovieController {


  private final MovieService MOVIE_SERVICE;

  @Autowired
  public MovieController(MovieService movie_service) {
    MOVIE_SERVICE = movie_service;
  }

  @GetMapping("/")
  @ResponseBody
  public String checkConnection() {
    return "<h1> Welcome, this application knows stuff about movies!🔥🔥🔥🔥" +
        " <br> " +
        " <br> " +
        "Enjoy :)) </h1>";
  }


  @GetMapping("/getFirst")
  @ResponseBody
  public String getFirstMovie() {
    return "First movie on the list is: " + MOVIE_SERVICE.getFirstMovie();
  }

  @GetMapping("/getRandom")
  @ResponseBody
  public String getRandom() {
    return "Random movie as requested: " + MOVIE_SERVICE.getRandomMovie();
  }

  @GetMapping("/getTenSortByPopularity")
  @ResponseBody
  public String getTopTen() {
    return "Top 10 movies by popularity: <br>" + MOVIE_SERVICE.getTopTen();
  }

  @GetMapping("/howManyWonAnAward")
  @ResponseBody
  public String getAwardsCount() {
    return MOVIE_SERVICE.getAwardsCount();
  }

  @GetMapping("/filter")
  @ResponseBody
  public String getFilteredList(@RequestParam(value = "char") String c, @RequestParam(value = "amount") String n) {
    // example input: http://localhost:8080/filter?char=n&amount=3
    return MOVIE_SERVICE.filteredList(c, n);
  }

  @GetMapping("/howManySequals")
  @ResponseBody
  public String getSequals(@RequestParam(value = "g1") String g1, @RequestParam(value = "g2") String g2) {
    // example input: http://localhost:8080/howManySequals?g1=Action&g2=Comedy
    return MOVIE_SERVICE.getLongestAvgByInput(g1, g2);
  }

  @GetMapping("/comedy")
  @ResponseBody
  public String getListComedyAwards() {
    return MOVIE_SERVICE.getListComedy();
  }
}
