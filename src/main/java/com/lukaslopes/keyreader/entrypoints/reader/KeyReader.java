package com.lukaslopes.keyreader.entrypoints.reader;

import com.lukaslopes.keyreader.usecases.ReadKeyFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Essa classe fará a leitura dos arquivos.
 * Primeiro ele busca os arquivos no diretorio indicado no properties
 * Caso não encontre, busca na pasta do resources
 */

@Component
@Slf4j
public class KeyReader {
    @Autowired
    private ReadKeyFile readKeyFile;

    @Autowired
    private KeyReaderProperties keyReaderProperties;

    public void readFiles() {

        try {
            readFilesInDirectory();
        } catch (final IOException ioException) {
            log.error("No files or directory found.", ioException);
            readFilesInResources();
        } catch (final Exception e) {
            log.error("Error finding files in classpath resources", e);
        }
    }

    private void readFilesInDirectory() throws IOException {
        log.info("Looking for files in directory");
        final String directory = keyReaderProperties.getKeysFilesLocation();

        if (StringUtils.isEmpty(directory)) {
            throw new FileNotFoundException();
        }

        // Stream parallel para processar multiplos arquivos
        Files.list(Paths.get(directory))
                .parallel()
                .forEach(file -> readKeyFile.execute(file));
    }

    private void readFilesInResources() {
        try {
            log.info("Looking for files in classpath resource");
            Files.list(
                    Paths.get(ClassLoader.getSystemResource(keyReaderProperties.getDefaultKeyFilesLocation()).toURI()))
                    .parallel()
                    .forEach(file -> readKeyFile.execute(file));
        } catch (final IOException ioException) {
            log.error("No files or directory found in classpath resources", ioException);
        } catch (final Exception e) {
            log.error("Error finding files in classpath resources", e);
        }
    }


}
