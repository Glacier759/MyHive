
package com.hive.Mode;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hive.Parameter.HiveParameter;
import com.hive.Save.HiveSaveData;


public class HiveVertical {
	private HiveParameter hiveParameter = null;
	public HiveVertical( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}
	
	public void doSaveData() {
		try {
			Document Doc = Jsoup.connect(hiveParameter.Url).timeout(50000)
								.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
								.get();
			System.out.println(hiveParameter.Url);
			//hiveParameter.hiveDatabase.insertUrl(hiveParameter.Url);
			new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), hiveParameter.Url, Doc.select("p").text(), hiveParameter.HostPathdir, true);
			
			Elements pageAllUrls = Doc.select("a[href]");
			for ( Element pageAllUrl : pageAllUrls ) {
				String pageUrl = pageAllUrl.attr("abs:href");
				System.out.println(pageUrl);
				if ( pageUrl.isEmpty() )
					continue;
				System.out.println(pageUrl);
				String hostname = new URL(pageUrl).getHost();
				//if ( (hostname.equals(hiveParameter.Hostname) && !(hiveParameter.hiveDatabase.isUniqueURL(pageUrl))) ) {
				if ( (hostname.equals(hiveParameter.Hostname)) && !(hiveParameter.hiveBloomFilter.isUniqueValue(pageUrl)) )	 {
					hiveParameter.hiveRedis.pushValue(pageUrl);
					//hiveParameter.hiveDatabase.insertUrl(pageUrl); 	//抓取后再加入数据库才对
				}
			}
			System.out.println("结束");
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
