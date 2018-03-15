package com.powerps;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IPAddress {

    public static String macAddress[] = new String[20];
    public static int totalMac = 0;

    public IPAddress() {
    }

    public static void getIP() {
        IPAddress ip = new IPAddress();
        ip.getInterfaces();
    }

    public void getInterfaces() {
        try {
            totalMac = 0;
            for (Enumeration e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface ni = (NetworkInterface) e.nextElement();
                Enumeration e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ip = (InetAddress) e2.nextElement();
                    String ipD = ip.toString();
                    if (ipD.length() > 20)
                        macAddress[totalMac++] = ipD;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
