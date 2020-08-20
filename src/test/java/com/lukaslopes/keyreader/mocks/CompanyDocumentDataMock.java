package com.lukaslopes.keyreader.mocks;

import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentData;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class CompanyDocumentDataMock {

    public static List<CompanyDocumentData> list() {
        final Instant now = Instant.now();

        return Arrays.asList(CompanyDocumentData.builder()
                        .companyId(90)
                        .documentKey("35170501512104006006550010001595511100000016")
                        .valid(Boolean.FALSE)
                        .creationDate(now)
                        .updateDate(now)
                        .build(),
                CompanyDocumentData.builder()
                        .companyId(60)
                        .documentKey("35170501512104006006550010001595511100000017")
                        .valid(Boolean.FALSE)
                        .creationDate(now)
                        .updateDate(now)
                        .build());
    }

    public static CompanyDocumentData defaultValues() {
        return list().get(0);
    }
}
