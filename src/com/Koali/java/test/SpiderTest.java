package com.Koali.java.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.Koali.java.utils.SpiderUtils;

public class SpiderTest {
	public static void main(String[] args) {
		try {
			PrintStream ps = new PrintStream(new FileOutputStream(new File("D:\\pic\\", "斗破苍穹.txt")));
			ps.println("");// 往文件里写入字符串
			String url = "http://www.37zw.com/1/1227/";
			/* 605834是页数的编码，所以要修改 */
			int first = 605834;// 第一章
			int last = 1861698;// 最后一章
			int i = 1;
			for (; i < 1307; first++,i++) {
				System.out.println(url + first + ".html");
				try {
					System.out.println("第"+i+"章开始下载!");
					ps.append("第"+i+"章!\r\n");
					ps.append(SpiderUtils.getText(url + first + ".html"));// 在已有的基础上添加字符串
					ps.append("\r\n");
					System.out.println("第"+i+"章下载结束!");
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
