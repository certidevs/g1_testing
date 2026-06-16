package com.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileServiceTest {

    FileService fileService = new FileService();

    @Test
    void storeConNullDevuelveNull() {
        assertNull(fileService.store(null));
    }

    @Test
    void storeConArchivoVacioDevuelveNull() {
        MockMultipartFile vacio = new MockMultipartFile("file", "", "text/plain", new byte[0]);
        assertNull(fileService.store(vacio));
    }

    @Test
    void storeGuardaElArchivoYDevuelveLaRuta() throws Exception {
        MockMultipartFile archivo = new MockMultipartFile("file", "foto.png", "image/png", "contenido".getBytes());
        String ruta = fileService.store(archivo);
        assertNotNull(ruta);
        assertTrue(ruta.startsWith("/uploads/"));
        Path guardado = Path.of(FileService.UPLOAD_DIR, ruta.substring("/uploads/".length()));
        assertTrue(Files.exists(guardado));
        Files.deleteIfExists(guardado);
    }
}
