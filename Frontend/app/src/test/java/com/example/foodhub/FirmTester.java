package com.example.foodhub;

import com.example.foodhub.Common.Firm;

import java.util.List;

public interface FirmTester {
    List<Firm> findAll();
    Firm findById(long id);
    List<Firm> findByFirmId(long firmId);
    List<Firm> findByCategoryId(long categoryId);
    void deleteById(long id);
}
