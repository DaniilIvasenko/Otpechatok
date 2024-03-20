package ru.otpechatok.data.businessCard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MaterialType")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class MaterialType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String materialType;
    @ManyToOne
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;


    public MaterialType(String materialType, BusinessCard businessCard) {
        this.materialType = materialType;
        this.businessCard = businessCard;
    }
}
