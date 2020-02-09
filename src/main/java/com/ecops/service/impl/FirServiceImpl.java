package com.ecops.service.impl;

import com.ecops.beans.*;
import com.ecops.domain.Fir;
import com.ecops.exception.BadRequestException;
import com.ecops.parameter.ApplicationParameter;
import com.ecops.repository.FirRepository;
import com.ecops.service.FirService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FirServiceImpl implements FirService {

    @Autowired
    FirRepository firRepository;

    @Autowired
    ApplicationParameter applicationParameter;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void createFir(FirRequest request) {
        Fir fir = Fir.builder()
                .address(request.getAddress())
                .crimeCommit(request.getCrimeCommit())
                .description(request.getDescription())
                .district(request.getDistrict())
                .filedDate(Instant.now())
                .incidentDate(request.getIncidentDate())
                .mobileNumber(request.getMobileNumber())
                .place(request.getPlace())
                .pincode(request.getPincode())
                .state(request.getState())
                .userId(request.getUserId())
                .build();
        System.out.println("FIR Details saved");
        firRepository.saveAndFlush(fir);
        assignPoliceStation(fir.getFirId());
    }

    @Override
    public List<AllFirResponse> getAllFir(String userId) {
        List<Fir> firList = firRepository.findByUserId(userId);
        List<AllFirResponse> firResponse = new ArrayList<>();
        firList.forEach(fir -> {
            AllFirResponse.AllFirResponseBuilder response = AllFirResponse.builder();
            response.firId(fir.getFirId())
                    .crimeType(fir.getCrimeCommit())
                    .filedDate(fir.getFiledDate())
                    .incidentDate(fir.getIncidentDate());
            firResponse.add(response.build());
        });
        return firResponse;
    }

    @Override
    public FirResponse getFir(String userId, int firId) {
        Fir fir = firRepository.findByUserIdAndFirId(userId,firId);
        FirResponse firResponse = FirResponse.builder().firId(firId)
                .crimeCommit(fir.getCrimeCommit())
                .description(fir.getDescription())
                .status(fir .getStatus())
                .comments(fir.getComments())
                .incidentDate(fir.getIncidentDate())
                .filedDate(fir.getFiledDate())
                .address(fir.getAddress())
                .district(fir.getDistrict())
                .place(fir.getPlace())
                .state(fir.getState())
                .pincode(fir.getPincode())
                .mobileNumber(fir.getMobileNumber())
                .build();
        return firResponse;
    }

    @Async
    private void assignPoliceStation(int firId) {
        try {
            Fir fir = firRepository.findById(firId).get();
            StationResponse stationResponse;
            String url = "http://localhost:9091/admin/api/v1/get_police_station?pincode=" + fir.getPincode();
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity requestEntity = new HttpEntity(null, headers);
            ResponseEntity<StationResponse> re = restTemplate.exchange(url, HttpMethod.GET, requestEntity, StationResponse.class);
            stationResponse = re.getBody();
            fir.setPoliceStationId(stationResponse.getStationId());
            fir.setStationName(stationResponse.getStationName());
            fir.setStationContact(stationResponse.getStationContact());
            System.out.println("Station details saved");
            assignPoliceOfficer(fir);
        } catch (Exception e) {
            throw new BadRequestException("Error while assigning police station");
        }
    }

    @Async
    private void assignPoliceOfficer(Fir fir) {
        try {
            OfficerResponse officerResponse;
            String url = "http://localhost:9091/admin/api/v1/get_police_officer?id=" + fir.getPoliceStationId() + "&crimeType=" + fir.getCrimeCommit();
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity requestEntity = new HttpEntity(null, headers);
            ResponseEntity<OfficerResponse> re = restTemplate.exchange(url, HttpMethod.GET, requestEntity, OfficerResponse.class);
            officerResponse = re.getBody();
            fir.setPoliceOfficerId(officerResponse.getId());
            fir.setPoliceOfficerName(officerResponse.getName());
            System.out.println("Officer details saved");
            firRepository.saveAndFlush(fir);
        } catch (Exception e) {
            throw new BadRequestException("Error while assigning police officer");
        }

    }
}
