package com.sparta.quickreserveproject.wishList.service;

import com.sparta.quickreserveproject.wishList.dto.WishListAddDto;
import com.sparta.quickreserveproject.user.dto.UserProductWishListDto;
import com.sparta.quickreserveproject.product.entity.Product;
import com.sparta.quickreserveproject.wishList.entity.WishList;
import com.sparta.quickreserveproject.product.repository.ProductRepository;
import com.sparta.quickreserveproject.wishList.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    private WishListRepository userProductWishRepository;

    @Autowired
//    private ProductService productService; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private ProductRepository productRepository;

    @Override
    public void addWish(WishListAddDto.Request dto) {
        Product product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        WishList userProductWish = new WishList(dto.getUserPk(), product.getProductPk());
        userProductWishRepository.save(userProductWish);
    }

    @Override
    public UserProductWishListDto.Response getWishList(UserProductWishListDto.Request dto) {
        Pageable pageable = PageRequest.of(0, dto.getSize());
        Page<WishList> wishPage = (dto.getCursor() == null) ?
                userProductWishRepository.findAllByOrderByUserProductWishPkAsc(pageable) :
                userProductWishRepository.findByUserProductWishPkGreaterThanOrderByUserProductWishPkAsc(dto.getCursor(), pageable);

        List<UserProductWishListDto.Response.Wish> wishDtoList = wishPage.stream()
                .map(wish -> new UserProductWishListDto.Response.Wish(
//                        wish.getProduct(), wish.getProduct().getProductName(), wish.getProduct().getProductDescription(),
//                        wish.getProduct().getProductPrice(), wish.getProduct().getProductStock(), wish.getProduct().getProductAvgRating(), wish.getProduct().getProductReviewCount() // TODO: MSA로 정보 가져오기
                ))
                .collect(Collectors.toList());

        Long nextCursor = wishPage.hasNext() ?
                wishDtoList.get(wishDtoList.size() - 1).getProductPk() : null;
        return new UserProductWishListDto.Response(wishDtoList, nextCursor);
    }
}
