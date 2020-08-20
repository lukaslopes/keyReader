package com.lukaslopes.keyreader.entrypoints.controller.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Documentos da empresa", description = "Documentos da empresa")
public class CompanyDocumentResponseDTO {

    @ApiModelProperty(value = "Código da empresa")
    private Integer codigoEmpresa;

    @ApiModelProperty(value = "Chave do documento")
    private String chaveDocumento;

    @ApiModelProperty(value = "Documento validado")
    private String validado;

}
