package br.com.douglasffilho.CarServices.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
@EqualsAndHashCode(callSuper = false)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private CarMaker maker;

    @Column(name = "build_year", nullable = false)
    private Integer buildYear;

    @Column(name = "image", length = 15000000)
    private String image;

    @Column(name = "description", nullable = false)
    private String description;

}
