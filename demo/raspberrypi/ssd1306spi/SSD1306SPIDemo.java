/**
  * @filename       : SSD1306SPIDemo.java
  * @date           : June 29 2017
  * @author         : soonuse from Github
  * @description:
  *   This demo is written in Java and tested with a Waveshare SSD1306 
  *   0.96inch OLED module working on Raspberry Pi 3 Model B. Before running 
  *   this demo, you have to enable the SPI interface on the Raspberry Pi. 
  *   See sudo raspi-config
  *
  *   Expected result:
  *   The OLED displays strings and images.
  ***********************************************************************
  * This is a demo of SSD1306 0.96inch OLED module working on Raspberry Pi.
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
 
import java.io.*;

public class SSD1306SPIDemo {
    public static void main(String[] args) {
        SSD1306SPI oled = new SSD1306SPI();
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
