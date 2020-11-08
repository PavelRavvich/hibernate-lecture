package com.pravvich.lecturehibernate;

import com.pravvich.lecturehibernate.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public long count() {
        return productRepository.count();
    }

}
