package ru.otpechatok.data.stamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * размер для конкретно печати
 */
@Entity
@Table(name = "stamp_sizes")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class StampSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * размер
     */
    private String size;
    /**
     * ссылка на печать
     */
    @ManyToOne
    @JoinColumn(name = "stamp_id")
    private  Stamp stamp;


    public StampSize(String size, Stamp stamp) {
        this.size = size;
        this.stamp = stamp;
    }
}
