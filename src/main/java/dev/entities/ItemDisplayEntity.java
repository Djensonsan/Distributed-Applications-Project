package dev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Displays")
public class ItemDisplayEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemDisplayId;
    private String imagePath;
    @ManyToMany(mappedBy = "itemDisplays")
    private List<ItemEntity> items = new ArrayList<>();

    public ItemDisplayEntity() {
    }

    public Long getItemDisplayId() {
        return itemDisplayId;
    }

    public void setItemDisplayId(Long itemDisplayId) {
        this.itemDisplayId = itemDisplayId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
