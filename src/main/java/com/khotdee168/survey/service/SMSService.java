package com.khotdee168.survey.service;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.khotdee168.common.model.SmsResultBean;

@Service
public class SMSService {

	
    @Value("${sms.message.url}")
    //"http://119.46.177.99:55000"
    private String url;

    @Value("${sms.message.username}")
    //"2311761100"
    private String username;

    @Value("${sms.message.password}")
    //"BnKm83R8"
    private String password;

    @Value("${sms.message.shortCode}")
    //"40001414";
    private String shortCode;

    @Value("${sms.message.sender}")
    //"MGsale";
    private String sender;
	
	
    public  boolean  sendMsg(String mobileNo,String content) throws Exception {

        String basicAuthen = new String(Base64.encodeBase64((username + ":" + password).getBytes()));

        CloseableHttpClient httpClient = HttpClients.createDefault();
//        SmsResultBean smsResult = null;
//            LOGGER.debug("================"+url+":"+username+":"+password+""+shortCode+":"+sender);
        HttpPost httpPost = new HttpPost(url);
        // Add Header
        httpPost.setHeader("Content-Type", "text/xml");
        httpPost.setHeader("Authorization", "Basic " + basicAuthen);
        httpPost.setHeader("Charset", "UTF-8");
        
        // Add Entity
        String xml = getSmsMtXmlTag(username, shortCode, formatMobile(mobileNo), StringEscapeUtils.escapeXml(content), sender);
        
        System.out.println("SMSUtils.sendSMS.requestXML: " + xml);
       
        StringEntity entity = new StringEntity(xml, ContentType.create("text/xml", Consts.UTF_8));
        httpPost.setEntity(entity);

        final HttpResponse response = httpClient.execute(httpPost);
        String responseXml = EntityUtils.toString(response.getEntity());
        
        System.out.println("SMSUtils.sendSMS.responseXML: " + responseXml);
        
//        smsResult = getSmsResultFromXml(responseXml);
        EntityUtils.consume(response.getEntity());
        httpClient.close();
            
        return true;
    }
	

    public boolean validMobileNo(String mobileNo) {
    	if(mobileNo!=null && mobileNo.length()==11){
    		return true;
    	}
    	return false;
	}
		
    public String formatMobile(String mobileNo) {
    	if(mobileNo!=null && mobileNo.length()>0){
	   		mobileNo = "66"+mobileNo.substring(1).replaceAll("-", "");
	   	}
	    return mobileNo;
	}
			
   
	
	 private String getSmsMtXmlTag(String serviceId, String shortCode, String mobileNo, String content, String sender) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+07:00'");
	        return
	                "<message>" +
	                        "<sms type=\"mt\">" +
	                        "<service-id>" + serviceId + "</service-id>" +
	                        "<destination>" +
	                        "<address>" +
	                        "<number type=\"international\">" + mobileNo + "</number>" +
	                        "</address>" +
	                        "</destination>" +
	                        "<source>" +
	                        "<address>" +
	                        "<number type=\"abbreviated\">" + shortCode + "</number>" +
	                        "<sender>" + sender + "</sender>" +
	                        "</address>" +
	                        "</source>" +
	                        "<ud type=\"text\" encoding=\"unicode\">" + content + "</ud>" +
	                        "<scts>" + sdf.format(new Date()) + "</scts>" +
	                        "<dro>true</dro>" +
	                        "</sms>" +
	                        "</message>";
	    }
	 
	  private SmsResultBean getSmsResultFromXml(String xml) throws Exception {

	        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = domFactory.newDocumentBuilder();
	        Document document = builder.parse(new InputSource(new StringReader(xml)));

	        XPath xPath = XPathFactory.newInstance().newXPath();
	        Node codeNode = (Node) xPath.evaluate("/message/rsr/rsr_detail/code", document, XPathConstants.NODE);
	        Node descNode = (Node) xPath.evaluate("/message/rsr/rsr_detail/description", document, XPathConstants.NODE);

	        SmsResultBean smsResult = new SmsResultBean();
	        smsResult.setCode(codeNode.getTextContent());
	        smsResult.setDescription(descNode.getTextContent());

	        return smsResult;
	    }

	  
}
