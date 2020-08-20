package com.lukaslopes.keyreader.usecases;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.providers.CompanyDocumentDatabaseProvider;
import com.lukaslopes.keyreader.gateways.repository.CompanyDocumentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ValidateCompanyDocument {

    private CompanyDocumentDatabaseProvider provider;
    private GetCompanyDocument getCompanyDocument;

    public Map<Boolean, List<String>> execute(final Integer companyId, final List<String> documentKeyList){
        List<String> success = new ArrayList<>();
        List<String> error = new ArrayList<>();
        Map<Boolean, List<String>> result = new HashMap<>();

        documentKeyList.forEach(key -> {
            final Optional<CompanyDocument> companyDocument = getCompanyDocument.getByCompanyAndKey(companyId, key);

            if(companyDocument.isPresent()){
                provider.update(companyDocument.get().toBuilder()
                        .valid(Boolean.TRUE)
                        .build());
                success.add(key);
            }
            else{
                error.add(key);
            }
        });

        result.put(Boolean.TRUE, success);
        result.put(Boolean.FALSE, error);
        return result;
    }

}
