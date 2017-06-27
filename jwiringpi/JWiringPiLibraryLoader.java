/**
  * @filename       : JWiringPiLibraryLoader.java
  * @project        : JWiringPi
  * @date           : June 26 2017
  * @description:
  *      Java wrapper of Arduino like Wiring library for the Raspberry Pi.
  *      The implements are based on WiringPi library.
  *      WiringPi Library Copyright (c) 2012-2017 Gordon Henderson
  *      JWiringPi project Copyright (c) 2017 soonuse from GitHub
  ***********************************************************************
  * This file is part of JWiringPi interface.
  *
  * The purpose of JWiringPi project is to create a convenient IO control
  * interface (containing the implements of class) for Raspberry Pi Java
  * programming.
  *
  * JWiringPi is free software: you can redistribute it and/or modify
  * it under the terms of the General Public License (GPL).
  *
  * JWiringPi is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

package jwiringpi;
import java.io.File;

/**
  * @brief C library loader to load libc4jwiringpi.so
  */
public class JWiringPiLibraryLoader {
    String[] libraryPath =
        System.getProperty("java.library.path").split("[:;]");
    int numberOfLibraryPath = libraryPath.length;
    boolean load() {
        for (int i = 0; i < this.numberOfLibraryPath; i++) {
            if (new File(libraryPath[i] +
                File.separator +
                "libc4jwiringpi.so").exists()) {
                // this loads the library libjavawiringpi.so, which equals
                //System.load("/usr/lib/libjavawiringpi.so");
                System.loadLibrary("c4jwiringpi");
                return true;
            } else {
                if (i == numberOfLibraryPath - 1) {
                    return false;
                }
            }
        }
        return false;
    }

}
