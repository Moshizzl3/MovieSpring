package com.example.demo.repository;

import com.example.demo.model.Movie;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MovieRepository {

  public List<Movie> getMovies() {

    List<Movie> result = new ArrayList<>();
    //Static method getConnection so this is ok.
    Connection connection = DBManager.getConnection();
    String sqlStatement = "SELECT * FROM movies";
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    try {
      preparedStatement = connection.prepareStatement(sqlStatement);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt(1));
        movie.setTitle(resultSet.getString(2));
        movie.setYear(resultSet.getInt(3));
        movie.setLength(resultSet.getInt(4));
        movie.setSubject(resultSet.getString(5));
        movie.setPopularity(resultSet.getInt(6));
        movie.setAwards(resultSet.getString(7));
        result.add(movie);
      }

    } catch (SQLException sq) {
      System.out.println(sq.getMessage());
    }
    return result;
  }

  public Movie getRandomMovie() {
    List<Movie> movies = getMovies();
    Random random = new Random();
    int randomInt = random.nextInt(movies.size());
    return movies.get(randomInt);
  }

  public List<Movie> topTenMovies() {
    List<Movie> result = new ArrayList<>();
    Connection connection = DBManager.getConnection();
    String sqlStatement =
        "SELECT * FROM (SELECT * FROM movies ORDER BY RAND () LIMIT 10) as random ORDER BY popularity DESC";
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    try {
      preparedStatement = connection.prepareStatement(sqlStatement);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt(1));
        movie.setTitle(resultSet.getString(2));
        movie.setYear(resultSet.getInt(3));
        movie.setLength(resultSet.getInt(4));
        movie.setSubject(resultSet.getString(5));
        movie.setPopularity(resultSet.getInt(6));
        movie.setAwards(resultSet.getString(7));
        result.add(movie);
      }

    } catch (SQLException sq) {
      System.out.println(sq.getMessage());
    }
    return result;
  }

  public int getWonAward() {

    int result = -1;
    Connection connection = DBManager.getConnection();
    String sqlStatement = "SELECT COUNT(*) FROM movies WHERE awards = 'yes'";
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    try {
      preparedStatement = connection.prepareStatement(sqlStatement);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        result = resultSet.getInt(1);
      }
    } catch (SQLException sq) {
      System.out.println(sq.getMessage());
    }
    return result;
  }

  public List<Movie> filterOnCharNTimes(char c, int n) {

    List<Movie> movies = getMovies();
    List<Movie> result = new ArrayList<>();

    for (Movie m : movies) {
      if (countChar(c, m.getTitle()) == n) {
        result.add(m);
      }
    }

    return result;
  }

  private int countChar(char x, String name) {
    char[] chars = name.toCharArray();
    int result = 0;
    for (char c : chars) {
      if (c == x) {
        result++;
      }
    }
    return result;
  }

  public List<Movie> getListComedy() {
    List<Movie> result = new ArrayList<>();
    Connection connection = DBManager.getConnection();
    String sqlStatement =
        "SELECT * FROM movies\n" +
            "WHERE subject = \"Comedy\" AND awards = \"YES\"";
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    try {
      preparedStatement = connection.prepareStatement(sqlStatement);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt(1));
        movie.setTitle(resultSet.getString(2));
        movie.setYear(resultSet.getInt(3));
        movie.setLength(resultSet.getInt(4));
        movie.setSubject(resultSet.getString(5));
        movie.setPopularity(resultSet.getInt(6));
        movie.setAwards(resultSet.getString(7));
        result.add(movie);
      }

    } catch (SQLException sq) {
      System.out.println(sq.getMessage());
    }
    return result;
  }

  public String getLongestAvgByGenre(String g1, String g2) {
    String result = "";
    Connection connection = DBManager.getConnection();
    String sqlStatement = "SELECT subject, AVG(length) as avg\n" +
        "FROM movies\n" +
        "WHERE subject = " + "\"" + g1 + "\"" + "OR subject = " + "\"" + g2 + "\"" + "\n" +
        "GROUP BY subject\n" +
        "ORDER BY avg DESC";
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    try {
      preparedStatement = connection.prepareStatement(sqlStatement);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        result = resultSet.getString(1);
        result += " : ";
        result += resultSet.getInt(2);
      }
    } catch (SQLException sq) {
      System.out.println(sq.getMessage());
    }
    return result;
  }

}
