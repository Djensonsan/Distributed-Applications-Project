package dev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Displays")
public class ItemDisplayEntity implements Serializable {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long itemDisplayId;
    @Id
    @Column(nullable = false)
    private String imageName;
    @Column(nullable = false)
    private String contentType;
    @Lob
    private byte [] image;
    @ManyToMany(mappedBy = "itemDisplays")
    private List<ItemEntity> items = new ArrayList<>();

    public ItemDisplayEntity() {
    }

    public ItemDisplayEntity(String imageName , String contentType, byte [] image) {
        this.imageName = imageName;
        this.contentType = contentType;
        this.image = image;
    }

//    public Long getItemDisplayId() {
//        return itemDisplayId;
//    }
//
//    public void setItemDisplayId(Long itemDisplayId) {
//        this.itemDisplayId = itemDisplayId;
//    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
