package com.brandon.desafio_tecnico_nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfValidationService {

    private static final String CPF_VALIDATION_URL = "https://user-info.herokuapp.com/users/";

    @Autowired
    private RestTemplate restTemplate;

    public boolean isAbleToVote(String cpf) {
        try {
            ResponseEntity<UserInfoResponse> response = restTemplate.getForEntity(CPF_VALIDATION_URL + cpf, UserInfoResponse.class);
            return response.getBody() != null && "ABLE_TO_VOTE".equals(response.getBody().getStatus());
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    // Classe para representar a resposta da API
    public static class UserInfoResponse {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

