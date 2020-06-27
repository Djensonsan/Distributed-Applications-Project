package dev.beans;

import dev.customExceptions.ItemDisplayAlreadyExistsException;
import dev.customExceptions.ItemDisplayNotFoundException;
import dev.entities.ItemDisplayEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class DisplayBean {
    @PersistenceContext(unitName = "DAPersistenceUnit")
    private EntityManager em;

    public DisplayBean() {}

    public ItemDisplayEntity getImage(String imageName) throws ItemDisplayNotFoundException {
        ItemDisplayEntity displayEntity = em.find(ItemDisplayEntity.class, imageName);
        if(displayEntity == null){
            throw new ItemDisplayNotFoundException();
        }
        return displayEntity;
    }

    public void addImage(ItemDisplayEntity displayEntity) throws ItemDisplayAlreadyExistsException {
        try {
            em.persist(displayEntity);
            em.flush();
        } catch (PersistenceException e){
            String cause = e.getMessage();
            if(cause.contains("Internal Exception: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry")){
                throw new ItemDisplayAlreadyExistsException();
            } else {
                throw e;
            }
        }
    }

    public void deleteImage(String imageName) throws ItemDisplayNotFoundException {
        ItemDisplayEntity displayEntity = em.find(ItemDisplayEntity.class, imageName);
        if(displayEntity == null){
            throw new ItemDisplayNotFoundException();
        }
        em.remove(displayEntity);
    }
}
