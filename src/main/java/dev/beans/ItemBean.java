package dev.beans;

import dev.customExceptions.ItemDisplayNotFoundException;
import dev.customExceptions.ItemNotFoundException;
import dev.entities.ItemDisplayEntity;
import dev.entities.ItemEntity;
import dev.DTOs.ItemDTO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.FetchProfile;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ItemBean {

    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    @EJB
    DisplayBean displayBean;

    // Increment Stock
    // Decrement Stock
    // Remove Item
    // Update Item
    // Get All Items
    // Add Image To Item
    // Remove Image From Item

    public ItemBean() {
    }

    public void addItem(ItemEntity itemEntity){
        em.persist(itemEntity);
    }

    public ItemEntity getItem(Long itemId) throws ItemNotFoundException{
        ItemEntity itemEntity = em.find(ItemEntity.class, itemId);
        if(itemEntity == null){
            throw new ItemNotFoundException();
        }
        return itemEntity;
    }

    public void addImageToItem(Long itemId, String imageName) throws ItemDisplayNotFoundException, ItemNotFoundException {
        ItemEntity itemEntity = getItem(itemId);
        ItemDisplayEntity displayEntity = displayBean.getImage(imageName);
        if(displayEntity == null){
            throw new ItemDisplayNotFoundException();
        }
        itemEntity.addItemDisplay(displayEntity);
    }

    public ArrayList<String> getAllImages (Long itemId) throws ItemNotFoundException{
        ItemEntity itemEntity = getItem(itemId);
        List<ItemDisplayEntity> itemDisplays = itemEntity.getItemDisplays();
        ArrayList<String> itemDisplayNames = new ArrayList<>();
        for (ItemDisplayEntity i:itemDisplays) {
            itemDisplayNames.add(i.getImageName());
        }
        return itemDisplayNames;
    }

    public ArrayList<ItemDTO> getAllItems (){
        List <ItemEntity> itemEntities = em.createQuery("select item from ItemEntity item", ItemEntity.class).getResultList();
        ArrayList<ItemDTO> ItemDTOS = new ArrayList<>();
        for (ItemEntity item: itemEntities) {
            ArrayList<String> imageUrls = new ArrayList<>();
            for (ItemDisplayEntity display:item.getItemDisplays()) {
                String url = "http://localhost:8080/DA_Project/images/get/" + display.getImageName();
                imageUrls.add(url);
            }
            ItemDTO itemDTO = new ItemDTO(item.getItemId(),item.getName(),item.getDescription(),item.getStockQuantity(),item.getQuantityUnit(),item.getPrice(), imageUrls);
            ItemDTOS.add(itemDTO);
        }
        return ItemDTOS;
    }

    public void deleteItem(Long itemId) throws ItemNotFoundException{
        ItemEntity itemEntity = em.find(ItemEntity.class, itemId);
        if (itemEntity == null){
            throw new ItemNotFoundException();
        }
        em.remove(itemEntity);
    }
}
