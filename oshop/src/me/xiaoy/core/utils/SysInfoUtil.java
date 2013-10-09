package me.xiaoy.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class SysInfoUtil {

	private static final Properties props = System.getProperties();

	public static final String OSNAME = "os.name";

	public static final String OSVERSION = "os.version";

	public static final String JAVAVERSION = "java.version";

	/**
	 * 获取操作系统名称
	 * 
	 * @return 操作系统名称
	 */
	public static String getSystemOS() {
		return props.getProperty(OSNAME);
	}

	/**
	 * 获取操作系统版本
	 * 
	 * @return
	 */
	public static String getSysteOSVersion() {
		return props.getProperty(OSVERSION);
	}

	/**
	 * 获取java运行时的环境版本
	 * 
	 * @return
	 */
	public static String getJavaVersion() {
		return props.getProperty(JAVAVERSION);
	}

	/**
	 * 获取linux网卡的mac地址. 系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
	 * 
	 * @return mac地址
	 */
	public static String getLinuxAllMACAddr() {
		StringBuffer sbMac = new StringBuffer();
		BufferedReader br = null;
		Process process = null;
		try {
			// linux下的命令获取所有网卡的mac地址
			process = Runtime.getRuntime().exec("ifconfig -a");// linux下的命令获取所有网卡的mac地址
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String strLine = null;
			int iTmpIndx = 0;
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
				iTmpIndx = strLine.toLowerCase().indexOf("hwaddr");
				if (strLine.startsWith("eth") && iTmpIndx > 0)// 如果以指定的字符串开头并包含指定的字符串
				{
					strLine = strLine.substring(
							iTmpIndx + "hwaddr".length() + 1).trim();
					sbMac.append(strLine.replaceAll(":", ""));// 拼接mac地址
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			br = null;
			process = null;
		}

		return sbMac.toString();
	}

	/**
	 * 获取widnows网卡的mac地址.
	 * 
	 * @return mac地址
	 */
	public static String getWindowsAllMACAddress() {
		StringBuffer sbMac = new StringBuffer();
		BufferedReader br = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
			br = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String strLine = null;
			int iTmpIndx = -1;
			while ((strLine = br.readLine()) != null) {
				iTmpIndx = strLine.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical
																				// address]
				if (iTmpIndx >= 0) {
					iTmpIndx = strLine.indexOf(":");// 寻找":"的位置
					if (iTmpIndx >= 0) {
						strLine = strLine.substring(iTmpIndx + 1).trim();// 取出mac地址并去除2边空格
						strLine = strLine.replaceAll("-", "");
						sbMac.append(strLine);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			br = null;
			process = null;
		}

		return sbMac.toString();
	}

}
