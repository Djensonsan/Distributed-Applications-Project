package dev.RESTwebservices;

import dev.beans.DisplayBean;
import dev.customExceptions.ItemDisplayAlreadyExistsException;
import dev.customExceptions.ItemDisplayNotFoundException;
import dev.entities.ItemDisplayEntity;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.*;

@Path("images")
@Stateless
public class ImageRestService {
    @Context
    private UriInfo uriInfo;

    @EJB
    DisplayBean displayBean;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile (@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = uploadedInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        ItemDisplayEntity displayEntity = new ItemDisplayEntity(fileDetail.getFileName(), fileDetail.getType(), byteArrayOutputStream.toByteArray());
        try {
            displayBean.addImage(displayEntity);
            return Response.status(Response.Status.CREATED).build();
        } catch (ItemDisplayAlreadyExistsException e){
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @Path("/get/{imageName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getImage(@PathParam("imageName") String imageName) {
        try {
            ItemDisplayEntity displayEntity = displayBean.getImage(imageName);
            return Response.ok(displayEntity.getImage(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", "attachment; filename=" + displayEntity.getImageName()).build();
        } catch (ItemDisplayNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/delete/{imageName}")
    public Response deleteImage(@PathParam("imageName") String imageName){
        try {
            displayBean.deleteImage(imageName);
            return Response.ok().build();
        } catch (ItemDisplayNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
