package com.example.shopee.repository.impl;

import com.example.shopee.dto.product.ProductSearchDTO;
import com.example.shopee.entity.Product;
import com.example.shopee.repository.IProductCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IProductCustomRepositoryImpl implements IProductCustomRepository {
    private EntityManager entityManager;

    public IProductCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Product> search(ProductSearchDTO productSearchDTO, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder(); // dùng để sử dụng các phép như or, and.....
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);  // tạo câu truy vấn dựa trên entity
        Root<Product> root = cq.from(Product.class); // tạo đối tượng root đại diện cho Employee
        Predicate predicate = cb.conjunction();  // tạo 1 biểu thức rỗng

        // Tìm kiếm theo tên
        if (productSearchDTO.getTitle() != null) {
            predicate = cb.and(predicate, cb.like(root.get("title"), "%" + productSearchDTO.getTitle() + "%"));
        }

        // Tìm kiếm theo danh mục
        if (productSearchDTO.getCategoryIds() != null && !productSearchDTO.getCategoryIds().isEmpty()) {
            predicate = cb.and(predicate, root.get("category").get("id").in(productSearchDTO.getCategoryIds()));
        }

        // Tìm kiếm theo giá
        if ("lt2".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.lt(root.get("discount"), 2000000));
        } else if ("2-5".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("discount"), 2000000),
                    cb.lessThan(root.get("discount"), 5000000));
        } else if ("5-10".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("discount"), 5000000),
                    cb.lessThan(root.get("discount"), 10000000));
        } else if ("gt10".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.ge(root.get("discount"), 10000000));
        }

        // thực hiện câu truy vấn
        cq.select(root).where(predicate);

        // Thêm sắp xếp từ Pageable
        if (pageable.getSort().isSorted()) {
            cq.orderBy(criteriaBuilderToOrder(cb, root, pageable.getSort()));
        }

        TypedQuery<Product> query = entityManager.createQuery(cq);

        // Thiết lập phân trang và trả về Page
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> resultList = query.getResultList();
        long total = countProductsWithCriteria(productSearchDTO);

        return new PageImpl<>(resultList, pageable, total);
    }

    // Trả về số lượng tìm kiếm dựa trên productSearchDTO
    private long countProductsWithCriteria(ProductSearchDTO productSearchDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> root = cq.from(Product.class);
        Predicate predicate = cb.conjunction();

        // Tìm kiếm theo tên
        if (productSearchDTO.getTitle() != null) {
            predicate = cb.and(predicate, cb.like(root.get("title"), "%" + productSearchDTO.getTitle() + "%"));
        }

        // Tìm kiếm theo danh mục
        if (productSearchDTO.getCategoryIds() != null && !productSearchDTO.getCategoryIds().isEmpty()) {
            predicate = cb.and(predicate, root.get("category").get("id").in(productSearchDTO.getCategoryIds()));
        }

        // Tìm kiếm theo giá
        if ("lt2".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.lt(root.get("discount"), 2000000));
        } else if ("2-5".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("discount"), 2000000),
                    cb.lessThan(root.get("discount"), 5000000));
        } else if ("5-10".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("discount"), 5000000),
                    cb.lessThan(root.get("discount"), 10000000));
        } else if ("gt10".equals(productSearchDTO.getDiscount())) {
            predicate = cb.and(predicate, cb.ge(root.get("discount"), 10000000));
        }

        cq.select(cb.count(root)).where(predicate);

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    // Chuyển đổi Spring Sort thành CriteriaBuilder Order
    private List<Order> criteriaBuilderToOrder(CriteriaBuilder cb, Root<Product> root, Sort sort) {
        List<Order> orders = new ArrayList<>();
        for (Sort.Order order : sort) {
            if (order.isAscending()) {
                orders.add(cb.asc(root.get(order.getProperty())));
            } else {
                orders.add(cb.desc(root.get(order.getProperty())));
            }
        }
        return orders;
    }
}

