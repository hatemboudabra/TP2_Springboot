package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CategoryRequest {
    private Integer id;
    private String code;
    private String designation;
    private Integer idEntreprise;
   @JsonIgnore
    private List<ArticleRequest> articles;
}
