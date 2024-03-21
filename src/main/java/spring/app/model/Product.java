package spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder

public class Product {
    private int id;
    private String title;
    private  String description;
    private float price;
    private String imageUrl;
}
