package com.lukaslopes.keyreader.entities;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class CompanyDocument {

    private Integer companyId;
    private String documentKey;
    private Boolean valid;
    private Instant creationDate;
    private Instant updateDate;
}
