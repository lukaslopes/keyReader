package com.lukaslopes.keyreader.entrypoints.controller;

import com.lukaslopes.keyreader.entrypoints.controller.converters.CompanyDocumentControllerConverter;
import com.lukaslopes.keyreader.entrypoints.controller.dto.request.ValidateDocumentRequestBodyDTO;
import com.lukaslopes.keyreader.entrypoints.controller.dto.response.CompanyDocumentResponseDTO;
import com.lukaslopes.keyreader.entrypoints.controller.dto.response.ValidateDocumentResponseDTO;
import com.lukaslopes.keyreader.usecases.GetCompanyDocument;
import com.lukaslopes.keyreader.usecases.ValidateCompanyDocument;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
public class CompanyDocumentController {

    private ValidateCompanyDocument validateCompanyDocument;

    private GetCompanyDocument getCompanyDocument;

    @ApiOperation(value = "Get Active MiniApps", notes = "Get all active MiniApps for a flavor", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Documentos validados"),
            @ApiResponse(code = 500, message = "Erro no servidor")})
    @PostMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ValidateDocumentResponseDTO> validateDocuments(@RequestParam(required = true) Integer company,
            @ApiParam(name = "Documentos", value = "Lista de documentos a validar", required = true) @RequestBody(required = false) ValidateDocumentRequestBodyDTO requestBody) {

        log.info("Validating documents");

        return ResponseEntity.ok(CompanyDocumentControllerConverter.validateToDTO(
                validateCompanyDocument.execute(company, requestBody.getDocuments())));
    }

    @ApiOperation(value = "Get Active MiniApps", notes = "Get all active MiniApps for a flavor", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Documentos validados"),
            @ApiResponse(code = 500, message = "Erro no servidor")})
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CompanyDocumentResponseDTO>> getAll() {

        log.info("Validating documents");

        return ResponseEntity.ok(CompanyDocumentControllerConverter.listToDTO(
                getCompanyDocument.getAll()));
    }


}
