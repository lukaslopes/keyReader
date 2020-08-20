package com.lukaslopes.keyreader.gateways.repository.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "company_document")
@IdClass(CompanyDocumentIdData.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDocumentData {

    @Id
    private Integer companyId;

    @Id
    private String documentKey;

    @Column
    private Boolean valid;

    @Column
    private Instant creationDate;

    @Column
    private Instant updateDate;
}
