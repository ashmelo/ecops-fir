package com.ecops.repository;

import com.ecops.domain.Fir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirRepository extends JpaRepository<Fir,Integer> {

    List<Fir> findByUserId(String userId);

    Fir findByUserIdAndFirId(String userId, int firId);
}
