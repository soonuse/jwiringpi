import java.io.*;

public class SH1106SPIDemo {
    public static void main(String[] args) {
        SH1106SPI oled = new SH1106SPI();
        if (oled.wiringPiSetup() < 0) {
            System.out.println("WiringPi setup error");
            return;
        }
        oled.begin();
        oled.pinMode(oled.RST_PIN, oled.OUTPUT);

        oled.clearFrameBuffer();
        oled.showImage(0, 0, new File("waveshare_logo.bmp"));
        oled.delay(2000);
        oled.clearFrameBuffer();
        oled.showString("Hello world!", 0, 36);
        oled.delay(2000);
        oled.clearFrameBuffer(true);
        oled.showMonoBitmap(0, 0, oled.WAVESHARE_LOGO, oled.OLED_WIDTH, oled.OLED_HEIGHT, false);
    }
}
