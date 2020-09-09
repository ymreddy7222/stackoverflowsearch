/**
 * 
 */
package com.search.bean;

/**
 * @author Madhav Reddy
 *
 */
public class SearchResult {

	private String title;
	private String link;
	private Owner owner;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "SearchResult [title=" + title + ", link=" + link + ", owner=" + owner + "]";
	}

}
