
package com.hive.Mode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hive.Parameter.HiveParameter;
import com.hive.Save.HiveSaveData;


public class HiveVertical {
	private HiveParameter hiveParameter = null;
	static public int count = 0;
	public HiveVertical( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
		try {
			this.hiveParameter.Hostname = new URL(hiveParameter.Url).getHost();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void doSaveData() {
		try {
			Document Doc = Jsoup.connect(hiveParameter.Url).timeout(5000)
								.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
								.get();
			System.out.println(hiveParameter.Url);
			//hiveParameter.hiveDatabase.insertUrl(hiveParameter.Url);
			System.out.println(Doc.select("p").text());
			new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), hiveParameter.Url, Doc.select("p").text(), hiveParameter.config.getSavePath()+hiveParameter.Ttag, true);
			System.out.println("hiveParameter.hostname = " + hiveParameter.Hostname);
			Elements pageAllUrls = Doc.select("a[href]");
			for ( Element pageAllUrl : pageAllUrls ) {
				String pageUrl = pageAllUrl.attr("abs:href");
				System.out.println(pageUrl);
				if ( pageUrl.isEmpty() )
					continue;
				String hostname = new URL(pageUrl).getHost();
				System.out.println(hostname);
				//if ( (hostname.equals(hiveParameter.Hostname) && !(hiveParameter.hiveDatabase.isUniqueURL(pageUrl))) ) {
				if ( (hostname.equals(hiveParameter.Hostname)) && !(hiveParameter.hiveBloomFilter.isUniqueValue(pageUrl)) )	 {
					System.out.println("加入redis");
					count ++;
					hiveParameter.hiveRedis.pushValue(pageUrl);
					//hiveParameter.hiveDatabase.insertUrl(pageUrl); 	//抓取后再加入数据库才对
				}
			}
			System.out.println("结束");
		} catch( IOException e ) {
			System.out.println(e);
		}
	}
}
