package ru.otpechatok.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otpechatok.dto.OrderDetailsDTO;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * детали заказа
 */
@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * тип продукта
     */
    private ProductType productType;
    /**
     * количество продукта, которое необходимо произвести
     */
    private int amount;
    /**
     * путь к файлу модели, полученному от клиента при оформлении заказа
     */
    private String modelFileName;
    /**
     * подробное описание продукта заказа
     */
    private String description;
    /**
     * цена за единицу продукта
     */
    private BigDecimal priceForOne;
    /**
     * ссылка на заказ к которому относятся эти детали
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    /**
     * преобразование объекта OrderDetailsDTO к объекту OrderDetails
     *
     * @param orderDetailsDTO данные на основе которых будет создан объект OrderDetails
     * @return OrderDetails
     */
    public static OrderDetails convertFromOrderDetailsDTO(OrderDetailsDTO orderDetailsDTO) {
        OrderDetails resultOrderDetails = new OrderDetails();
        resultOrderDetails.setProductType(
                Arrays.stream(ProductType.values())
                        .filter(x -> x.getProductType().equals(orderDetailsDTO.getProductType()))
                        .findFirst()
                        .orElseThrow(IllegalAccessError::new)
        );
        resultOrderDetails.setAmount(orderDetailsDTO.getAmount());
        resultOrderDetails.setModelFileName(orderDetailsDTO.getModelFileName());
        resultOrderDetails.setDescription(orderDetailsDTO.getDescription());
        resultOrderDetails.setPriceForOne(BigDecimal.valueOf(orderDetailsDTO.getPriceForOne()));
        return resultOrderDetails;
    }
}
