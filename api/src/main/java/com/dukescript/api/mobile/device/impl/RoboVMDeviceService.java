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
package com.dukescript.api.mobile.device.impl;

import com.dukescript.api.mobile.device.Device;
import com.dukescript.api.mobile.device.spi.DeviceService;
import java.util.Properties;
import org.robovm.apple.foundation.Foundation;

/**
 *
 * @author antonepple
 */
public class RoboVMDeviceService implements DeviceService{

    @Override
    public Device getDeviceImpl() {
        Device device = new Device();
        device.setManufacturer("Apple");
        device.setPlatform("iOS");
        Properties properties = java.lang.System.getProperties();

        device.setModel(properties.getProperty("os.name"));
        device.setVersion(Foundation.getSystemVersionString());
        
        return device;
    }
    
}
