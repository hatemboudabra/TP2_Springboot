package net.javaguides.springboot.springbootbackend.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import net.javaguides.springboot.springbootbackend.Services.ArticleService;
import net.javaguides.springboot.springbootbackend.Services.FlickrService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleRequest>{
   private FlickrService flickrService;
    private ArticleService articleService;

    @Autowired
    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }
    @Override
    public ArticleRequest savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ArticleRequest articleRequest = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        articleRequest.setPhoto(urlPhoto);
        return articleService.save(articleRequest);

    }


}
