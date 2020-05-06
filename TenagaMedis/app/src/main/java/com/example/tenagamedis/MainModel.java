package com.example.tenagamedis;

public class MainModel {
    Integer numberImages;
    String numberWords;

    public MainModel(Integer numberImages,String numberWords){
        this.numberImages = numberImages;
        this.numberWords = numberWords;
    }

    public Integer getNumberImages() {
        return numberImages;
    }

    public String getNumberWords(){
        return numberWords;
    }
}
