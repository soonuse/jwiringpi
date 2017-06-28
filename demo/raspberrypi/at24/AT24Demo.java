/**
  * @filename       : AT24Demo.java
  * @date           : June 28 2017
  * @author         : soonuse from Github
  * @description:
  *   This demo is written in Java and tested with an AT24C04 EEPROM 
  *   module working on Raspberry Pi 3 Model B.
  *
  *   Expected result:
  *   This writes 0x00, 0x01, 0x02 ... 0xFF, 0x00, 0x01, 0x02 ... 0xFF
  *   (512 bytes) into the EEPROM and then reads them and print out.
  ***********************************************************************
  * This file is a demo of AT24C04 EEPROM working on Raspberry Pi.
  *
  * This demo is distributed in the hope that it will be useful,
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

public class AT24Demo {
    private static final int BUFFER_SIZE = 512;
    public static void main(String[] args) {
        AT24EEPROM eeprom = new AT24EEPROM();
        System.out.printf("fd1: %d\nfd2: %d\n", eeprom.fdFirst16Pages, eeprom.fdLast16Pages);
        byte[] dataWrite = new byte[BUFFER_SIZE];
        for (int i = 0; i < BUFFER_SIZE; i++) {
            dataWrite[i] = (byte)i;
        }
        eeprom.writeAll(dataWrite);
        byte[] dataRead = eeprom.readAll();
        System.out.println("Bytes read:");
        for (int i = 0; i < BUFFER_SIZE; i++) {
            System.out.printf("%02X ", dataRead[i]);
            if (i % 256 == 255) {
                System.out.println();
            }
        }
    }
}
