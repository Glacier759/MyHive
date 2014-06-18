
package com.hive.Final;

import java.net.MalformedURLException;
import java.net.URL;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hive.Parameter.HiveParameter;
import com.hive.Redis.HiveRedis;
import com.hive.Save.HiveSaveData;

public class HiveFinal implements Runnable {
	private HiveParameter hiveParameter;
	private HiveRedis hiveRedis;
	private String ThreadName;
	
	public HiveFinal( HiveRedis hiveRedis, HiveParameter hiveParameter, String ThreadName ) {
		this.hiveParameter = hiveParameter;
		this.hiveRedis = hiveRedis;
		this.ThreadName = ThreadName;
		this.hiveRedis.ConnectRedis(hiveParameter.config.getRedisIP(), hiveParameter.config.getRedisPort());
		this.hiveRedis.setKey(hiveParameter.hiveRedis.getKey());
	}

	 public void run() {
		 String link = "";
		// synchronized (this) {
			 while( true ) {
					try {
						//lock.lock();
						//String link = hiveParameter.hiveRedis.popValue();
						link = hiveRedis.popValue();
						//lock.unlock();
						//if ( hiveParameter.hiveRedis.getLength() <= 0 ) {
						if ( hiveRedis.getLength() <= 0 ) {
							System.out.println("这才是真正的结束");
							break;
						}
							if ( hiveParameter.Flag == 1 ) {
								if ( link.length() == 0 )
									continue;
									URL url = new URL(link);
									if ( !url.getHost().equals(hiveParameter.Hostname) ) {
										continue;
									}
							}

								Document Doc = null;
								//lock.lock();
							//	if ( !hiveParameter.hiveDatabase.isUniqueURL(link) ) {
								if ( !hiveParameter.hiveBloomFilter.isUniqueValue(link) ) {
									//System.out.println(link);
									//System.out.println(link + "\t\t\t"+ThreadName);
									System.out.println(ThreadName+"\t\t"+link);
									//hiveParameter.hiveDatabase.insertUrl(link);
									hiveParameter.hiveBloomFilter.addValue(link);
									//new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.html(), hiveParameter.Path, true);
								} else {
									continue;
								}
								//lock.unlock();
								
								Doc = Jsoup.connect(link).timeout(50000)
										.userAgent("Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
										.get();
								new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.select("p").text(), hiveParameter.Path, false);
								if ( hiveParameter.Flag == 1 ) {
									Elements Links = Doc.select("a[href]");
									for ( Element Link : Links ) {
										hiveRedis.pushValue(Link.attr("abs:href"));
									}
								}
								//new HiveSaveData(hiveParameter).doSaveToDB(Doc.title(), link, Doc.html(), hiveParameter.Path, true);
					} catch( MalformedURLException e ) {
						System.out.println("HiveFinal-82\t" + link + "\t" + e );
					} catch ( Exception e ) {
						e.printStackTrace();
						//System.out.println("HiveFinal-run-line46:\t" + e);
					}
			 }
		//}
	}
}
