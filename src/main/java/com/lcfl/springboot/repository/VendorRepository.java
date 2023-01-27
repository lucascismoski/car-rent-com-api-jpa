package com.lcfl.springboot.repository;

import com.lcfl.springboot.model.Vendor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface VendorRepository extends PagingAndSortingRepository<Vendor, Long> {
    public Iterable<Vendor> findByNameContainingIgnoreCase(String name);
}