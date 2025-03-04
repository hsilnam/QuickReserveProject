package com.sparta.quickreserveproject.order.service;

import com.sparta.quickreserveproject.cart.entity.CartItem;
import com.sparta.quickreserveproject.order.dto.OrderPlaceCartRequestDto;
import com.sparta.quickreserveproject.order.dto.OrderPlaceDirectRequestDto;
import com.sparta.quickreserveproject.order.entity.Order;
import com.sparta.quickreserveproject.order.entity.OrderItem;
import com.sparta.quickreserveproject.order.repository.OrderItemRepository;
import com.sparta.quickreserveproject.order.repository.OrderRepository;
import com.sparta.quickreserveproject.cart.repository.CartItemRepository;
import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public void placeOrderFromCart(OrderPlaceCartRequestDto dto) {
        double calculatedTotalPrice = dto.getOrderItems().stream()
                .map(item -> {
                    CartItem cartItem = cartItemRepository.findById(item.getCartItemPk())
                            .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));

                    Product product = productRepository.findById(cartItem.getProductPk())
                            .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

                    if (product.getProductStock() < cartItem.getCartItemQuantity()) {
                        throw new IllegalArgumentException("재고가 부족합니다: " + product.getProductName());
                    }

                    product.setProductStock(product.getProductStock() - cartItem.getCartItemQuantity());
                    productRepository.save(product);

                    return (double) (cartItem.getCartItemPrice() * cartItem.getCartItemQuantity()); // TODO: 물건 price 자체를 double로 만들지 고민 check
                })
                .reduce(0.0, Double::sum);

        Order order = Order.builder()
                .userPk(dto.getUserPk())
                .totalPrice(calculatedTotalPrice)
                .status(Order.OrderStatus.PENDING)
                .build();
        order = orderRepository.save(order);

        for (OrderPlaceCartRequestDto.OrderItem item : dto.getOrderItems()) {
            CartItem cartItem = cartItemRepository.findById(item.getCartItemPk())
                    .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productPk(cartItem.getProductPk())
                    .quantity(cartItem.getCartItemQuantity())
                    .price(cartItem.getCartItemPrice())
                    .build();
            orderItemRepository.save(orderItem);

            cartItemRepository.delete(cartItem);
        }
    }

    @Override
    public void placeDirectOrder(OrderPlaceDirectRequestDto dto) {
        Order order = Order.builder()
                .userPk(dto.getUserPk())
                .status(Order.OrderStatus.PENDING)
                .build();
        order = orderRepository.save(order);

        int totalPrice = 0;
        for (OrderPlaceDirectRequestDto.OrderItem item : dto.getOrderItems()) {
            Product product = productRepository.findById(item.getProductPk())
                    .orElseThrow(() -> new IllegalArgumentException("상품 정보를 찾을 수 없습니다."));

            if (product.getProductStock() < item.getQuantity()) {
                throw new IllegalArgumentException("재고가 부족합니다: " + product.getProductName());
            }

            product.setProductStock(product.getProductStock() - item.getQuantity());
            productRepository.save(product);

            int itemTotalPrice = product.getProductPrice() * item.getQuantity();
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productPk(item.getProductPk())
                    .quantity(item.getQuantity())
                    .price(itemTotalPrice)
                    .build();
            orderItemRepository.save(orderItem);

            totalPrice += itemTotalPrice;
        }
        order.setTotalPrice(totalPrice);
    }
}
