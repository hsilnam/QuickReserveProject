package com.sparta.quickreserveproject.cart.service;

import com.sparta.quickreserveproject.cart.dto.*;
import com.sparta.quickreserveproject.cart.entity.Cart;
import com.sparta.quickreserveproject.cart.entity.CartItem;
import com.sparta.quickreserveproject.cart.repository.CartItemRepository;
import com.sparta.quickreserveproject.cart.repository.CartRepository;
import com.sparta.quickreserveproject.product.entity.Product;
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
    private final ProductRepository productRepository;

    @Override
    public void addItemToCart(CartItemAddRequestDto dto) {
        Product product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        if (product.getProductStock() < dto.getQuantity()) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        Cart cart = cartRepository.findByUserPk(dto.getUserPk())
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .userPk(dto.getUserPk())
                        .build()));

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
                        productRepository.findById(cartItem.getProductPk()).map(Product::getProductName).orElse("Unknown"), // TODO: 찾을 수 없는 product pk 처리 방법 check
                        cartItem.getCartItemQuantity(),
                        cartItem.getCartItemPrice()
                ))
                .collect(Collectors.toList());

        Long nextCursor = cartItemPage.hasNext() ?
                cartItemDtoList.get(cartItemDtoList.size() - 1).getCartItemPk() : null;

        return new CartResponseDto(cartItemDtoList, nextCursor);
    }

    @Override
    public CartItemResponseDto getCartItem(Long userPk, Long itemPk) {
        CartItem cartItem = cartItemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 카트 아이템을 찾을 수 없습니다."));
        return CartItemResponseDto.builder()
                .cartItemPk(cartItem.getCartItemPk())
                .productPk(cartItem.getProductPk())
                .cartItemPrice(cartItem.getCartItemPrice())
                .cartItemQuantity(cartItem.getCartItemQuantity())
                .build();
    }

    @Override
    public void deleteCartItem(Long userPk, Long itemPk) {
        CartItem cartItem = cartItemRepository.findById(itemPk)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 카트 아이템을 찾을 수 없습니다."));
        cartItemRepository.delete(cartItem);
    }
}
