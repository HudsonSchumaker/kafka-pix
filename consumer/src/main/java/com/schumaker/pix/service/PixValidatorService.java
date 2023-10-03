package com.schumaker.pix.service;

import com.schumaker.pix.model.*;
import com.schumaker.pix.view.PixDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class PixValidatorService {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private PixRepository pixRepository;

    @RetryableTopic(backoff = @Backoff(1000L), attempts = "4", include = Exception.class)
    @KafkaListener(topics = "pix-topic", groupId = "transfers")
    public void processPix(PixDTO pixDTO) {
        System.out.println("Pix received: " + pixDTO.getIdentifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());

        Key origem = keyRepository.findByKey(pixDTO.getOriginKey());
        Key destino = keyRepository.findByKey(pixDTO.getDestinyKey());

        if (origem == null || destino == null) {
            pix.setStatus(PixStatus.ERROR);
        } else {
            pix.setStatus(PixStatus.PROCESSED);
        }
        System.out.println("Pix status: " + pixDTO.getStatus().toString());
        pixRepository.save(pix);
    }
}
