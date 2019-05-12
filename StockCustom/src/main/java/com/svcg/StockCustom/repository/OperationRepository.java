package com.svcg.StockCustom.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svcg.StockCustom.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation,Serializable> {

	
	
}
