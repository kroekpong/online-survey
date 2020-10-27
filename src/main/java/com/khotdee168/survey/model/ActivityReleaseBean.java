package com.khotdee168.survey.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.khotdee168.common.model.BaseDomain;

public class ActivityReleaseBean extends BaseDomain{

	private String id;
	private String act_id;
	private String set_id;
	private String uid;
	private String sid;
	private String channel;
	private String open_flg;
	private String release_flg ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAct_id() {
		return act_id;
	}

	public void setAct_id(String act_id) {
		this.act_id = act_id;
	}

	public String getSet_id() {
		return set_id;
	}

	public void setSet_id(String set_id) {
		this.set_id = set_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOpen_flg() {
		return open_flg;
	}

	public void setOpen_flg(String open_flg) {
		this.open_flg = open_flg;
	}

	public String getRelease_flg() {
		return release_flg;
	}

	public void setRelease_flg(String release_flg) {
		this.release_flg = release_flg;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}