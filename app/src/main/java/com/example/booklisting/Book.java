package com.example.booklisting;

public class Book {
  private String mtitle;
  private String mprice;
  private String mauthor;
  private String mdescription;


  public Book(String title,String price,String author,String description)
  {
      mtitle=title;
      mprice=price;
      mauthor=author;
      mdescription=description;
  }

  public String getMtitle(){ return mtitle; }
  public String getMprice(){return mprice;}
  public String getMauthor(){return mauthor;}
  public String getMdescription(){return mdescription;}


}
