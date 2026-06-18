package com.grammerpal.grammerpal.repository;

import com.grammerpal.grammerpal.entity.CorrectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrectionHistoryRepository
        extends JpaRepository<CorrectionHistory, Long> {
}