package com.imooc.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
   public static void main(String[] args) throws UnknownHostException {
	   InetAddress address=InetAddress.getLocalHost();
	   System.out.println(address.getHostName());
}
}
