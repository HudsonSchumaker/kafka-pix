package com.schumaker.pix.view;

import com.schumaker.pix.model.PixStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixDTO {

    private String identifier;
    private String originKey;
    private String destinyKey;
    private Double value;
    private LocalDateTime dueDate;
    private PixStatus status;
}
