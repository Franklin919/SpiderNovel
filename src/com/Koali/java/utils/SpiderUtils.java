package com.Koali.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderUtils {
	public static String getText(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		Element contentOrigin = doc.getElementById("content");
		String contentText = contentOrigin.html();
		String  contentFinally= contentText.replaceAll("<br>|&nbsp;","");
		return contentFinally;
	}
}
