package com.lcfl.springboot.controller;

import com.lcfl.springboot.model.Vendor;
import com.lcfl.springboot.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping
    public Iterable<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public Optional<Vendor> getVendorById(@PathVariable Long id) {
        return vendorRepository.findById(id);
    }

    @GetMapping(path="/name/{name}")
    public Iterable<Vendor> getVendorById(@PathVariable String name) {
        return vendorRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping(path = "/page/{pageNumber}")
    public Iterable<Vendor> getVendorById(@PathVariable int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 10);
        return vendorRepository.findAll(page);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody Vendor newVendor(@Valid Vendor vendor) {
        vendorRepository.save(vendor);
        return vendor;
    }

    @DeleteMapping(path="/{id}")
    public void deleteVendorById(@PathVariable Long id) {
        vendorRepository.deleteById(id);
    }
}
