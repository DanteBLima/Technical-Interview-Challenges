package com.challenge.service;


import com.challenge.domain.user.User;
import com.challenge.dtos.NotiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotifyService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();

        NotiDto notificationRequest = new NotiDto(email, message);

//        String url = "https://util.devi.tools/api/v1/notify";
//
//        ResponseEntity<String> notiResponse = restTemplate.postForEntity(url, notificationRequest, String.class);
//
//        if (notiResponse.getStatusCode() != HttpStatus.OK){
//            System.out.println("Erro ao enviar notificacao");
//            throw new Exception("Servico de notificacao fora do ar");
//
//        }

        System.out.println("Notificacao enviada");
    }
}
