package com.lukaslopes.keyreader.entrypoints.controller.dto.request;

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
@ApiModel(value = "Validate documents request", description = "Validate documents request")
public class ValidateDocumentRequestBodyDTO {

    @ApiModelProperty(value = "Lista de documetos a validar")
    private List<String> documents;
}
