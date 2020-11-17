package com.pravvich.lecturehibernate.repository;

import com.pravvich.lecturehibernate.model.Customer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Override
    Optional<Customer> findById(Long id);

    @Query("SELECT c FROM Customer c JOIN FETCH c.products p WHERE c.id = :id")
    Optional<Customer> findCustomerByIdFetchProducts(Long id);

    @EntityGraph(attributePaths = {"products"})
    Optional<Customer> findCustomerById(Long id);

    @Override
    @EntityGraph(value = "customer.products")
    List<Customer> findAll(@Nullable Specification<Customer> specification);

    @AllArgsConstructor
    class CustomerSpecification implements Specification<Customer> {
        private final CustomerFilter filter;

        @Override
        public Predicate toPredicate(@NotNull Root<Customer> root,
                                     @NotNull CriteriaQuery<?> query,
                                     @NotNull CriteriaBuilder builder) {

            Predicate predicate = builder.conjunction();
            List<Expression<Boolean>> exps = predicate.getExpressions();

            filter.getAge().ifPresent(age -> exps.add(builder.greaterThan(root.get("age"), age)));
            filter.getName().ifPresent(name ->
                    exps.add(
                            builder.like(
                                    builder.lower(
                                            root.get("name")), "%" + name.toLowerCase() + "%")));

            return predicate;
        }
    }

    @Data
    @Builder
    class CustomerFilter {

        private String name;

        private Integer age;

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Integer> getAge() {
            return Optional.ofNullable(age);
        }

    }
}
