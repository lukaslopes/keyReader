package com.lukaslopes.keyreader.usecases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReadKeyFile {

    private CreateCompanyDocumentKey createCompanyDocumentKey;

    private static String removeUTF8BOM(String line) {
        if (line.startsWith("\uFEFF")) {
            line = line.substring(1);
        }
        return line;
    }

    public void execute(final Path file) {
        // Para cada arquivo, gero um strem paralelo para ler cada linha
        try {
            log.info("Reading file {}", file.toString());
            Files.lines(file)
                    .parallel()
                    .forEach(line -> {
                        readSingleLine(file, line);
                    });
        } catch (final IOException ioException) {
            log.error("Error reading path lines", file);
        }
    }

    private void readSingleLine(final Path file, String line) {
        line = removeUTF8BOM(line);
        final String[] lineData = line.split(";");

        // verifica se está no formato <companyId>;<key>
        if (lineData.length == 2) {
            try {
                final Integer companyId = Integer.parseInt(lineData[0].trim());
                final String documentKey = lineData[1].trim();

                createCompanyDocumentKey.execute(companyId, documentKey);

            } catch (final NumberFormatException numberFormatException) {
                // valida que o código é um numero
                log.error("Invalid companyId on line {}, file: {}", line, file.toString());
            }
        } else {
            log.error("Invalid line format on line {}, file: {}", line, file.toString());
        }
    }
}
