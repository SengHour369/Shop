package com.Shop.Shop.Exception;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestError {
    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;
    private String path;
    private short  status;
}
