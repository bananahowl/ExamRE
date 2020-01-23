/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Image;

/**
 *
 * @author ahmed
 */
public class ImageDTO {
    
    private String URL;

    public ImageDTO() {
    }

    public ImageDTO(Image image) {
        this.URL = image.getImageUrl();
    }

    public String getURL() {
        return URL;
    }
    
    
}
