
package com.hive.Final;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.hive.Parameter.HiveParameter;
import com.hive.Save.HiveSaveData;

public class HiveFinal implements Runnable {
	private HiveParameter hiveParameter;
	
	public HiveFinal( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}

	public void run() {
		while( true ) {
			try {
				String link = hiveParameter.hiveRedis.popValue();
				if ( hiveParameter.Flag == 1 ) {
						URL url = new URL(link);
						if ( !url.getHost().equals(hiveParameter.Hostname)  || hiveParameter.hiveDatabase.isUniqueURL(link) ) {
							continue;
						}
					} else if ( hiveParameter.Flag == 2 ) {
						if ( hiveParameter.hiveRedis.getLength() <= 0 ) {
							System.out.println("这才是真正的结束");
							break;
						}
					}
					System.out.println(link);
					Document Doc = null;
					Doc = Jsoup.connect(link).timeout(50000)
						.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
						.get();
					//hiveParameter.hiveDatabase.insertUrl(link);
					new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.html(), hiveParameter.Path);		
			} catch ( Exception e ) {
				System.out.println("HiveFinal-run-line46:\t" + e);
			}
		}
	}
}
