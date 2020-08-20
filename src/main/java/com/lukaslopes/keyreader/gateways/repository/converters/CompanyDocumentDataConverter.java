package com.lukaslopes.keyreader.gateways.repository.converters;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentData;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentIdData;

public class CompanyDocumentDataConverter {

    public static CompanyDocumentIdData toIdData(final CompanyDocument companyDocument) {
        return CompanyDocumentIdData.builder()
                .companyId(companyDocument.getCompanyId())
                .documentKey(companyDocument.getDocumentKey())
                .build();
    }

    public static CompanyDocument toEntity(final CompanyDocumentData companyDocumentData) {
        return CompanyDocument.builder()
                .companyId(companyDocumentData.getCompanyId())
                .documentKey(companyDocumentData.getDocumentKey())
                .valid(companyDocumentData.getValid())
                .creationDate(companyDocumentData.getCreationDate())
                .updateDate(companyDocumentData.getUpdateDate())
                .build();
    }

    public static CompanyDocumentData toData(final CompanyDocument companyDocumentData) {
        return CompanyDocumentData.builder()
                .companyId(companyDocumentData.getCompanyId())
                .documentKey(companyDocumentData.getDocumentKey())
                .valid(companyDocumentData.getValid())
                .creationDate(companyDocumentData.getCreationDate())
                .updateDate(companyDocumentData.getUpdateDate())
                .build();
    }
}
