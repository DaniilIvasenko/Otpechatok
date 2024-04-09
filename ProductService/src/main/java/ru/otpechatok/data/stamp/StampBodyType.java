package ru.otpechatok.data.stamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * тип корпуса конкретной печати
 */
@Entity
@Table(name = "stamp_body_type")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class StampBodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * название типа корпуса
     */
    private String bodyType;
    /**
     * ссылка на печать
     */
    @ManyToOne
    @JoinColumn(name = "stamp_id")
    private Stamp stamp;


    public StampBodyType(String bodyType, Stamp stamp) {
        this.bodyType = bodyType;
        this.stamp = stamp;
    }

}