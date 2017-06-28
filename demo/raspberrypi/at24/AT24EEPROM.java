/**
  * @filename       : AT24EEPROM.java
  * @date           : June 28 2017
  * @author         : soonuse from Github
  * @description:
  *   This class is written in Java and tested with an AT24C04 EEPROM 
  *   module working on Raspberry Pi 3 Model B.
  ***********************************************************************
  * This file is a class of AT24C04 EEPROM working on Raspberry Pi.
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
import java.io.*;

public class AT24EEPROM extends JWiringPiController {
    public static final int ADDR_AT24C04_FIRST_16_PAGES = 0x50;
    public static final int ADDR_AT24C04_LAST_16_PAGES = 0x51;
    public static final int AT24C04_PAGE_SIZE = 16;
    public static final int BUFFER_SIZE = 512;

    public int fdFirst16Pages;
    public int fdLast16Pages;

    public AT24EEPROM() {
        if (this.wiringPiSetup() < 0) {
            System.out.println("WiringPi setup error.");
            System.exit(-1);
        }
        this.fdFirst16Pages = this.wiringPiI2CSetup(ADDR_AT24C04_FIRST_16_PAGES);
        this.fdLast16Pages = this.wiringPiI2CSetup(ADDR_AT24C04_LAST_16_PAGES);
    }
    public int writeAll(byte[] data) {
        int memoryAddress = 0;
        int page = 0;
        int dataIndex = 0;
        int remain = data.length;
        int state;
        byte[] subBuffer = new byte[AT24C04_PAGE_SIZE + 1];

        while(page < 16) {
            memoryAddress = page << 4;
            subBuffer[0] = (byte)(memoryAddress & 0xFF);
            for (int i = 1; i < subBuffer.length; i++) {
                subBuffer[i] = data[dataIndex];
                dataIndex++;
            }
            state = wiringPiI2CWrite(this.fdFirst16Pages, subBuffer, subBuffer.length);
            if (state != subBuffer.length) {
                System.out.println("write error: " + state);
                // write error
                return -1;
            }
            this.delay(20);
            page++;
        }

        while(page >= 16 && page < 32) {
            memoryAddress = (page - 16) << 4;
            subBuffer[0] = (byte)(memoryAddress & 0xFF);
            for (int i = 1; i < subBuffer.length; i++) {
                subBuffer[i] = data[dataIndex];
                dataIndex++;
            }

            state = wiringPiI2CWrite(this.fdLast16Pages, subBuffer, subBuffer.length);
            if (state != subBuffer.length) {
                // write error
                System.out.println("write error: " + state);
                return -1;
            }
            this.delay(20);
            page++;
        }
        return 0;
    }

    public byte[] readAll() {
        byte[] data = new byte[BUFFER_SIZE];

        // Specify the start address to read: 0 offset from the head of first 16 pages
        wiringPiI2CWrite(this.fdFirst16Pages, 0);

        // Read data continuously from EEPROM
        for (int i = 0; i < BUFFER_SIZE; i++) {
            data[i] = (byte)wiringPiI2CRead(this.fdFirst16Pages);
        }
        return data;    
    }
}
