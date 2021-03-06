package com.dukescript.api.mobile.robovm;

import com.dukescript.api.device.tests.DeviceInfoCheck;
import net.java.html.boot.BrowserBuilder;
import net.java.html.junit.BrowserRunner;

/*
 * #%L
 * RoboVM Testing - Device API - a library from the "DukeScript" project.
 * %%
 * Copyright (C) 2015 - 2018 Dukehoff GmbH
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

public class RoboMain {
    public static void main(String... args) {
        BrowserBuilder.newBrowser().
            loadPage("pages/index.html").
            loadFinished(new Runnable() {
            @Override
            public void run() {
                try {
                    executeTests();
                    System.exit(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).showAndWait();
    }

    static void executeTests() throws Exception {
        BrowserRunner.execute(DeviceInfoCheck.class.getName());
    }
}
