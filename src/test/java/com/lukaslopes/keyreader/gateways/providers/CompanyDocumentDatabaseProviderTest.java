package com.lukaslopes.keyreader.gateways.providers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.gateways.repository.CompanyDocumentRepository;
import com.lukaslopes.keyreader.gateways.repository.converters.CompanyDocumentDataConverter;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentData;
import com.lukaslopes.keyreader.gateways.repository.domain.CompanyDocumentIdData;
import com.lukaslopes.keyreader.mocks.CompanyDocumentDataMock;
import com.lukaslopes.keyreader.mocks.CompanyDocumentMock;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CompanyDocumentDatabaseProviderTest {

    @Autowired
    private CompanyDocumentDatabaseProvider companyDocumentDatabaseProvider;

    @MockBean
    private CompanyDocumentRepository repository;

    @Test
    public void shouldCreateNewCompanyDocument() {
        final CompanyDocument newCompanyDocument = CompanyDocumentMock.newCompanyDocument();
        final CompanyDocumentIdData dataId = CompanyDocumentIdData.builder()
                .companyId(newCompanyDocument.getCompanyId()).documentKey(newCompanyDocument.getDocumentKey()).build();
        final CompanyDocumentData data = CompanyDocumentDataConverter.toData(CompanyDocumentMock.defaultValues());

        when(repository.findById(dataId)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(data);

        final CompanyDocument result = companyDocumentDatabaseProvider.createIfNotExists(newCompanyDocument);

        verify(repository, times(1)).save(any());

        assertEquals(data.getCompanyId(), result.getCompanyId());
        assertEquals(data.getCreationDate(), result.getCreationDate());
        assertEquals(data.getValid(), result.getValid());
        assertNotNull(result.getCreationDate());
    }

    @Test
    public void shouldNotSaveCompanyDocumentAlreadyExists() {
        final CompanyDocument newCompanyDocument = CompanyDocumentMock.newCompanyDocument();
        final CompanyDocumentIdData dataId = CompanyDocumentIdData.builder()
                .companyId(newCompanyDocument.getCompanyId()).documentKey(newCompanyDocument.getDocumentKey()).build();
        final CompanyDocumentData data = CompanyDocumentDataConverter.toData(CompanyDocumentMock.defaultValues());

        when(repository.findById(dataId)).thenReturn(Optional.of(data));

        final CompanyDocument result = companyDocumentDatabaseProvider.createIfNotExists(newCompanyDocument);

        verify(repository, never()).save(any());

        assertEquals(data.getCompanyId(), result.getCompanyId());
        assertEquals(data.getCreationDate(), result.getCreationDate());
        assertEquals(data.getValid(), result.getValid());
        assertNotNull(result.getCreationDate());
    }

    @Test
    public void shouldFindByCompanyAndDocument() {
        final CompanyDocumentData data = CompanyDocumentDataMock.defaultValues();

        when(repository.findById(any())).thenReturn(Optional.of(data));

        final Optional<CompanyDocument> result = companyDocumentDatabaseProvider
                .findByCompanyAndDocumentKey("any", 1);

        assertEquals(data.getCompanyId(), result.get().getCompanyId());
        assertEquals(data.getDocumentKey(), result.get().getDocumentKey());
        assertEquals(data.getCreationDate(), result.get().getCreationDate());
        assertEquals(data.getValid(), result.get().getValid());
    }

    @Test
    public void shouldNotFindByCompanyAndDocument() {

        when(repository.findById(any())).thenReturn(Optional.empty());

        final Optional<CompanyDocument> result = companyDocumentDatabaseProvider
                .findByCompanyAndDocumentKey("any", 1);

        assertFalse(result.isPresent());
    }

    @Test
    public void shouldFindAll() {
        final List<CompanyDocumentData> data = CompanyDocumentDataMock.list();

        when(repository.findAll()).thenReturn(data);

        final List<CompanyDocument> result = companyDocumentDatabaseProvider
                .findAll();

        assertEquals(data.get(0).getCompanyId(), result.get(0).getCompanyId());
        assertEquals(data.get(0).getDocumentKey(), result.get(0).getDocumentKey());
        assertEquals(data.get(1).getCompanyId(), result.get(1).getCompanyId());
        assertEquals(data.get(1).getDocumentKey(), result.get(1).getDocumentKey());
    }

}
