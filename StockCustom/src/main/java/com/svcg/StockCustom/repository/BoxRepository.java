package com.svcg.StockCustom.repository;

import com.svcg.StockCustom.entity.Box;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository("boxRepository")
public interface BoxRepository extends JpaRepository<Box, Serializable> {
    Box findByDateOpen(Date dateOpen);

    Box findByDateClose(Date dateClose);

    Box findByBoxId(Long boxId);

    List<Box> findByDateCloseIsNullOrOpenIsTrue();

    Page<Box> findByDateCloseIsNull(Pageable pageable);
}
