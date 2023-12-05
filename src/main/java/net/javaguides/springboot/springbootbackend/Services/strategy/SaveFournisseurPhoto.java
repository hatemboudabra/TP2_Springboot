package net.javaguides.springboot.springbootbackend.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.FournisseurRequest;
import net.javaguides.springboot.springbootbackend.Services.FlickrService;
import net.javaguides.springboot.springbootbackend.Services.FournisseurService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("fournissuerStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurRequest> {
    FlickrService flickrService;
    FournisseurService fournisseurService;
    @Autowired
    public SaveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
        this.flickrService = flickrService;
        this.fournisseurService = fournisseurService;
    }
    @Override
    public FournisseurRequest savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        FournisseurRequest fournisseurRequest = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo du fournisseur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseurRequest.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseurRequest);
    }

}
