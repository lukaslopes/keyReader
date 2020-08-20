package com.lukaslopes.keyreader.gateways.providers;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.mocks.CompanyDocumentMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompanyDocumentDatabaseProvider {

    @Autowired
    private CompanyDocumentDatabaseProvider companyDocumentDatabaseProvider;

    @Test
    public void shouldCreateNewCompanyDocument(){
        final CompanyDocument newCompanyDocument = CompanyDocumentMock.newCompanyDocument();
    }

}
