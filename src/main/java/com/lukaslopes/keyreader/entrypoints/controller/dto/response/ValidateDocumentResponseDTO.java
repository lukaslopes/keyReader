package com.lukaslopes.keyreader.entrypoints.controller.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Validate documents response", description = "Validate documents response")
public class ValidateDocumentResponseDTO {
    @ApiModelProperty(value = "Documentos validados com sucesso")
    private List<String> success;

    @ApiModelProperty(value = "Documentos não validados")
    private List<String> fails;
}
