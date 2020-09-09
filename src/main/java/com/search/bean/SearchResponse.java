/**
 * 
 */
package com.search.bean;

import java.util.List;

/**
 * @author Madhav Reddy
 *
 */
public class SearchResponse {

	private List<SearchResult> items;
	
	private int status;
	private String message;

	public List<SearchResult> getItems() {
		return items;
	}

	public void setItems(List<SearchResult> items) {
		this.items = items;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SearchResponse [items=" + items + ", status=" + status + ", message=" + message + "]";
	}

}
