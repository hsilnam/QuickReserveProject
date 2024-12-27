package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.entity.Cart;
import com.sparta.quickreserveproject.entity.CartItem;
import com.sparta.quickreserveproject.entity.Product;
import com.sparta.quickreserveproject.entity.User;
import com.sparta.quickreserveproject.repository.CartItemRepository;
import com.sparta.quickreserveproject.repository.CartRepository;
import com.sparta.quickreserveproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


//    private ProductService productService; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private final ProductRepository productRepository;

    @Override
    public void addItemToCart(CartItemAddRequestDto dto) {

        Product product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        if (product.getProductStock() < dto.getQuantity()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        Cart cart = cartRepository.findByUser_UserPk(dto.getUserPk())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(User.builder().userPk(dto.getUserPk()).build())
                            .build();
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .cartItemQuantity(dto.getQuantity())
                .cartItemPrice(product.getProductPrice() * dto.getQuantity())
                .build();

        cartItemRepository.save(cartItem);

    }
}
