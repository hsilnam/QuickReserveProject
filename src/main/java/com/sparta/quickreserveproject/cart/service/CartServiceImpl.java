package com.sparta.quickreserveproject.cart.service;

import com.sparta.quickreserveproject.cart.dto.CartItemAddRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartRequestDto;
import com.sparta.quickreserveproject.cart.dto.CartResponseDto;
import com.sparta.quickreserveproject.cart.entity.Cart;
import com.sparta.quickreserveproject.cart.entity.CartItem;
import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.user.entity.User;
import com.sparta.quickreserveproject.cart.repository.CartItemRepository;
import com.sparta.quickreserveproject.cart.repository.CartRepository;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public CartResponseDto getCart(CartRequestDto dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());

        Page<CartItem> cartItemPage = (dto.getCursor() == null) ?
                cartItemRepository.findAllByCart_User_UserPkOrderByCartItemPkAsc(dto.getUserPk(), pageable) :
                cartItemRepository.findByCart_User_UserPkAndCartItemPkGreaterThanOrderByCartItemPkAsc(
                        dto.getUserPk(), dto.getCursor(), pageable
                );

        List<CartResponseDto.CartItem> cartItemDtoList = cartItemPage.stream()
                .map(cartItem -> new CartResponseDto.CartItem(
                        cartItem.getCartItemPk(),
                        cartItem.getProduct().getProductPk(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getCartItemQuantity(),
                        cartItem.getCartItemPrice()
                ))
                .collect(Collectors.toList());

        Long nextCursor = cartItemPage.hasNext() ?
                cartItemDtoList.get(cartItemDtoList.size() - 1).getCartItemPk() : null;

        return new CartResponseDto(cartItemDtoList, nextCursor);

    }
}
