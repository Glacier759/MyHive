package com.hive.Mode;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hive.Parameter.HiveParameter;
import com.hive.Save.HiveSaveData;
import com.hive.SearchEngine.ThreeSixZeroSpider;

public class HiveSearch {
	private HiveParameter hiveParameter = null;
	
	public HiveSearch( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}
	public void doSaveData() {
		try {
			//添加搜索引擎
			ThreeSixZeroSpider Spider360 = new ThreeSixZeroSpider( hiveParameter );
			Set<String> UrlSet = Spider360.doParse360();
			System.out.println(UrlSet.size());
			Iterator<String> itr = UrlSet.iterator();
			while( itr.hasNext() ) {
				String purl = itr.next();
				System.out.println(purl);
				String Hostname = new URL(purl).getHost();
				Document Doc = null;
				try {
					Doc = Jsoup.connect(purl).timeout(50000)
						.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
						.get();
				} catch( SocketTimeoutException e ) {
					System.out.println("HiveSearch-doSaveData-42:\t" + e);
					continue;
				} catch( HttpStatusException e ) {
					System.out.println("HiveSearch-doSaveData-45:\t" + e);
					continue;
				}
				hiveParameter.hiveDatabase.insertUrl(purl);
				new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), purl, Doc.html(), hiveParameter.Path);
				Elements pageAllUrls = Doc.select("a[href]");
				for ( Element pageAllUrl : pageAllUrls ) {
					String pageUrl = pageAllUrl.attr("abs:href");
					if ( pageUrl.isEmpty() )
						continue;
					String hostname = new URL(pageUrl).getHost();
					if ( (hostname.equals(Hostname) && !(hiveParameter.hiveDatabase.isUniqueURL(pageUrl))) ) {
						hiveParameter.hiveRedis.pushValue(pageUrl);
						//hiveParameter.hiveDatabase.insertUrl(pageUrl); 	//抓取后再加入数据库才对
					}
				}
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
