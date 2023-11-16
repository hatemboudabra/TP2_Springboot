package net.javaguides.springboot.springbootbackend.Model;

import lombok.*;
import net.javaguides.springboot.springbootbackend.Ennumeration.SourceMvtStk;
import net.javaguides.springboot.springbootbackend.Ennumeration.TypeMvtStk;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "MvtStk")

public class MvtStk extends AbstractEntity {
    @Column(name = "datemvt")
    private Instant dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvt;

    @Column(name = "sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStk sourceMvt;

    @Column(name = "identreprise")
    private Integer idEntreprise;

}
