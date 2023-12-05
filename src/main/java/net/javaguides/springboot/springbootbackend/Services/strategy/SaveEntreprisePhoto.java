package net.javaguides.springboot.springbootbackend.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.EntrepriseRequest;
import net.javaguides.springboot.springbootbackend.Services.EntrepriseService;
import net.javaguides.springboot.springbootbackend.Services.FlickrService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseRequest> {
    FlickrService flickrService;
    EntrepriseService entrepriseService;
    @Autowired
    public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService = entrepriseService;
    }
    @Override
    public EntrepriseRequest savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        EntrepriseRequest entrepriseRequest = entrepriseService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entreprise", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseRequest.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseRequest);
    }
}
