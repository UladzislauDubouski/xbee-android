/*
 * Copyright 2019, Digi International Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package com.digi.xbee.api.android;

import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.digi.xbee.api.android.connection.AndroidUSBPermissionListener;
import com.digi.xbee.api.connection.serial.SerialPortParameters;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.relay.ISerialDataReceiveListener;

/**
 * This class represents a local XBee device.
 *
 * @see CellularDevice
 * @see DigiPointDevice
 * @see DigiMeshDevice
 * @see Raw802Device
 * @see ThreadDevice
 * @see WiFiDevice
 * @see ZigBeeDevice
 */
public class XBeeDevice extends com.digi.xbee.api.XBeeDevice {
    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Android USB host interface API to
     * communicate with the devices.</p>
     *
     * @param context The Android context.
     * @param baudRate The USB connection baud rate.
     *
     * @throws IllegalArgumentException if {@code baudRate < 1}.
     * @throws NullPointerException if {@code context == null}.
     *
     * @see #XBeeDevice(Context, int, AndroidUSBPermissionListener)
     * @see #XBeeDevice(Context, String, int)
     * @see #XBeeDevice(Context, String, SerialPortParameters)
     */
    public XBeeDevice(Context context, int baudRate) {
        super(XBee.createConnectiontionInterface(context, baudRate));
    }

    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Android USB host interface API to
     * communicate with the devices.</p>
     *
     * @param context The Android context.
     * @param baudRate The USB connection baud rate.
     * @param permissionListener The USB permission listener that will be
     *                           notified when user grants USB permissions.
     *
     * @throws IllegalArgumentException if {@code baudRate < 1}.
     * @throws NullPointerException if {@code context == null}.
     *
     * @see #XBeeDevice(Context, int)
     * @see #XBeeDevice(Context, String, int)
     * @see #XBeeDevice(Context, String, SerialPortParameters)
     * @see AndroidUSBPermissionListener
     */
    public XBeeDevice(Context context, int baudRate, AndroidUSBPermissionListener permissionListener) {
        super(XBee.createConnectiontionInterface(context, baudRate, permissionListener));
    }

    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Digi Android Serial Port API based on the
     * RxTx library to communicate with the devices.</p>
     *
     * @param context The Android application context.
     * @param port Serial port name where XBee device is attached to.
     * @param baudRate The serial port connection baud rate.
     *
     * @throws NullPointerException If {@code context == null} or
     *                              if {@code port == null}.
     * @throws IllegalArgumentException if {@code baudRate < 1}.
     *
     * @see #XBeeDevice(Context, int)
     * @see #XBeeDevice(Context, int, AndroidUSBPermissionListener)
     * @see #XBeeDevice(Context, String, SerialPortParameters)
     */
    public XBeeDevice(Context context, String port, int baudRate) {
        super(XBee.createConnectiontionInterface(context, port, baudRate));
    }

    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Digi Android Serial Port API based on the
     * RxTx library to communicate with the devices.</p>
     *
     * @param context The Android application context.
     * @param port Serial port name where XBee device is attached to.
     * @param parameters The serial port parameters.
     *
     * @throws NullPointerException If {@code context == null} or
     *                              if {@code port == null} or
     *                              if {@code parameters == null}.
     *
     * @see #XBeeDevice(Context, int)
     * @see #XBeeDevice(Context, int, AndroidUSBPermissionListener)
     * @see #XBeeDevice(Context, String, int)
     * @see com.digi.xbee.api.connection.serial.SerialPortParameters
     */
    public XBeeDevice(Context context, String port, SerialPortParameters parameters) {
        super(XBee.createConnectiontionInterface(context, port, parameters));
    }

    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Android Bluetooth Low Energy API to
     * communicate with the devices.</p>
     *
     * <p>The Bluetooth password must be provided before calling the
     * {@link #open()} method, either through this constructor or the
     * {@link #setBluetoothPassword(String)} method.</p>
     *
     * @param context The Android application context.
     * @param bleDevice Bluetooth device.
     * @param password Bluetooth password (can be {@code null}).
     *
     * @see #XBeeDevice(Context, String, String)
     * @see BluetoothDevice
     */
    public XBeeDevice(Context context, BluetoothDevice bleDevice, String password) {
        super(XBee.createConnectionInterface(context, bleDevice));

        this.bluetoothPassword = password;
    }

    /**
     * Class constructor. Instantiates a new {@code XBeeDevice} object for
     * Android with the given parameters.
     *
     * <p>This constructor uses the Android Bluetooth Low Energy API to
     * communicate with the devices.</p>
     *
     * <p>The Bluetooth password must be provided before calling the
     * {@link #open()} method, either through this constructor or the
     * {@link #setBluetoothPassword(String)} method.</p>
     *
     * @param context The Android application context.
     * @param deviceAddress Address of the Bluetooth device.
     * @param password Bluetooth password (can be {@code null}).
     *
     * @see #XBeeDevice(Context, BluetoothDevice, String)
     */
    public XBeeDevice(Context context, String deviceAddress, String password) {
        super(XBee.createConnectionInterface(context, deviceAddress));

        this.bluetoothPassword = password;
    }

    @Override
    public void setBluetoothPassword(String password) {
        super.setBluetoothPassword(password);
    }

    @Override
    public void sendSerialData(byte[] data) throws XBeeException {
        super.sendSerialData(data);
    }

    @Override
    public void addSerialDataListener(ISerialDataReceiveListener listener) {
        super.addSerialDataListener(listener);
    }

    @Override
    public void removeSerialDataListener(ISerialDataReceiveListener listener) {
        super.removeSerialDataListener(listener);
    }
}
