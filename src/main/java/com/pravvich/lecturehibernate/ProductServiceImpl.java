package com.pravvich.lecturehibernate;

import com.pravvich.lecturehibernate.repository.ProductRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepositoryImpl;

    @Override
    public long count() {
        return 0;
    }

}
