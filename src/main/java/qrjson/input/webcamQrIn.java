package qrjson.input;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


import javax.swing.JFrame;
import javax.swing.JTextArea;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import org.json.simple.parser.ParseException;

public class webcamQrIn {
	public String chache = "";
	private String old = "";
    public void run(display d) {
        stringToJson s = new stringToJson();
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (d.webcam.isOpen()) {

				if ((image = d.webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (result != null) {
				if(!result.getText().equals(old)){
					old =  result.getText();
					if(chache!=""){
						chache += "-";
					}
					chache += result.getText();
				d.textarea.setText(chache);
			}
			}
        }while(true);
    }
}
