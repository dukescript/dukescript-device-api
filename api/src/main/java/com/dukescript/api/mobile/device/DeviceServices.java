/*
 * #%L
 * device-api - a library from the "DukeScript" project.
 * %%
 * Copyright (C) 2018 Dukehoff GmbH
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
package com.dukescript.api.mobile.device;

import com.dukescript.api.mobile.device.spi.DeviceService;
import com.dukescript.api.mobile.device.impl.AndroidDeviceService;
import com.dukescript.api.mobile.device.impl.MOEDeviceService;
import com.dukescript.api.mobile.device.impl.RoboVMDeviceService;
import net.java.html.json.Model;
import net.java.html.json.Property;

/**
 * A service to get information about the device your application is running on.
 * usage:
 * <p>
 * {@codesnippet com.dukescript.api.mobile.device.DeviceServicesTest#testDummyImpl}
 * 
 * @author antonepple
 */
public class DeviceServices {

    public static DeviceService INSTANCE;

    /**
     * Get the Device your application is running on.
     * @return the Device  
     */
    public static Device getDevice() {
        if (INSTANCE == null) {
            try {
                AndroidDeviceService androidDeviceService = new AndroidDeviceService();
                androidDeviceService.getDeviceImpl();
                INSTANCE = androidDeviceService;
            } catch (NoClassDefFoundError ncdfe) {
                // not android
            }
            if (INSTANCE == null) {
                try {
                    RoboVMDeviceService roboVMDeviceService = new RoboVMDeviceService();
                    roboVMDeviceService.getDeviceImpl();
                    INSTANCE = roboVMDeviceService;
                } catch (NoClassDefFoundError ncdfe) {
                    // not RoboVM
                }
            }
            if (INSTANCE == null) {
                try {
                    MOEDeviceService moeDeviceService = new MOEDeviceService();
                    moeDeviceService.getDeviceImpl();
                    INSTANCE = moeDeviceService;
                } catch (NoClassDefFoundError ncdfe) {
                    // not MobiVM
                }
            }
        }
        if (INSTANCE == null) {
            // fallback to Dummy/JS
            INSTANCE = new DeviceServiceImpl();
        }
        return INSTANCE.getDeviceImpl();
    }


    @Model(className = "Device", properties = {
        @Property(name = "platform", type = String.class),
        @Property(name = "version", type = String.class),
        @Property(name = "model", type = String.class),
        @Property(name = "manufacturer", type = String.class)}
    )
    public static class DeviceVMD {
    }

    private static class DeviceServiceImpl implements DeviceService {

        public DeviceServiceImpl() {
        }

        @Override
        public Device getDeviceImpl() {
            return new Device();
        }
    }
}
