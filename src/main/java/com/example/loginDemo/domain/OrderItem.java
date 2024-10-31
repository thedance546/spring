package com.example.loginDemo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items") // 'order_items' 테이블 이름으로 설정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", updatable = false)
    private Long id;

    @NotNull(message = "Item cannot be null")
    @ManyToOne(fetch = FetchType.LAZY) // Many-to-One 관계 설정
    @JoinColumn(name = "item_id", nullable = false) // 외래 키 컬럼 설정
    private Item item; // FK

    @NotNull(message = "Order cannot be null")
    @ManyToOne(fetch = FetchType.LAZY) // Many-to-One 관계 설정
    @JoinColumn(name = "order_id", nullable = false) // 외래 키 설정
    private Order order; // FK

    @NotNull(message = "Order price cannot be null")
    @Column(name = "order_price", nullable = false)
    private int orderPrice;

    @NotNull(message = "Count cannot be null")
    @Column(name = "count", nullable = false)
    private int count;

    @Builder
    public OrderItem(Item item, Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
