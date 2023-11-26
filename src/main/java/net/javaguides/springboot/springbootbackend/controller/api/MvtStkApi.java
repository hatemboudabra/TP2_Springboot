package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.APP_ROOT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.MvtStkRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Api("MvtStkApi")
public interface MvtStkApi {
    @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
    List<MvtStkRequest> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(APP_ROOT + "/mvtstk/entree")
    MvtStkRequest entreeStock(@RequestBody MvtStkRequest mvtStkRequest );

    @PostMapping(APP_ROOT + "/mvtstk/sortie")
    MvtStkRequest sortieStock(@RequestBody MvtStkRequest mvtStkRequest);

    @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
    MvtStkRequest correctionStockPos(@RequestBody MvtStkRequest mvtStkRequest);

    @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
    MvtStkRequest correctionStockNeg(@RequestBody MvtStkRequest mvtStkRequest);
}
