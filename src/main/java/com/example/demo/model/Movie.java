package com.example.demo.model;

public class Movie {

  private int movieId;
  private String title;
  private int year;
  private int length;
  private String subject;
  private int popularity;
  private String awards;

  public int getMovieId() {
    return movieId;
  }

  public void setMovieId(int movieId) {
    this.movieId = movieId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getPopularity() {
    return popularity;
  }

  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }

  public String getAwards() {
    return awards;
  }

  public void setAwards(String awards) {
    this.awards = awards;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "movieId=" + movieId +
        ", title='" + title + '\'' +
        ", year=" + year +
        ", length=" + length +
        ", subject='" + subject + '\'' +
        ", popularity=" + popularity +
        ", awards='" + awards + '\'' +
        '}';
  }
}
