/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Image;
import entities.Kayak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class KayakDTO {
    
    private String name;
    
    private String model;
    
    private String desciption;
    
    private int age;
    
    private int PersonAmount;
    
    private List<Image> images;
    
    public KayakDTO() {
    }

    public KayakDTO(Kayak kayakData) {
        this.name = kayakData.getName();
        this.model = kayakData.getModel();
        this.desciption = kayakData.getDesciption();
        this.age = kayakData.getYear();
        this.PersonAmount = kayakData.getPersonsAllowed();
        this.images = kayakData.getImages();
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public int getAge() {
        return age;
    }

    public int getPersonAmount() {
        return PersonAmount;
    }
    
    public List<String> getImagesLink(){
    List <String> imageList = new ArrayList();
    
    for(Image value : images){
    imageList.add(value.getImageUrl());
    }
     return imageList;   
    }

    public String getModel() {
        return model;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "KayakDTO{" + "name=" + name + ", model=" + model + ", desciption=" + desciption + ", age=" + age + ", PersonAmount=" + PersonAmount + ", images=" + images + '}';
    }
    
    
}
