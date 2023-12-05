package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.MvtStkRequest;
import net.javaguides.springboot.springbootbackend.Services.MvtStkService;
import net.javaguides.springboot.springbootbackend.controller.api.MvtStkApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class MvtStkController implements MvtStkApi {
    MvtStkService mvtStkService;
    @Autowired
    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }
    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        return mvtStkService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkRequest> mvtStkArticle(Integer idArticle) {
        return mvtStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MvtStkRequest entreeStock(MvtStkRequest mvtStkRequest) {
        return mvtStkService.entreeStock(mvtStkRequest);
    }

    @Override
    public MvtStkRequest sortieStock(MvtStkRequest mvtStkRequest) {
        return mvtStkService.entreeStock(mvtStkRequest);
    }

    @Override
    public MvtStkRequest correctionStockPos(MvtStkRequest mvtStkRequest) {
        return mvtStkService.correctionStockPos(mvtStkRequest);
    }

    @Override
    public MvtStkRequest correctionStockNeg(MvtStkRequest mvtStkRequest) {
        return mvtStkService.correctionStockNeg(mvtStkRequest);
    }
}
