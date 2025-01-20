package com.sparta.orderservice.service;

import com.sparta.orderservice.client.CartClient;
import com.sparta.orderservice.client.ProductClient;
import com.sparta.orderservice.client.dto.*;
import com.sparta.orderservice.dto.OrderPlaceCartRequestDto;
import com.sparta.orderservice.dto.OrderPlaceDirectRequestDto;
import com.sparta.orderservice.entity.Order;
import com.sparta.orderservice.entity.OrderItem;
import com.sparta.orderservice.repository.OrderItemRepository;
import com.sparta.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartClient cartClient;
    private final ProductClient productClient;
//    private final UserClient userClient;

    @Override
    public void placeOrderFromCart(OrderPlaceCartRequestDto dto) {
        // NOTE: 결제가 성공적으로 완료됐다는 전제하에
        /*User user = userRepository.findById(dto.getUserPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
*/ //
        CartResponseDto cartResponse = cartClient.getCart(new CartRequestDto(null, 0, dto.getUserPk()));
        double calculatedTotalPrice = dto.getOrderItems().stream()
                .map(item -> {
                    CartResponseDto.CartItem cartItem = cartResponse.getProductList().stream()
                            .filter(ci -> ci.getCartItemPk().equals(item.getCartItemPk()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));

                    ProductResponseDto product = productClient.getProduct(cartItem.getProductPk());
                    if (product == null) {
                        throw new IllegalArgumentException("상품 정보를 찾을 수 없습니다.");
                    }

                    if (product.getProductStock() < cartItem.getQuantity()) {
                        throw new IllegalArgumentException("재고가 부족합니다: " + product.getProductName());
                    }

                    productClient.updateStockProduct(product.getProductPk(),
                            new ProductStockUpdateRequestDto().builder().
                                    productStock(product.getProductStock() - cartItem.getQuantity())
                                    .build());
                    return cartItem.getPrice() * cartItem.getQuantity();
                })
                .reduce(0.0, Double::sum);


        Order order = Order.builder()
                .userPk(dto.getUserPk())
                .totalPrice(calculatedTotalPrice) // TODO: 만약 request에도 프론트에서 계산한 totalPrice 정보를 넣는다면, 서버에서 계산한 값이 정말 맞는지 검사도 해야할까?
                .status(Order.OrderStatus.PENDING)
                .build();
        order = orderRepository.save(order);

        for (OrderPlaceCartRequestDto.OrderItem item : dto.getOrderItems()) {
            CartItemResponseDto cartItem = cartClient.getCartItem(item.getCartItemPk());
            if (cartItem == null) {
                throw new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다.");
            }

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productPk(cartItem.getProductPk())
                    .quantity(cartItem.getCartItemQuantity())
                    .price(cartItem.getCartItemPrice())
                    .build();
            orderItemRepository.save(orderItem);

            cartClient.deleteCartItem(cartItem.getCartItemPk());
        }
    }

    @Override
    public void placeDirectOrder(OrderPlaceDirectRequestDto dto) {
        // NOTE: 결제가 성공적으로 완료됐다는 전제하에
/*        User user = userRepository.findById(dto.getUserPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));*/
        // TODO: MSA 변환 필요
        Order order = Order.builder()
                .userPk(dto.getUserPk())
                .status(Order.OrderStatus.PENDING)
                .build();
        order = orderRepository.save(order);

        int totalPrice = 0;
        for (OrderPlaceDirectRequestDto.OrderItem item : dto.getOrderItems()) {

            ProductResponseDto product = productClient.getProduct(item.getProductPk());
            if (product == null) {
                throw new IllegalArgumentException("상품 정보를 찾을 수 없습니다.");
            }

            if (product.getProductStock() < item.getQuantity()) {
                throw new IllegalArgumentException("재고가 부족합니다: " + product.getProductName());
            }

            productClient.updateStockProduct(product.getProductPk(),
                    new ProductStockUpdateRequestDto().builder().
                            productStock(product.getProductStock() - item.getQuantity())
                            .build());

            // TODO: MSA 리펙토링 필요
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
