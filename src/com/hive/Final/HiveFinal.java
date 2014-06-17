
package com.hive.Final;

import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.hive.Parameter.HiveParameter;
import com.hive.Save.HiveSaveData;

public class HiveFinal implements Runnable {
	private HiveParameter hiveParameter;
	private Lock lock = new ReentrantLock();
	
	public HiveFinal( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}

	 public void run() {
		while( true ) {
			try {
				//lock.lock();
				String link = hiveParameter.hiveRedis.popValue();
				//lock.unlock();
					if ( hiveParameter.Flag == 1 ) {
							URL url = new URL(link);
							if ( !url.getHost().equals(hiveParameter.Hostname) ) {
								continue;
							}
						} else if ( hiveParameter.Flag == 2 ) {
							if ( hiveParameter.hiveRedis.getLength() <= 0 ) {
								System.out.println("这才是真正的结束");
								break;
							}
						}

						Document Doc = null;
						lock.lock();
					//	if ( !hiveParameter.hiveDatabase.isUniqueURL(link) ) {
						if ( !hiveParameter.hiveBloomFilter.isUniqueValue(link) ) {
							//System.out.println(link);
							System.out.println(link + "\t\t\tThread ID = " + Thread.currentThread().getId());
							//hiveParameter.hiveDatabase.insertUrl(link);
							hiveParameter.hiveBloomFilter.addValue(link);
							//new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.html(), hiveParameter.Path, true);
						} else {
							continue;
						}
						lock.unlock();
						
						Doc = Jsoup.connect(link).timeout(50000)
								.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
								.get();
						new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.select("p").text(), hiveParameter.Path, false);
						//new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.html(), hiveParameter.Path, true);
			} catch ( Exception e ) {
				e.printStackTrace();
				//System.out.println("HiveFinal-run-line46:\t" + e);
			}
		}
	}
}
