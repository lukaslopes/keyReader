package com.lukaslopes.keyreader.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.providers.CompanyDocumentDatabaseProvider;
import com.lukaslopes.keyreader.gateways.providers.CompanyDocumentDatabaseProviderTest;
import com.lukaslopes.keyreader.mocks.CompanyDocumentMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CreateCompanyDocumentKeyTests {

    @Autowired
    private CreateCompanyDocumentKey createCompanyDocumentKey;

    @MockBean
    private CompanyDocumentDatabaseProvider companyDocumentDatabaseProvider;

    @Test
    public void shouldSaveNewCompanyDocument() {
        final CompanyDocument companyDocument = CompanyDocumentMock.defaultValues();
        final CompanyDocument newCompanyDocument = CompanyDocumentMock.newCompanyDocument();

        when(companyDocumentDatabaseProvider.createIfNotExists(newCompanyDocument)).thenReturn(companyDocument);

        final CompanyDocument result = createCompanyDocumentKey
                .execute(newCompanyDocument.getCompanyId(), newCompanyDocument.getDocumentKey());

        assertEquals(result, companyDocument);
    }

}
