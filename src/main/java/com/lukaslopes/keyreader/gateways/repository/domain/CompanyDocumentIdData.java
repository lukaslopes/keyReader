package com.lukaslopes.keyreader.gateways.repository.domain;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CompanyDocumentIdData {

    private Integer companyId;
    private String documentKey;
}
