package com.lukaslopes.keyreader.entrypoints.controller.converters;

import com.lukaslopes.keyreader.entities.CompanyDocument;
import com.lukaslopes.keyreader.entrypoints.controller.dto.response.CompanyDocumentResponseDTO;
import com.lukaslopes.keyreader.entrypoints.controller.dto.response.ValidateDocumentResponseDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CompanyDocumentControllerConverter {

    public static ValidateDocumentResponseDTO validateToDTO(final Map<Boolean, List<String>> result) {
        return ValidateDocumentResponseDTO.builder()
                .success(result.get(Boolean.TRUE))
                .fails(result.get(Boolean.FALSE))
                .build();
    }

    public static List<CompanyDocumentResponseDTO> listToDTO(final List<CompanyDocument> documents) {
        return documents.stream()
                .map(doc -> CompanyDocumentResponseDTO.builder()
                        .chaveDocumento(doc.getDocumentKey())
                        .codigoEmpresa(doc.getCompanyId())
                        .validado(doc.getValid()?"Sim":"Não")
                        .build())
                .collect(Collectors.toList());
    }
}
