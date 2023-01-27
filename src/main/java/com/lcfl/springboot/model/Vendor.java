package com.lcfl.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "xx_vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "xx_vendors_gen")
    @SequenceGenerator(name = "xx_vendors_gen", sequenceName = "xx_vendors_s")
    @Column(name = "vendor_id", nullable = false)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(1)
    @Max(99999)
    private double salary;
    private Date hireDate;
    private Date creationDate;
    private Date lastUpdateDate;

    public Vendor() {
    }

    public Vendor(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}

