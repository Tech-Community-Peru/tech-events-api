package com.techcommunityperu.techcommunityperu.service;

import com.google.zxing.WriterException;
import java.io.IOException;

public interface QRCodeService {
    byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException;
    // Nuevo método para guardar el QR en un archivo
    void saveQRCodeToFile(String content, int width, int height, String filePath) throws WriterException, IOException;
}
