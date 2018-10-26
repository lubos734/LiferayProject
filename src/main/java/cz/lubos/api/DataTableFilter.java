package cz.lubos.api;

import cz.sonet.utils.jquery.datatable.TableRequestConditions;

/**
 * Base filter object for datatable 
 */
public class DataTableFilter {

	protected int start;
	
	protected int count;
	
	protected String orderByColumn;
	
	protected String order;

	public DataTableFilter() {

	}
	
	public DataTableFilter(TableRequestConditions conditions) {
		start = conditions.getStart();
		count = conditions.getCount();
		order = conditions.getOrder();
		orderByColumn = conditions.getOrderByColumn();
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
