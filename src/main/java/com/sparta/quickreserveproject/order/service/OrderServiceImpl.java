package com.sparta.quickreserveproject.order.service;

import com.sparta.quickreserveproject.cart.entity.CartItem;
import com.sparta.quickreserveproject.cart.repository.CartItemRepository;
import com.sparta.quickreserveproject.order.entity.Order;
import com.sparta.quickreserveproject.order.entity.OrderItem;
import com.sparta.quickreserveproject.order.repository.OrderItemRepository;
import com.sparta.quickreserveproject.order.repository.OrderRepository;
import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import com.sparta.quickreserveproject.user.entity.User;
import com.sparta.quickreserveproject.user.repository.UserRepository;
import com.sparta.quickreserveproject.order.dto.OrderPlaceDirectRequestDto;
import com.sparta.quickreserveproject.order.dto.OrderPlaceCartRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private final CartItemRepository cartItemRepository; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private final UserRepository userRepository; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요

    @Override
    public void placeOrderFromCart(OrderPlaceCartRequestDto dto) {
        // NOTE: 결제가 성공적으로 완료됐다는 전제하에
        /*User user = userRepository.findById(dto.getUserPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
*/ // TODO: MSA 리펙토링 필요
        int calculatedTotalPrice = dto.getOrderItems().stream()
                .map(item -> {
                    CartItem cartItem = cartItemRepository.findById(item.getCartItemPk())
                            .orElseThrow(() -> new IllegalArgumentException("장바구니 아이템을 찾을 수 없습니다."));

                    /*Product product = cartItem.getProduct();
                    if (product.getProductStock() < cartItem.getCartItemQuantity()) {
                        throw new IllegalArgumentException("재고가 부족합니다: " + product.getProductName());
                    }

                    product.setProductStock(product.getProductStock() - cartItem.getCartItemQuantity());
*///TODO: MSA 리펙토링 필요
                    return cartItem.getCartItemPrice() * cartItem.getCartItemQuantity();
                })
                .reduce(0, Integer::sum);


        Order order = Order.builder()
                .userPk(dto.getUserPk())
                .totalPrice(calculatedTotalPrice) // TODO: 만약 request에도 프론트에서 계산한 totalPrice 정보를 넣는다면, 서버에서 계산한 값이 정말 맞는지 검사도 해야할까?
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
            /*Product product = productRepository.findById(item.getProductPk())
                    .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
            if (product.getProductStock() < item.getQuantity()) {
                throw new IllegalArgumentException("재고가 부족합니다.");
            }

            product.setProductStock(product.getProductStock() - item.getQuantity());

            int itemTotalPrice = product.getProductPrice() * item.getQuantity();
*/
            // TODO: MSA 리펙토링 필요
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productPk(item.getProductPk())
                    .quantity(item.getQuantity())
                    .price(99999) //TODO: temp
                    .build();
            orderItemRepository.save(orderItem);

//            totalPrice += itemTotalPrice; //TODO: MSA 변환 필요
        }

        order.setTotalPrice(totalPrice);
    }
}
