package com.ecops.service;

import com.ecops.beans.AllFirResponse;
import com.ecops.beans.FirRequest;
import com.ecops.domain.Fir;

import java.util.List;

public interface FirService {

    void createFir(FirRequest request);

    List<AllFirResponse> getAllFir(String userId);
}
