package com.lukaslopes.keyreader.usecases;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.providers.CompanyDocumentDatabaseProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CreateCompanyDocumentKey {

    private CompanyDocumentDatabaseProvider companyDocumentDatabaseProvider;

    public CompanyDocument execute(final Integer companyId, final String documentKey) {
        log.info("Creating new company document");

        // Os documentos a principio não são válidos, serão validados pela api
        // Para não sobrescrever os dados já existentes, o provider verificará se já existe o documento
        return companyDocumentDatabaseProvider
                .createIfNotExists(CompanyDocument
                        .builder()
                        .companyId(companyId)
                        .documentKey(documentKey)
                        .valid(Boolean.FALSE)
                        .build());
    }
}
