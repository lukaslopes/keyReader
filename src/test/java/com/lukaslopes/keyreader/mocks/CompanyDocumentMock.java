package com.lukaslopes.keyreader.mocks;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import java.time.Instant;

public class CompanyDocumentMock {

    public static CompanyDocument newCompanyDocument() {
        return CompanyDocument.builder()
                .companyId(90)
                .documentKey("35170501512104006006550010001595511100000016")
                .valid(Boolean.FALSE)
                .build();
    }

    public static CompanyDocument defaultValues() {
        final Instant now = Instant.now();

        return newCompanyDocument().toBuilder()
                .creationDate(now)
                .updateDate(now)
                .build();
    }
}
