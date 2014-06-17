package com.hive.Save;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.hive.Parameter.HiveParameter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

class XMLFormat {
	private String title;
	private String crawltime;
	private String modifytime;
	private String source;
	private String language;
	private String encode;
	private String body;
	
		
	public String getTitle() {	return this.title;	}
	public String getCrawltime() {	return this.crawltime;	}
	public String getModifytime() {	return this.modifytime;	}
	public String getSource() {	return this.source;	}
	public String getLanguage() {	return this.language;	}
	public String getEncode() {	return this.encode;	}
	public String getBody() {	return this.body;	}
	
	public void setTitle( String title ) {	this.title = title;	}
	public void setCrawltime( String crawltime ) {	this.crawltime = crawltime;	}
	public void setModifytime( String modifytime ) {	this.modifytime = modifytime;	}
	public void setSource( String source ) {	this.source = source;	}
	public void setLanguage( String language ) {	this.language = language;	}
	public void setEncode( String encode ) {	this.encode =encode;	}
	public void setBody( String body ) {	this.body =  body;	}
}

public class HiveSaveData {
	private HiveParameter hiveParameter = null;
	
	public HiveSaveData( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}
	
	
	public void doSaveToDB( String Title, String Link, String Pinfo, String SavePath, boolean isInsert ) {
		XStream xstream = new XStream( new DomDriver() );
		XMLFormat xmlFormat = new XMLFormat();
		xstream.alias("hive", xmlFormat.getClass());
		SimpleDateFormat SDF = new SimpleDateFormat( "yyyy-MM-dd" );
		File file = new File(SavePath + new Date().getTime() + ".xml");
		xmlFormat.setTitle(Title);
		xmlFormat.setCrawltime(SDF.format(new Date()));
		xmlFormat.setModifytime("");
		xmlFormat.setSource(Link);
		xmlFormat.setLanguage("中文");
		xmlFormat.setEncode("utf-8");
		xmlFormat.setBody(Pinfo);
		
		String XML = xstream.toXML(xmlFormat);
	//	System.out.println(XML);
		try {
			FileUtils.writeStringToFile(file, XML);
			hiveParameter.hiveLog.SysLog("save ok!");
			if ( isInsert ) {
				//hiveParameter.hiveDatabase.insertUrl(Link);
				hiveParameter.hiveBloomFilter.addValue(Link);
			}
			//System.exit(0);
		} catch( IOException e ) {
			hiveParameter.hiveLog.ErrLog("write error!");
			e.printStackTrace();
		} catch( Exception e ) {
			System.out.println(e);
		}
	}	
}
