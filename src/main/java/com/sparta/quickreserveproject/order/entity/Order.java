package com.sparta.quickreserveproject.order.entity;

import com.sparta.quickreserveproject.user.entity.User;
import com.sparta.quickreserveproject.global.entity.CEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends CEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderPk;

    @Column(nullable = false)
    private Long userPk;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    public enum OrderStatus {
        PENDING, SHIPPED, COMPLETED, CANCELLED
    }
}
