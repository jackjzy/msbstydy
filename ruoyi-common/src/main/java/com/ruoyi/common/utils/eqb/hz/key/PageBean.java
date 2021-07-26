package com.ruoyi.common.utils.eqb.hz.key;

import java.util.List;

public class PageBean implements Comparable<PageBean> {
	private int pageIndex;
	private List<PosBean> coordinateList;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<PosBean> getCoordinateList() {
		return coordinateList;
	}
	public void setCoordinateList(List<PosBean> coordinateList) {
		this.coordinateList = coordinateList;
	}
	@Override
	public int compareTo(PageBean o) {
		if(this.pageIndex > o.getPageIndex())
			return 1;
		if(this.pageIndex < o.getPageIndex())
			return -1;
		return 0;
	}
	
	
}
