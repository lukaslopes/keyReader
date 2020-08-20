package com.lukaslopes.keyreader.gateways.providers;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.repository.CompanyDocumentRepository;
import com.lukaslopes.keyreader.gateways.repository.converters.CompanyDocumentDataConverter;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentIdData;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyDocumentDatabaseProvider {

    private CompanyDocumentRepository companyDocumentRepository;

    public CompanyDocument createIfNotExists(final CompanyDocument newCompanyDocument) {

        return companyDocumentRepository.findById(CompanyDocumentDataConverter.toIdData(newCompanyDocument))
                .map(CompanyDocumentDataConverter::toEntity)
                .orElseGet(() -> create(newCompanyDocument));
    }

    public CompanyDocument create(final CompanyDocument newCompanyDocument) {
        final Instant now = Instant.now();
        return CompanyDocumentDataConverter.toEntity(
                companyDocumentRepository.save(CompanyDocumentDataConverter
                        .toData(newCompanyDocument.toBuilder()
                                .creationDate(now)
                                .updateDate(now)
                                .build())));
    }

    public CompanyDocument update(final CompanyDocument companyDocument) {
        final Instant now = Instant.now();
        return CompanyDocumentDataConverter.toEntity(
                companyDocumentRepository.save(CompanyDocumentDataConverter
                        .toData(companyDocument.toBuilder()
                                .updateDate(now)
                                .build())));
    }

    public Optional<CompanyDocument> findByCompanyAndDocumentKey(final String documentKey, final Integer companyId){
        return companyDocumentRepository.findById(CompanyDocumentIdData.builder()
                        .companyId(companyId)
                        .documentKey(documentKey)
                        .build())
                .map(CompanyDocumentDataConverter::toEntity);
    }

    public List<CompanyDocument> findAll(){
        return StreamSupport.stream(companyDocumentRepository.findAll().spliterator(), false)
                .map(CompanyDocumentDataConverter::toEntity)
                .collect(Collectors.toList());
    }
}
