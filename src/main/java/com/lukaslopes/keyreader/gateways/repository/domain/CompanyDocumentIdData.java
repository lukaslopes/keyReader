package com.lukaslopes.keyreader.gateways.repository.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDocumentIdData implements Serializable {

    private Integer companyId;
    private String documentKey;
}
