/**
  * @filename       : SSD1331SPI.java
  * @date           : July 4 2017
  * @author         : soonuse from Github
  * @description:
  *   This class is written in Java and tested with a Waveshare SSD1306 
  *   0.96inch OLED module working on Raspberry Pi 3 Model B. 
  ***********************************************************************
  * This is a class for SSD1331 0.95inch RGB OLED module working on Raspberry Pi.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

import jwiringpi.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class SSD1331SPI extends JWiringPiController {
    final public int OLED_WIDTH                      = 96;
    final public int OLED_HEIGHT                     = 64;
    final public int RST_PIN                         = 24;
    final public int DC_PIN                          = 27;
    final public int CHANNEL                         = 0;

    final public int DRAW_LINE                       = 0x21;
    final public int DRAW_RECTANGLE                  = 0x22;
    final public int COPY_WINDOW                     = 0x23;
    final public int DIM_WINDOW                      = 0x24;
    final public int CLEAR_WINDOW                    = 0x25;
    final public int FILL_WINDOW                     = 0x26;
    final public int DISABLE_FILL                    = 0x00;
    final public int ENABLE_FILL                     = 0x01;
    final public int CONTINUOUS_SCROLLING_SETUP      = 0x27;
    final public int DEACTIVE_SCROLLING              = 0x2E;
    final public int ACTIVE_SCROLLING                = 0x2F;

    final public int SET_COLUMN_ADDRESS              = 0x15;
    final public int SET_ROW_ADDRESS                 = 0x75;
    final public int SET_CONTRAST_A                  = 0x81;
    final public int SET_CONTRAST_B                  = 0x82;
    final public int SET_CONTRAST_C                  = 0x83;
    final public int MASTER_CURRENT_CONTROL          = 0x87;
    final public int SET_PRECHARGE_SPEED_A           = 0x8A;
    final public int SET_PRECHARGE_SPEED_B           = 0x8B;
    final public int SET_PRECHARGE_SPEED_C           = 0x8C;
    final public int SET_REMAP                       = 0xA0;
    final public int SET_DISPLAY_START_LINE          = 0xA1;
    final public int SET_DISPLAY_OFFSET              = 0xA2;
    final public int NORMAL_DISPLAY                  = 0xA4;
    final public int ENTIRE_DISPLAY_ON               = 0xA5;
    final public int ENTIRE_DISPLAY_OFF              = 0xA6;
    final public int INVERSE_DISPLAY                 = 0xA7;
    final public int SET_MULTIPLEX_RATIO             = 0xA8;
    final public int DIM_MODE_SETTING                = 0xAB;
    final public int SET_MASTER_CONFIGURE            = 0xAD;
    final public int DIM_MODE_DISPLAY_ON             = 0xAC;
    final public int DISPLAY_OFF                     = 0xAE;
    final public int NORMAL_BRIGHTNESS_DISPLAY_ON    = 0xAF;
    final public int POWER_SAVE_MODE                 = 0xB0;
    final public int PHASE_PERIOD_ADJUSTMENT         = 0xB1;
    final public int DISPLAY_CLOCK_DIV               = 0xB3;
    final public int SET_GRAy_SCALE_TABLE            = 0xB8;
    final public int ENABLE_LINEAR_GRAY_SCALE_TABLE  = 0xB9;
    final public int SET_PRECHARGE_VOLTAGE           = 0xBB;
    final public int SET_V_VOLTAGE                   = 0xBE;
 
    final public int BLACK = convertRGB888To565(0, 0, 0);
    final public int GRAY = convertRGB888To565(192, 192, 192); 
    final public int WHITE = convertRGB888To565(255, 255, 255);
    final public int RED = convertRGB888To565(255, 0, 0);
    final public int PINK = convertRGB888To565(255, 192, 203);
    final public int YELLOW = convertRGB888To565(255, 255, 0);
    final public int GOLDEN = convertRGB888To565(255, 215, 0);
    final public int BROWN = convertRGB888To565(128, 42, 42);
    final public int BLUE = convertRGB888To565(0, 0, 255);
    final public int CYAN = convertRGB888To565(0, 255,255);
    final public int GREEN = convertRGB888To565(0, 255, 0);
    final public int PURPLE = convertRGB888To565(160, 32, 240);

    public byte frameBuffer[] = new byte[OLED_WIDTH * OLED_HEIGHT * 2];

    private static int convertRGB888To565 (int red, int green, int blue) {
        return (((red & 0xFF) >> 3) << 11) | (((green & 0xFF) >> 2) << 5) | ((blue & 0xFF) >> 3);
    }

    private static int convertRGB888To565 (int colorRGB888) {
        int[] colorBuffer = new int[3];
        colorBuffer[0] = colorRGB888 & 0xFF;
        colorBuffer[1] = (colorRGB888 & 0xFF00) >> 8;
        colorBuffer[2] = (colorRGB888 & 0xFF0000) >> 16;
        return convertRGB888To565(colorBuffer[2], colorBuffer[1], colorBuffer[0]);
    }

    private void display() {
        int subBufferLength = 512; // buffer length cannot be so big
        int remain = frameBuffer.length;
        int index = 0;      // as a pointer to the pixel of the oled data
        byte[] subBuffer = new byte[subBufferLength];

        sendCommand(SET_COLUMN_ADDRESS);
        sendCommand(0);         //cloumn start address
        sendCommand(OLED_WIDTH - 1); //cloumn end address
        sendCommand(SET_ROW_ADDRESS);
        sendCommand(0);         //page atart address
        sendCommand(OLED_HEIGHT - 1); //page end address
        digitalWrite(DC_PIN, HIGH);
        while (remain > subBufferLength) {
            for (int i = 0; i < subBufferLength; i++) {
                subBuffer[i] = frameBuffer[index];
                index++;
            }
            wiringPiSPIDataRW(CHANNEL, subBuffer, subBufferLength);
            remain -= subBufferLength;
        }
        for (int i = 0; i < subBufferLength; i++) {
            subBuffer[i] = frameBuffer[index];
            index++;
        }
        wiringPiSPIDataRW(CHANNEL, subBuffer, remain);
    }

    public void showString(String string, int x, int y, Font font, int color) {
        BufferedImage image = new BufferedImage(OLED_WIDTH, OLED_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.drawString(string, x, y);
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), new int[image.getWidth() * image.getHeight()], 0, image.getWidth());
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (pixels[i * image.getWidth() + j] == -1) {
                    drawPixelToFrameBuffer(j, i, color);
                }
            }
        }
        display();
    }

    public void showString(String string, int x, int y, int fontSize, int color) {
        showString(string, x, y, new Font("consolas", Font.PLAIN, fontSize), color);
    }

    public void showString(String string, int x, int y) {
        showString(string, x, y, new Font("consolas", Font.PLAIN, 12), WHITE);
    }

    public void showMonoBitmap(int x, int y, short[] bmpBuffer,
        int width, int height, int color) {

        int byteWidth = (width + 7) / 8;
        int index = 0;
        for(int j = 0; j < OLED_HEIGHT; j++) {
            for(int i = 0; i < OLED_WIDTH; i ++) {
                if((bmpBuffer[j * byteWidth + i / 8] & 0xFF & (128 >> (i & 7))) != 0) {
                    drawPixelToFrameBuffer(x + i, y + j, color); }
            }
        }    
    }

    public void showMonoBitmap(int x, int y, int[] bmpBuffer,
        int width, int height, int color) {

        int byteWidth = (width + 7) / 8;
        int index = 0;
        for(int j = 0; j < OLED_HEIGHT; j++) {
            for(int i = 0; i < OLED_WIDTH; i ++) {
                if((bmpBuffer[j * byteWidth + i / 8] & 0xFF & (128 >> (i & 7))) != 0) {
                    drawPixelToFrameBuffer(x + i, y + j, color); }
            }
        }    
        display();
    }

    public void showImage(int x, int y, File file) {
        int color;
        if (! (file.exists() && file.isFile())) {
            return;
        }
        try {
            BufferedImage image = ImageIO.read(file);
            int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), new int[image.getWidth() * image.getHeight()], 0, image.getWidth());
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    color = convertRGB888To565(pixels[i * image.getWidth() + j]);
                    drawPixelToFrameBuffer(j, i, color);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        display();
    }

    public void drawPixelToFrameBuffer(int x, int y, int color) {
        if(x >= OLED_WIDTH || y >= OLED_HEIGHT) {
            return;
        }
        frameBuffer[x * 2 + y * OLED_WIDTH * 2] = (byte)(color >> 8);
        frameBuffer[x * 2 + y * OLED_WIDTH * 2 + 1] = (byte)color;
    }

    public void sendCommand(int command) {
        byte[] commands = {(byte)(command & 0xFF)};
        digitalWrite(DC_PIN, LOW);
        wiringPiSPIDataRW(CHANNEL, commands, 1);
    }

    public void begin() {
        pinMode(RST_PIN, OUTPUT);
        pinMode(DC_PIN, OUTPUT);
        wiringPiSPISetup(CHANNEL, 2000000);    //2M

        digitalWrite(RST_PIN, HIGH);
        delay(10);
        digitalWrite(RST_PIN, LOW);
        delay(10);
        digitalWrite(RST_PIN, HIGH);

        sendCommand(DISPLAY_OFF);          //Display Off
        sendCommand(SET_CONTRAST_A);       //Set contrast for color A
        sendCommand(0xFF);                     //145 0x91
        sendCommand(SET_CONTRAST_B);       //Set contrast for color B
        sendCommand(0xFF);                     //80 0x50
        sendCommand(SET_CONTRAST_C);       //Set contrast for color C
        sendCommand(0xFF);                     //125 0x7D
        sendCommand(MASTER_CURRENT_CONTROL);//master current control
        sendCommand(0x06);                     //6
        sendCommand(SET_PRECHARGE_SPEED_A);//Set Second Pre-change Speed For ColorA
        sendCommand(0x64);                     //100
        sendCommand(SET_PRECHARGE_SPEED_B);//Set Second Pre-change Speed For ColorB
        sendCommand(0x78);                     //120
        sendCommand(SET_PRECHARGE_SPEED_C);//Set Second Pre-change Speed For ColorC
        sendCommand(0x64);                     //100
        sendCommand(SET_REMAP);            //set remap & data format
        sendCommand(0x72);                     //0x72              
        sendCommand(SET_DISPLAY_START_LINE);//Set display Start Line
        sendCommand(0x0);
        sendCommand(SET_DISPLAY_OFFSET);   //Set display offset
        sendCommand(0x0);
        sendCommand(NORMAL_DISPLAY);       //Set display mode
        sendCommand(SET_MULTIPLEX_RATIO);  //Set multiplex ratio
        sendCommand(0x3F);
        sendCommand(SET_MASTER_CONFIGURE); //Set master configuration
        sendCommand(0x8E);
        sendCommand(POWER_SAVE_MODE);      //Set Power Save Mode
        sendCommand(0x00);                     //0x00
        sendCommand(PHASE_PERIOD_ADJUSTMENT);//phase 1 and 2 period adjustment
        sendCommand(0x31);                     //0x31
        sendCommand(DISPLAY_CLOCK_DIV);    //display clock divider/oscillator frequency
        sendCommand(0xF0);
        sendCommand(SET_PRECHARGE_VOLTAGE);//Set Pre-Change Level
        sendCommand(0x3A);
        sendCommand(SET_V_VOLTAGE);        //Set vcomH
        sendCommand(0x3E);
        sendCommand(DEACTIVE_SCROLLING);   //disable scrolling
        sendCommand(NORMAL_BRIGHTNESS_DISPLAY_ON);//set display on
    }

    public void clearScreen() {
        clearFrameBuffer();
        display();
    }

    public void clearFrameBuffer() {
        for(int i = 0; i < frameBuffer.length; i++) {
            frameBuffer[i] = 0;
        }
    }

    // A monocolor bitmap example for easy migration from some embedded hardware.
    final public int[] WAVESHARE_LOGO = {/*0X00,0X01,0X60,0X00,0X40,0X00,*/
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X41,0X83,0X0C,0X30,0X33,0XF1,0XF8,0XC1,0X81,0X81,0XFC,0X7E,0X61,0XC2,0X0E,0X10,
        0X33,0X03,0X00,0XC1,0X83,0X81,0X8C,0X60,0X61,0XC2,0X1A,0X18,0X23,0X03,0X00,0XC1,
        0X82,0XC1,0X84,0X60,0X63,0X46,0X12,0X18,0X63,0X03,0X00,0XC1,0X82,0X41,0X84,0X60,
        0X23,0X46,0X33,0X08,0X43,0X01,0X80,0XC1,0X86,0X61,0X8C,0X60,0X32,0X64,0X31,0X0C,
        0XC3,0XF0,0XE0,0XFF,0X84,0X61,0XF8,0X7E,0X32,0X64,0X21,0X8C,0XC3,0X00,0X30,0XC1,
        0X8C,0X21,0XF0,0X60,0X16,0X2C,0X7F,0X84,0X83,0X00,0X18,0XC1,0X8F,0XF1,0X98,0X60,
        0X1C,0X3C,0X7F,0X87,0X83,0X00,0X18,0XC1,0X8F,0XF1,0X8C,0X60,0X1C,0X38,0XC0,0XC7,
        0X83,0X02,0X18,0XC1,0X98,0X11,0X86,0X60,0X0C,0X18,0XC0,0XC3,0X03,0XF3,0XF0,0XC1,
        0X98,0X19,0X86,0X7E,0X08,0X18,0X80,0X43,0X03,0XF0,0XE0,0XC1,0X90,0X09,0X02,0X7E,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X3E,0X00,0X1F,0XFF,0XFF,0X3F,0X76,0XE0,0X7F,0XFF,0XFE,0X3F,0XFF,
        0XFE,0X1F,0XFF,0XFF,0X3F,0X76,0XF0,0X7F,0XFF,0XFE,0X3F,0XFF,0XFE,0X1F,0XFF,0XFF,
        0X3B,0X76,0XFC,0X7F,0XFF,0XFE,0X3F,0XFF,0XFE,0X1F,0XFF,0XFF,0XFB,0X77,0XFC,0X00,
        0X3C,0X00,0X3F,0XFF,0XFE,0X00,0X00,0X3F,0XF3,0X77,0XFC,0X7F,0XFF,0XFE,0X3F,0XFF,
        0XFE,0X00,0X00,0X3E,0XF3,0X77,0XC0,0X7F,0XFF,0XFE,0X3C,0X3E,0X1E,0X00,0X00,0X7C,
        0X73,0XFE,0X00,0X7F,0XFF,0XFE,0X3C,0X3E,0X1E,0X00,0X00,0X7C,0X3F,0XFE,0X1C,0X70,
        0X3C,0X0E,0X3C,0X3E,0X1E,0X00,0X00,0XF8,0X3F,0XFE,0XDC,0X77,0X3C,0XEE,0X3C,0X3E,
        0X1E,0X01,0XFF,0XF0,0X7B,0XFD,0XF8,0X77,0XFF,0XEE,0X3F,0XFF,0XFE,0X01,0XFF,0XF0,
        0XF8,0X01,0XF8,0X73,0XBD,0XCE,0X3F,0XFF,0XFE,0X01,0XFF,0XE0,0XFB,0XFC,0XF8,0X71,
        0XBD,0X8E,0X3F,0XFF,0XFE,0X00,0X01,0XE0,0XF3,0XFE,0XF8,0X73,0XBF,0XCE,0X3F,0XFF,
        0XFE,0X00,0X01,0XE0,0XF3,0XFE,0XF8,0X7F,0XBF,0XEE,0X3C,0X3E,0X1E,0X00,0X01,0XE0,
        0X72,0X04,0XF0,0X00,0X00,0X00,0X3C,0X3E,0X1E,0X3F,0XFF,0XFF,0X70,0X00,0XF0,0X7F,
        0XFF,0XFE,0X3C,0X3E,0X1E,0X3F,0XFF,0XFF,0X71,0XF8,0X70,0X7F,0XFF,0XFE,0X3C,0X3E,
        0X1E,0X3F,0XFF,0XFF,0X71,0XFC,0X70,0X7F,0XFF,0XFE,0X3C,0X3E,0X1E,0X3F,0XFF,0XFF,
        0X71,0XFC,0X70,0X00,0X00,0X1E,0X3F,0XFF,0XFE,0X00,0X01,0XE0,0X71,0XDC,0XF0,0X00,
        0X00,0X1E,0X3F,0XFF,0XFE,0X00,0X01,0XE0,0X71,0XDC,0XF8,0X7F,0XFF,0XFE,0X3F,0XFF,
        0XFE,0X00,0X01,0XE0,0X71,0XDC,0XF8,0X7F,0XFF,0XFE,0X3F,0XFF,0XFE,0X00,0X01,0XE0,
        0X71,0XDC,0XF8,0X7F,0XFF,0XFE,0X00,0X3E,0X00,0X00,0X01,0XE0,0X71,0XDD,0XF8,0X00,
        0X00,0X1E,0X00,0X3F,0XFF,0X0F,0XFF,0XE0,0X73,0XDF,0XFC,0X00,0X00,0X1E,0X00,0X3F,
        0XFF,0X0F,0XFF,0XE0,0X77,0XDF,0XFC,0X7F,0XFF,0XFE,0X00,0X3F,0XFF,0X0F,0XFF,0XE0,
        0X73,0X9F,0XDC,0X7F,0XFF,0XFE,0X00,0X3F,0XFF,0X0F,0XFF,0XC0,0X70,0X00,0X08,0X7F,
        0XFF,0XFE,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
        0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
    };
}
