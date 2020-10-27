package com.khotdee168.common.model;

import java.util.ArrayList;
import java.util.List;

public class DataTableAjax {

	private Integer draw = 0;
	private Integer recordsTotal = 0;
	private Integer recordsFiltered = 0;
	private List data = new ArrayList();

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) { 
		if (data != null) {
			this.data = data;
		}
	}

}
