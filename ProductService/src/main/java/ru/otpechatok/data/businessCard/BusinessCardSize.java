package ru.otpechatok.data.businessCard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * сущность размера визитки
 */
@Entity
@Table(name = "BusinessCardSize")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BusinessCardSize{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    /**
     * размер
     */
    private String size;
    /**
     * ссылка на id визитки
     */
    @ManyToOne
    @JoinColumn(name = "business_card_id")
    private BusinessCard businessCard;


    public BusinessCardSize(String size, BusinessCard businessCard) {
        this.size = size;
        this.businessCard = businessCard;
    }

}
