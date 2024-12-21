package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.ProductListDto;
import com.sparta.quickreserveproject.dto.UserProductWishAddDto;
import com.sparta.quickreserveproject.dto.UserProductWishListDto;
import com.sparta.quickreserveproject.entity.Product;
import com.sparta.quickreserveproject.entity.UserProductWish;
import com.sparta.quickreserveproject.repository.ProductRepository;
import com.sparta.quickreserveproject.repository.UserProductWishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProductWishServiceImpl implements UserProductWishService {
    @Autowired
    private UserProductWishRepository userProductWishRepository;

    @Autowired
//    private ProductService productService; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private ProductRepository productRepository;

    @Override
    public void addWish(UserProductWishAddDto.Request dto) {
        Product product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        UserProductWish userProductWish = new UserProductWish(dto.getUserPk(), product);
        userProductWishRepository.save(userProductWish);
    }

    @Override
    public UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<UserProductWish> wishPage = (dto.getCursor() == null) ?
                userProductWishRepository.findAllByOrderByUserProductWishPkAsc(pageable) :
                userProductWishRepository.findByUserProductWishPkGreaterThanOrderByUserProductWishPkAsc(dto.getCursor(), pageable);

        List<UserProductWishListDto.Response.Wish> wishDtoList = wishPage.stream()
                .map(wish -> new UserProductWishListDto.Response.Wish(
                        wish.getProduct().getProductPk(), wish.getProduct().getProductName(), wish.getProduct().getProductDescription(),
                        wish.getProduct().getProductPrice(), wish.getProduct().getProductStock(), wish.getProduct().getProductAvgRating(), wish.getProduct().getProductReviewCount()
                ))
                .collect(Collectors.toList());

        Long nextCursor = wishPage.hasNext() ?
                wishDtoList.get(wishDtoList.size() - 1).getProductPk() : null;
        return new UserProductWishListDto.Response(wishDtoList, nextCursor);
    }
}
