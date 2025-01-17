package com.sparta.cartservice.service;

import com.sparta.cartservice.client.ProductClient;
import com.sparta.cartservice.dto.CartItemAddRequestDto;
import com.sparta.cartservice.dto.CartRequestDto;
import com.sparta.cartservice.dto.CartResponseDto;
import com.sparta.cartservice.dto.ProductResponseDto;
import com.sparta.cartservice.entity.Cart;
import com.sparta.cartservice.entity.CartItem;
import com.sparta.cartservice.repository.CartItemRepository;
import com.sparta.cartservice.repository.CartRepository;
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
    private final ProductClient productClient;

    @Override
    public void addItemToCart(CartItemAddRequestDto dto) {
        ProductResponseDto product = productClient.getProduct(dto.getProductPk());

        if (product == null || product.getProductStock() < dto.getQuantity()) {
            throw new IllegalArgumentException("재고가 부족하거나 상품을 찾을 수 없습니다.");
        }

        if (product.getProductStock() < dto.getQuantity()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        Cart cart = cartRepository.findByUserPk(dto.getUserPk())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .userPk(dto.getUserPk())
                            .build();
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .productPk(product.getProductPk())
                .cartItemQuantity(dto.getQuantity())
                .cartItemPrice(product.getProductPrice() * dto.getQuantity())
                .build();

        cartItemRepository.save(cartItem);

    }

    @Override
    public CartResponseDto getCart(CartRequestDto dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());

        Cart cart = cartRepository.findByUserPk(dto.getUserPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 카트를 찾을 수 없습니다."));

        Page<CartItem> cartItemPage = (dto.getCursor() == null) ?
                cartItemRepository.findByCart_CartPkOrderByCartItemPkAsc(cart.getCartPk(), pageable) :
                cartItemRepository.findByCart_CartPkAndCartItemPkGreaterThanOrderByCartItemPkAsc(
                        cart.getCartPk(), dto.getCursor(), pageable
                );

        List<CartResponseDto.CartItem> cartItemDtoList = cartItemPage.stream()
                .map(cartItem -> new CartResponseDto.CartItem(
                        cartItem.getCartItemPk(),
                        cartItem.getProductPk(),
                        "temp", // TODO: MSA를 통해 정보 가져올 것
                        cartItem.getCartItemQuantity(),
                        cartItem.getCartItemPrice()
                ))
                .collect(Collectors.toList());

        Long nextCursor = cartItemPage.hasNext() ?
                cartItemDtoList.get(cartItemDtoList.size() - 1).getCartItemPk() : null;

        return new CartResponseDto(cartItemDtoList, nextCursor);

    }
}
