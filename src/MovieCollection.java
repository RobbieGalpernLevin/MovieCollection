import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();

      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie object to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    ArrayList<String> entireCast = new ArrayList<>();
    System.out.print("Enter a person to search for: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
//    for (int i = 0; i < movies.size(); i++)
//    {
//      String movieCast = movies.get(i).getCast();
//      movieCast = movieCast.toLowerCase();
//
//      if (movieCast.indexOf(searchTerm) != -1)
//      {
//        //add the Movie object to the results list
//        results.add(movies.get(i));
//      }
//    }

    // sort the results by title
//    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < movies.size(); i++)
    {
      String cast = movies.get(i).getCast();
      String[] castList = cast.split("\\|");
      for (String string : castList)
      {
        String str = string.toLowerCase();
        if (str.indexOf(searchTerm) != -1)
        {
          if (entireCast.indexOf(str) == -1)
          {
            entireCast.add(str);
          }
        }
      }
    }

    for (int j = 1; j < entireCast.size(); j++)
    {
      String temp = entireCast.get(j);

      int possibleIndex = j;
      while (possibleIndex > 0 && temp.compareTo(entireCast.get(possibleIndex - 1)) < 0)
      {
        entireCast.set(possibleIndex, entireCast.get(possibleIndex - 1));
        possibleIndex--;
      }
      entireCast.set(possibleIndex, temp);
    }
      // this will print index 0 as choice 1 in the results list; better for user!
      for(int i = 0; i < entireCast.size(); i++)
      {
        int choiceNum = i + 1;

        System.out.println("" + choiceNum + ". " + entireCast.get(i));
      }

    System.out.println("Which actor would you like to learn more about?");
    System.out.print("Enter number: ");

    int actorNum = scanner.nextInt();
    scanner.nextLine();
    String actor = entireCast.get(actorNum - 1);
    for(int i = 0; i < movies.size(); i++)
    {
      if(movies.get(i).getCast().indexOf(actor) != -1)
      {
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + movies.get(i).getTitle());
      }
    }


    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void searchKeywords()
  {
    System.out.print("Enter a keyword search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieKeyword = movies.get(i).getKeywords();
      movieKeyword = movieKeyword.toLowerCase();

      if (movieKeyword.indexOf(searchTerm) != -1)
      {
        //add the Movie object to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listGenres()
  {
      ArrayList<String> genres = new ArrayList<>();
      genres.add("Action");
      genres.add("Adventure");
      genres.add("Animation");
      genres.add("Comedy");
      genres.add("Crime");
      genres.add("Documentary");
      genres.add("Drama");
      genres.add("Family");
      genres.add("Fantasy");
      genres.add("Foreign");
      genres.add("History");
      genres.add("Horror");
      genres.add("Music");
      genres.add("Mystery");
      genres.add("Romance");
      genres.add("Science Fiction");
      genres.add("TV Movie");
      genres.add("Thriller");
      genres.add("War");
      genres.add("Western");
      for(int i = 0; i < genres.size(); i++)
      {
        System.out.println("" + (i + 1) + ". " + genres.get(i));
      }

    System.out.println("Which genre would you like to learn more about?");
    System.out.print("Enter number: ");

    int genreChoice = scanner.nextInt();
    scanner.nextLine();

    String selectedGenre = genres.get(genreChoice - 1);
    ArrayList<Movie> moviesToList = new ArrayList<>();
    for(int i = 0; i < movies.size(); i++)
    {
      if(movies.get(i).getGenres().indexOf(selectedGenre) != -1)
      {
          moviesToList.add(movies.get(i));
      }
    }

    for (int j = 1; j < moviesToList.size(); j++)
    {
      Movie temp = moviesToList.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(moviesToList.get(possibleIndex - 1).getTitle()) < 0)
      {
        moviesToList.set(possibleIndex, moviesToList.get(possibleIndex - 1));
        possibleIndex--;
      }
      moviesToList.set(possibleIndex, temp);
    }

    for (int i = 0; i < moviesToList.size(); i++)
    {
      String title = moviesToList.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }


    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = moviesToList.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listHighestRated()
  {
    String title = "";
    double rating = 0.0;
    int index1 = 0;
    for(int i = 1; i < 51; i++)
    {
      for(int z = 1; z < movies.size(); z++)
      {
        if(z == 1 && movies.get(z-1).getUserRating() > movies.get(z).getUserRating())
        {
          title = movies.get(z-1).getTitle();
          rating = movies.get(z-1).getUserRating();
          index1 = z-1;
        }
        if(z == 1 && movies.get(z-1).getUserRating() < movies.get(z).getUserRating())
        {
          title = movies.get(z).getTitle();
          rating = movies.get(z).getUserRating();
          index1 = z;
        }
        if(rating < movies.get(z).getUserRating() && z!=1)
        {
          title = movies.get(z).getTitle();
          rating = movies.get(z).getUserRating();
          index1 = z;
        }
      }
      System.out.println(i + ": " + title + " " + rating);
      movies.remove(index1);
    }
  }
  
  private void listHighestRevenue()
  {
    String title = "";
    double revenue = 0.0;
    int index1 = 0;
    for(int i = 1; i < 51; i++)
    {
      for(int z = 1; z < movies.size(); z++)
      {
        if(z == 1 && movies.get(z-1).getRevenue() > movies.get(z).getRevenue())
        {
          title = movies.get(z-1).getTitle();
          revenue = movies.get(z-1).getRevenue();
          index1 = z-1;
        }
        if(z == 1 && movies.get(z-1).getRevenue() < movies.get(z).getRevenue())
        {
          title = movies.get(z).getTitle();
          revenue = movies.get(z).getRevenue();
          index1 = z;
        }
        if(revenue < movies.get(z).getRevenue() && z!=1)
        {
          title = movies.get(z).getTitle();
          revenue = movies.get(z).getRevenue();
          index1 = z;
        }
      }
      System.out.println(i + ": " + title + " " + revenue);
      movies.remove(index1);
    }
  }

  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies = new ArrayList<Movie>();

      while ((line = bufferedReader.readLine()) != null)
      {
        // import all cells for a single row as an array of Strings,
        // then convert to ints as needed
        String[] movieFromCSV = line.split(",");

        // pull out the data for this cereal
        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        // create Cereal object to store values
        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);

        // adding Cereal object to the arraylist
        movies.add(nextMovie);
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());
    }
  }
  
  // ADD ANY ADDITIONAL PRIVATE HELPER METHODS you deem necessary

}