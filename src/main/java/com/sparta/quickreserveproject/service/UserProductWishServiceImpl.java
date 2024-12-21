package com.sparta.quickreserveproject.service;

import com.sparta.quickreserveproject.dto.UserProductWishAddDto;
import com.sparta.quickreserveproject.entity.ProductEntity;
import com.sparta.quickreserveproject.entity.UserProductWish;
import com.sparta.quickreserveproject.repository.ProductRepository;
import com.sparta.quickreserveproject.repository.UserProductWishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProductWishServiceImpl implements UserProductWishService {
    @Autowired
    private UserProductWishRepository userProductWishRepository;

    @Autowired
//    private ProductService productService; // TODO: 서비스 메서드를 통해 가져오도록 리펙토링 필요
    private ProductRepository productRepository;

    @Override
    public void addWish(UserProductWishAddDto.Request dto) {
        ProductEntity product = productRepository.findById(dto.getProductPk())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));

        UserProductWish userProductWish = new UserProductWish(dto.getUserPk(), product);
        userProductWishRepository.save(userProductWish);
    }
}
