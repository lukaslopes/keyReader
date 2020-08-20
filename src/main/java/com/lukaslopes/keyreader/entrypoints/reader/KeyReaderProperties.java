package com.lukaslopes.keyreader.entrypoints.reader;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class KeyReaderProperties {

    @Value("${reader.location}")
    private String keysFilesLocation;

    @Value("${reader.default}")
    private String defaultKeyFilesLocation;

}
