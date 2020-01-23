/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author ahmed
 */
@Entity
@NamedQuery(name = "Image.deleteAllRows", query = "DELETE from Image")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String imageUrl;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Kayak kayakID;

    public Image() {
    }

    public Image(String imageUrl ) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Kayak getKayakID() {
        return kayakID;
    }

    public void setKayakID(Kayak kayakID) {
        this.kayakID = kayakID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", imageUrl=" + imageUrl + ", kayakID=" + kayakID + '}';
    }

    
}
