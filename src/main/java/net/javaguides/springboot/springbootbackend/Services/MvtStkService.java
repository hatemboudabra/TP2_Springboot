package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.MvtStkRequest;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {
    BigDecimal stockReelArticle(Integer idArticle);

    List<MvtStkRequest> mvtStkArticle(Integer idArticle);
    MvtStkRequest entreeStock(MvtStkRequest mvtStkRequest);


    MvtStkRequest sortieStock(MvtStkRequest mvtStkRequest);
    MvtStkRequest correctionStockPos(MvtStkRequest mvtStkRequest);

    MvtStkRequest correctionStockNeg(MvtStkRequest mvtStkRequest);
}
