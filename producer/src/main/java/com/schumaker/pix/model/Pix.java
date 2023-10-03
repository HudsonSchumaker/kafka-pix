package com.schumaker.pix.model;

import com.schumaker.pix.view.PixDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String originKey;
    private String destinyKey;
    private Double value;
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setDestinyKey(pixDTO.getDestinyKey());
        pix.setStatus(pixDTO.getStatus());
        pix.setValue(pixDTO.getValue());
        pix.setDueDate(pixDTO.getDueDate());
        pix.setOriginKey(pixDTO.getOriginKey());
        return pix;
    }
}
