package com.schumaker.pix.view;

import com.schumaker.pix.model.PixStatus;
import com.schumaker.pix.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final PixService pixService;

    @PostMapping
    public PixDTO doTransfer(@RequestBody PixDTO pixDTO) {

        pixDTO.setIdentifier(UUID.randomUUID().toString());
        pixDTO.setDueDate(LocalDateTime.now());
        pixDTO.setStatus(PixStatus.PROCESSING);

        return pixService.savePix(pixDTO);
    }
}
