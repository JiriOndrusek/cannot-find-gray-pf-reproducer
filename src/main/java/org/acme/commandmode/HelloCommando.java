package org.acme.commandmode;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.io.FileOutputStream;
import java.util.EnumMap;

@QuarkusMain
public class HelloCommando implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        String fileName = "barcode.jpg";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            final MultiFormatWriter writer = new MultiFormatWriter();
            // create code image
            final BitMatrix matrix = writer.encode(
                    "test",
                    BarcodeFormat.AZTEC,
                    200,
                    200,
                    new EnumMap<>(EncodeHintType.class));

            // write image back to stream
            MatrixToImageWriter.writeToStream(matrix, "jpg", fos);
        }
        System.out.println("done");
        return 0;
    }
}
