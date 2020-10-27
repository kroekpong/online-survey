package com.khotdee168.common.utils;

import org.apache.commons.lang3.text.WordUtils;

public class GenScriptUtils {

	private static String fnput =  
			"Project"
			+",Phase"
			+",Number"
			+",VIN No."
			+",Qty."
			+",Model"
			+",Vehicle Code"
			+",Valid date BG"
			+",Expiry date BG"
			+",Plate No."
			+",Plate Start"
			
			+",Plate Expired"
			+",Date of start out FZ"
			+",Date of return to FZ"
			+",CMI Start"
			+",CMI Expired"
			+",VMI Start"
			+",VMI Expired"
			+",Build Plan finish"
			+",Status"
			+",Remark";
	
	
	private static String[] fieldArr =  fnput.split(",");
	
	private static void genHTMLInput() {
		
		final char[] delimiters = { '_' };
		
		int i=0;
		for (String el : fieldArr) {
			
//			String fwd = WordUtils.capitalizeFully(el, delimiters);
//			String name = fwd.replace(" ", "_").replace(".", "");
			String name = el.toLowerCase().replace(" ", "_").replace(".", "");
			
//			System.out.println("row.createCell("+(i++)+").setCellValue(bean.get"+name+"());");
//			System.out.println("row.createCell("+(i++)+").setCellValue(bean.get"+name+"());");
			
//			String html = "<div class=\"form-group row\"><label class=\"col-form-label col-sm-4\">"+el+"</label>"+
//					"<div class=\"col-sm-8\"><input type=\"text\" class=\"form-control input\"  id=\""+name+"-e\" name=\""+name+"\" ></div></div>";
			System.out.println("$('#"+name+"-e').val(obj['"+name+"']);");
//			$("#carId-e").val(obj['carId']);
//			 row.createCell(0) .setCellValue(bean.getName());
		}
		

	}
	
	
	
	public static void main(String[] args) {
		genHTMLInput();
	}
}
