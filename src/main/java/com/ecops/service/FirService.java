package com.ecops.service;

import com.ecops.beans.AllFirResponse;
import com.ecops.beans.FirRequest;
import com.ecops.beans.FirResponse;
import com.ecops.beans.UpdateCaseRequest;
import com.ecops.domain.Fir;

import java.util.List;

public interface FirService {

    void createFir(FirRequest request);

    List<AllFirResponse> getAllFir(String userId);

    FirResponse getFir(String userId, int firId);

    List<AllFirResponse> getAllCases(String loginId);

    FirResponse getCaseDetails(String loginId, int caseId);

    void updateCaseStatus(String loginId, int caseId, UpdateCaseRequest updateCaseRequest);
}
