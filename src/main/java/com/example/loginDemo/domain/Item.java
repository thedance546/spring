package com.example.loginDemo.domain;

import com.example.loginDemo.exception.NotEnoughStockException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items") // 'items' 테이블 이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long id;

    @NotBlank(message = "Item_name cannot be blank")
    @Column(name = "item_name", nullable = false)
    private String name;

    @NotNull(message = "Stock quantity cannot be null")
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @NotNull(message = "Item_price cannot be null")
    @Column(name = "item_price", nullable = false)
    private int price;

    @Builder
    public Item(String name, int stockQuantity, int price) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    //비즈니스 로직
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
