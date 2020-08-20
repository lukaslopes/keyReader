package com.lukaslopes.keyreader.gateways.repository;

import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentData;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentIdData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDocumentRepository extends CrudRepository<CompanyDocumentData, CompanyDocumentIdData> {

}
