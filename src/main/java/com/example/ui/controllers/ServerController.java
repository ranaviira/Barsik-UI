package com.example.ui.controllers;

import com.example.ui.entity.Contract;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ServerController {

    private List<Contract> contractsList;

    private void getAllContractsFromServer() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Contract>> rateResponse =
                restTemplate.exchange("http://localhost:8080/server",
                        HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
        contractsList = rateResponse.getBody();

    }

    public List<Contract> getContractsList() {
        getAllContractsFromServer();
        return contractsList;
    }
}
