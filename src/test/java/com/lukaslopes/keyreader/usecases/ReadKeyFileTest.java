package com.lukaslopes.keyreader.usecases;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReadKeyFileTest {

    @Autowired
    private ReadKeyFile readKeyFile;

    @MockBean
    private CreateCompanyDocumentKey createCompanyDocumentKey;

    @Test
    public void shouldRead10LineFile() throws URISyntaxException, IOException {
        final Path path = Paths.get(ClassLoader.getSystemResource("keys/file_keys-test.txt").toURI());

        when(createCompanyDocumentKey.execute(anyInt(), anyString()))
                .thenReturn(null);

        readKeyFile.execute(path);

        Files.lines(path).forEach(
                line -> {
                    if (StringUtils.isNotBlank(line)) {
                        verify(createCompanyDocumentKey, times(1))
                                .execute(anyInt(), eq(line.split(";")[1]));
                    }
                }
        );

        verify(createCompanyDocumentKey, times(10)).execute(anyInt(), anyString());
    }

    @Test
    public void shouldReadFileWith3BadLinesAnd8GoodLines() throws URISyntaxException, IOException {
        final Path path = Paths
                .get(ClassLoader.getSystemResource("keys-bad-files/file_keys-test-bad-lines.txt").toURI());

        when(createCompanyDocumentKey.execute(anyInt(), anyString()))
                .thenReturn(null);

        readKeyFile.execute(path);

        verify(createCompanyDocumentKey, times(8)).execute(anyInt(), anyString());
    }

}
