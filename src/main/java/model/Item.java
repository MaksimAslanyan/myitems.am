package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private int id;
    private String title;
    private double price;
    private String picUrl;
    private Category categoryId;
    private User userId;
}
