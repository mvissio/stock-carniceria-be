package com.svcg.StockCustom.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svcg.StockCustom.entity.DetailOperation;

public interface DetailOperationRepository extends JpaRepository<DetailOperation,Serializable> {

}
