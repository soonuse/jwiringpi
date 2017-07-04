/**
  * @filename       : SSD1331SPIDemo.java
  * @date           : July 4 2017
  * @author         : soonuse from Github
  * @description:
  *   This demo is written in Java and tested with a Waveshare SSD1331 
  *   0.95inch RGB OLED working on Raspberry Pi 3 Model B. Before running 
  *   this demo, you have to enable the SPI interface on the Raspberry Pi. 
  *   See sudo raspi-config
  *
  *   Expected result:
  *   The OLED displays strings and images.
  ***********************************************************************
  * This is a demo of SSD1331 0.95inch OLED module working on Raspberry Pi.
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

public class SSD1331SPIDemo {
    public static void main(String[] args) {
        SSD1331SPI oled = new SSD1331SPI();
        if (oled.wiringPiSetup() < 0) {
            System.out.println("WiringPi setup error");
            return;
        }
        oled.begin();
        oled.pinMode(oled.RST_PIN, oled.OUTPUT);

        oled.clearFrameBuffer();
        oled.showImage(0, 0, new File("anne.bmp"));
        oled.delay(2000);
        oled.clearFrameBuffer();
        oled.showString("Hello world!", 0, 36);
        oled.delay(2000);
        oled.clearFrameBuffer();
        oled.showMonoBitmap(0, 0, oled.WAVESHARE_LOGO, oled.OLED_WIDTH, oled.OLED_HEIGHT, oled.BLUE);
    }
}
