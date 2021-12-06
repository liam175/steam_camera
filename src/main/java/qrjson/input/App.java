package qrjson.input;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

/**
 * Hello world!
 *
 */
public class App 
{
    display d = new display();
    webcamQrIn w = new webcamQrIn(); 
    public static void main( String[] args )
    {
        App a = new App();
        a.loop();
    }
    void loop(){
        d.init();
        w.run(d);
    }
}
