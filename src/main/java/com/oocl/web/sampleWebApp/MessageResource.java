package com.oocl.web.sampleWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageResource {

    @Autowired
    SingleEntityRepository singleEntityRepository;

    @GetMapping
    public ResponseEntity<MessageResponse> get(){
        SingleEntity entity = singleEntityRepository.findAll().get(0);
        return new ResponseEntity<MessageResponse>(
                new MessageResponse(entity.name), HttpStatus.OK);
    }
}