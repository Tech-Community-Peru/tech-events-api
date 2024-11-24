package com.techcommunityperu.techcommunityperu.service.impl;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.techcommunityperu.techcommunityperu.service.QRCodeService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    @Override
    public void saveQRCodeToFile(String content, int width, int height, String filePath) throws WriterException, IOException {
        // Configuración para la generación del QR
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);

        // Convertir BitMatrix a BufferedImage
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);

        // Crear la carpeta si no existe
        File outputFile = new File(filePath);
        outputFile.getParentFile().mkdirs(); // Crear los directorios si no existen

        // Guardar el QR como archivo PNG
        ImageIO.write(bufferedImage, "PNG", outputFile);
    }

    // Método para convertir BitMatrix a BufferedImage
    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        // Pintar la imagen en blanco y negro dependiendo del valor de cada bit
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0x000000 : 0xFFFFFF); // negro o blanco
            }
        }
        return image;
    }
}

