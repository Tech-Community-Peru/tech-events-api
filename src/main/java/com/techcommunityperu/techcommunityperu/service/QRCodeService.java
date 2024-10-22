package com.techcommunityperu.techcommunityperu.service;

import com.google.zxing.WriterException;
import java.io.IOException;

public interface QRCodeService {
    byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException;
}