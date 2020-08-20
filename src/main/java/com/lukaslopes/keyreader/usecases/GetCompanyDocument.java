package com.lukaslopes.keyreader.usecases;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.providers.CompanyDocumentDatabaseProvider;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class GetCompanyDocument {

    private CompanyDocumentDatabaseProvider provider;

    public Optional<CompanyDocument> getByCompanyAndKey(final Integer companyId, final String documentKey) {
        return provider.findByCompanyAndDocumentKey(documentKey, companyId);
    }

    public List<CompanyDocument> getAll(){
        return provider.findAll();
    }
}
