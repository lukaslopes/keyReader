package com.lukaslopes.keyreader.entrypoints.reader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lukaslopes.keyreader.usecases.ReadKeyFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class KeyReaderTest {

    @Autowired
    private KeyReader keyReader;

    @MockBean
    private ReadKeyFile readKeyFile;

    @SpyBean
    private KeyReaderProperties keyReaderProperties;

    @Test
    public void justTesting() throws URISyntaxException {

        try {
            Files.list(
                    Paths.get(ClassLoader.getSystemResource(keyReaderProperties.getDefaultKeyFilesLocation()).toURI()))
                    .forEach(System.out::println);

            Files.list(Paths.get(keyReaderProperties.getKeysFilesLocation()))
                    .parallel()
                    .forEach(System.out::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findFilesInDirectory() throws IOException, URISyntaxException {
        doNothing().when(readKeyFile).execute(any());
        when(keyReaderProperties.getKeysFilesLocation())
                .thenReturn(ClassLoader.getSystemResource("keys-directory").getPath());

        keyReader.readFiles();

        Files.list(Paths.get(keyReaderProperties.getKeysFilesLocation()))
                .forEach(path -> verify(readKeyFile, times(1)).execute(path));

        verify(readKeyFile, times(2)).execute(any());
    }

    @Test
    public void findFilesInResourcesBecauseDirectoryNotFound() throws IOException, URISyntaxException {
        doNothing().when(readKeyFile).execute(any());
        when(keyReaderProperties.getKeysFilesLocation()).thenReturn("/file/dont/exists");

        keyReader.readFiles();

        Files.list(Paths.get(ClassLoader.getSystemResource(keyReaderProperties.getDefaultKeyFilesLocation()).toURI()))
                .forEach(path -> verify(readKeyFile, times(1)).execute(path));

        verify(readKeyFile, times(2)).execute(any());
    }

    @Test
    public void findFilesInResourcesBecausePropertieIsEmpty() throws IOException, URISyntaxException {
        doNothing().when(readKeyFile).execute(any());
        when(keyReaderProperties.getKeysFilesLocation()).thenReturn("");

        keyReader.readFiles();

        Files.list(Paths.get(ClassLoader.getSystemResource(keyReaderProperties.getDefaultKeyFilesLocation()).toURI()))
                .forEach(path -> verify(readKeyFile, times(1)).execute(path));

        verify(readKeyFile, times(2)).execute(any());
    }

    @Test
    public void filesNotFoundInResources() throws FileNotFoundException {
        doNothing().when(readKeyFile).execute(any());
        when(keyReaderProperties.getDefaultKeyFilesLocation()).thenReturn("ThisDirectoryDontExists");
        when(keyReaderProperties.getKeysFilesLocation()).thenReturn("ThisNeither");

        keyReader.readFiles();

        verify(readKeyFile, never()).execute(any());
    }
}
