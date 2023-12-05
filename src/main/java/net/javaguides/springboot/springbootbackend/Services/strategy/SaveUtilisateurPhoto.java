package net.javaguides.springboot.springbootbackend.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.FlickrService;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurRequest>{
    private FlickrService flickrService;
    private UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurRequest savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        UtilisateurRequest utilisateurRequest = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateurRequest.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateurRequest);

    }


}
