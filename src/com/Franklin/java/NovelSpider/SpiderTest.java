package com.Franklin.java.NovelSpider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Node;

import com.Franklin.java.utils.SpiderUtils;

public class SpiderTest {
	

    private static String formattedDecimalToPercentage(double decimal)
    {
    	//获取格式化对象
    	NumberFormat nt = NumberFormat.getPercentInstance();
    	//设置百分数精确度2即保留两位小数
    	nt.setMinimumFractionDigits(2);
    	return nt.format(decimal);
    }

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			String bookSort = null;
			double d = 0;	//进度
			System.out.println("【1:玄幻小说；2:修真小说；3:都市小说；4:穿越小说；5:网游小说；6:科幻小说】");
			System.out.println("请选择要爬取的小说类型：（输入编号即可）");
			int sortNum = sc.nextInt();
			switch(sortNum) {
				case 1:
					bookSort = "玄幻小说";
				break;
				case 2:
					bookSort = "修真小说";
					break;
				case 3:
					bookSort = "都市小说";
					break;
				case 4:
					bookSort = "穿越小说";
					break;
				case 5:
					bookSort = "网游小说";
					break;
				case 6:
					bookSort = "科幻小说";
					break;
				default:
					break;
			}
			ArrayList booklist = new ArrayList<Node>(SpiderUtils.getBooklist("https://www.37zw.net/xiaoshuo" + sortNum));
			for (int i = 0; i < booklist.size(); i++) {
				String url = booklist.get(i).toString();
				String title = SpiderUtils.getTitle(url);
				System.out.println("正在爬取书本:"+title+"，地址："+booklist.get(i));
				File bookFolder = new File("/Users/franklin919/Documents/小说/"+bookSort);
				File bookFile = new File("/Users/franklin919/Documents/小说/"+bookSort+"/"+title+".txt");
				//System.out.println("文件名："+bookFile);
				if(!bookFolder.exists()) {
					bookFolder.mkdirs();
				};
				if(!bookFile.exists()){
					PrintStream ps = new PrintStream(new FileOutputStream(new File("/Users/franklin919/Documents/小说/"+bookSort, title + ".txt")));
					ps.println("");
					ArrayList list = new ArrayList<Node>(SpiderUtils.getChara(url));
					for (int j = 1; j < list.size(); j++) {
						// System.out.print(url + list.get(i) + ".html：");
						try {
							d= (double)j / list.size();
							// System.out.println("第"+i+"章开始下载!");
							ps.append("第" + j + "章!\r\n");
							ps.append(SpiderUtils.getText(url + list.get(j) + ".html"));// 在已有的基础上添加字符串
							ps.append("\r\n");
							System.out.println("下载进度："+ formattedDecimalToPercentage(d) +"，" +j+" / "+list.size());
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}else {
					System.out.println("书本："+title+"已存在，如要下载请手动删除文件；");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
