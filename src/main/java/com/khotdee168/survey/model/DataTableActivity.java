package com.khotdee168.survey.model;

import java.util.ArrayList;
import java.util.List;

import com.khotdee168.common.model.DataTableAjax;

public class DataTableActivity extends DataTableAjax{
	
	private String msg = "";
	private List uids = new ArrayList();

	public List getUids() {
		return uids;
	}

	public void setUids(List uids) {
		this.uids = uids;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
