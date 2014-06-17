package com.hive.Save;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.hive.Parameter.HiveParameter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

class XMLFormat {
	private String title;
	private String link;
	private String html;
		
	public String getTitle() {	return title;	}
	public String getLink() {	return link;	}
	public String getHtml() {	return html;	}
	public void setTitle( String title ) {	this.title = title;	}
	public void setLink( String link ) {	this.link = link;	}
	public void setHtml( String html ) {	this.html = html;	}
}

public class HiveSaveData {
	private HiveParameter hiveParameter = null;
	
	public HiveSaveData( HiveParameter hiveParameter ) {
		this.hiveParameter = hiveParameter;
	}
	
	
	public void doSaveToDB( String Title, String Link, String HTML, String SavePath, boolean isInsert ) {
		XStream xstream = new XStream( new DomDriver() );
		XMLFormat xmlFormat = new XMLFormat();
		xstream.alias("hive", xmlFormat.getClass());
		File file = new File(SavePath + new Date().getTime() + ".xml");
		xmlFormat.setTitle(Title);
		xmlFormat.setLink(Link);
		xmlFormat.setHtml(HTML);
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
