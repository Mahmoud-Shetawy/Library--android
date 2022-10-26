package com.example.libraryprojectpart.model;

public class Book {
    private String title , description , author , imgUrl;
    private int pages , review;
    private int _id;
    private float rating;
    private int drawableResource;

    public Book() {
    }

    public Book( String title , String author ) {
    }

    public Book( String title , String author , int pages ) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book( int drawableResource ) {
        this.drawableResource = drawableResource;
    }

    public Book(  int _id ,String title , String author, int pages ,String description  , String imgUrl  , int review , float rating , int drawableResource ) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.imgUrl = imgUrl;
        this.pages = pages;
        this.review = review;
        this._id = _id;
        this.rating = rating;
        this.drawableResource = drawableResource;
    }

    public Book( int _id,String title , String author , int pages   ) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this._id = _id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl( String imgUrl ) {
        this.imgUrl = imgUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages( int pages ) {
        this.pages = pages;
    }

    public int getReview() {
        return review;
    }

    public void setReview( int review ) {
        this.review = review;
    }


    public int get_id() {
        return _id;
    }

    public void set_id( int _id ) {
        this._id = _id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating( float rating ) {
        this.rating = rating;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource( int drawableResource ) {
        this.drawableResource = drawableResource;
    }


}
