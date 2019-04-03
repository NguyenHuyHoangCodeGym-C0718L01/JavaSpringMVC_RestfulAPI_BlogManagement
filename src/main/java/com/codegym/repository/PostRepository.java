package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    @Query("select p From Post p where p.category.id = ?1")
    Optional<Iterable<Post>> findAllByCategory(int id);

    Page<Post> findAllByName(String name, Pageable pageable);
    Iterable<Post> findAllByName(String name, Sort sort);

    Page<Post> findAllByCategoryName(String name, Pageable pageable);
    Iterable<Post> findAllByCategoryName(String name, Sort sort);
}
