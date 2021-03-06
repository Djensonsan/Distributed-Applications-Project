package dev.RESTwebservices;

import dev.beans.ItemBean;
import dev.customExceptions.ItemDisplayNotFoundException;
import dev.customExceptions.ItemNotFoundException;
import dev.DTOs.ItemDTO;
import dev.entities.ItemEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

@Path("items")
@Stateless
public class ItemRestService {
    @Context
    private UriInfo uriInfo;

    @EJB
    ItemBean itemBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(ItemEntity itemEntity){
        if(itemEntity == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        itemBean.addItem(itemEntity);
        return Response.ok(itemEntity, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add/{itemId}/{imageName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addImageToItem(@PathParam("imageName") String imageName, @PathParam("itemId") Long itemId){
        try{
            itemBean.addImageToItem(itemId, imageName);
            return Response.ok().build();
        } catch (ItemNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (ItemDisplayNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemId") Long itemId){
        try {
            ItemEntity itemEntity = itemBean.getItem(itemId);
            return Response.ok(itemEntity, MediaType.APPLICATION_JSON).build();
        } catch (ItemNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/getImages/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllImages(@PathParam("itemId") Long itemId){
        try {
            ArrayList<String> itemDisplayNames = itemBean.getAllImages(itemId);
            return Response.ok(itemDisplayNames, MediaType.APPLICATION_JSON).build();
        } catch (ItemNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems(){
        ArrayList <ItemDTO> itemDTOs = itemBean.getAllItems();
        return Response.ok(itemDTOs, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{itemId}")
    public Response deleteItem(@PathParam("itemId") Long itemId){
        try {
            itemBean.deleteItem(itemId);
            return Response.ok().build();
        } catch (ItemNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
