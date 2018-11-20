package com.Franklin.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class SpiderUtils {
	
	public static String subString(String str, String strStart, String strEnd) {
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);
 
        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "空";
        }
        if (strEndIndex < 0) {
            return "空";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }
	
	public static String getTitle(String url) throws IOException{
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
		String contentOrigin = doc.getElementById("list").childNodes().get(1).childNodes().get(1).toString();
		String title = subString(contentOrigin,"《","》");
		return title;
	}
	
	public static ArrayList getChara(String url) throws IOException{
		String pages = null;
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
		List<Node> contentOrigin = doc.getElementById("list").childNodes().get(1).childNodes();
		ArrayList al = new ArrayList<Node>(contentOrigin);
		al.remove(0);
		al.remove(0);
		ArrayList list = new ArrayList<Node>();
		for(int i = 0;i < al.size() ;i++) {
			pages = subString(al.get(i).toString(),"<dd>\n" + " <a href=\"",".html");
			if(pages.equals("空")) {
				continue;
			}else {
				list.add(pages);
			}
		}
		return list;
	}
	
	public static ArrayList getBooklist(String url) throws IOException{
		String book = null;
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
		Elements contentOrigin = doc.getElementsByClass("newlistmulu").get(0).getElementsByTag("a");
		ArrayList booklist = new ArrayList<Node>();
		for(int i = 0; i < contentOrigin.size(); i++) {
			book = "https://www.37zw.net/"+subString(contentOrigin.get(i).toString(), "net/", "\">");
			booklist.add(book);
		}
		return booklist;
	}
	
	public static String getText(String url) throws IOException{
		Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").get();
		Element contentOrigin = doc.getElementById("content");
		String contentText = contentOrigin.html();
		String  contentFinally= contentText.replaceAll("<br>|&nbsp;","");
		return contentFinally;
	}
}
