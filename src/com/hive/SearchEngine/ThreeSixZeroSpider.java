package com.hive.SearchEngine;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hive.Parameter.HiveParameter;

public class ThreeSixZeroSpider {
	private HiveParameter hiveParameter;
	public ThreeSixZeroSpider( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}
	
	public Set<String> doParse360() {
		try {
			Set<String> UrlSet = new HashSet<String>();
			for ( int i = 1; i <= hiveParameter.config.getMaxpn(); i ++ ) {
				String SearchURL = "http://www.so.com/s?q=" + hiveParameter.Tinfo + "&pn=" + i;
				System.out.println(SearchURL);
				Document Doc = Jsoup.connect(SearchURL).timeout(50000)
						.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
						.get();
				Elements pageAllUrls = Doc.select("li[class=res-list]");
				for ( Element pageAllUrl : pageAllUrls ) {
					String pageUrl = pageAllUrl.getElementsByTag("a").attr("href");
					if ( pageUrl.isEmpty() )
						continue;
					else 
						UrlSet.add(pageUrl);
				}
			}
			return UrlSet;
		} catch( IOException e ) {
			hiveParameter.hiveLog.ErrLog(this.getClass().getName() + "  error!");
			e.printStackTrace();
			return null;
		}
	}
}
