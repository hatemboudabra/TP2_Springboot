package net.javaguides.springboot.springbootbackend.controller;

import com.flickr4java.flickr.FlickrException;
import net.javaguides.springboot.springbootbackend.Services.strategy.StrategyPhotoContext;
import net.javaguides.springboot.springbootbackend.controller.api.PhotoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {
    StrategyPhotoContext strategyPhotoContext;

    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }
    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContext.savePhoto(context,id,photo.getInputStream(),title);
    }


}
