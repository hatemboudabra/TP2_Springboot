package net.javaguides.springboot.springbootbackend.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.ClientRequest;
import net.javaguides.springboot.springbootbackend.Services.ClientService;
import net.javaguides.springboot.springbootbackend.Services.FlickrService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientRequest> {
    FlickrService flickrService;
    ClientService clientService;
    @Autowired
    public SaveClientPhoto(FlickrService flickrService, ClientService clientService) {
        this.flickrService = flickrService;
        this.clientService = clientService;
    }
    @Override
    public ClientRequest savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ClientRequest clientRequest= clientService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo du client", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        clientRequest.setPhoto(urlPhoto);
        return clientService.save(clientRequest);
    }

}
