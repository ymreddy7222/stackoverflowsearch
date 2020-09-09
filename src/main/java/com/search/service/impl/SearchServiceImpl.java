/**
 * 
 */
package com.search.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.bean.SearchResponse;
import com.search.service.SearchService;

/**
 * @author Madhav Reddy
 *
 */
@SuppressWarnings("deprecation")
public class SearchServiceImpl implements SearchService {

	/* (non-Javadoc)
	 * @see com.search.service.SearchService#search(java.lang.String)
	 */
	public SearchResponse search(String query) {
		
		//Logger logger = LoggerFactory.getLogger(SearchService.class);
		SearchResponse searchResponse = new SearchResponse();
		CloseableHttpClient httpclient = new DefaultHttpClient();
		
		try {
		    // Add your data
			String encode = URLEncoder.encode(query, "UTF-8");
			HttpGet httpGet = new HttpGet("https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle="+encode+"&site=stackoverflow");
		    HttpResponse response = httpclient.execute(httpGet);
		    
		    HttpEntity entity = response.getEntity();
		    Header contentEncodingHeader = entity.getContentEncoding();
		    
		    if (contentEncodingHeader != null) {
		        HeaderElement[] encodings =contentEncodingHeader.getElements();
		        for (int i = 0; i < encodings.length; i++) {
		            if (encodings[i].getName().equalsIgnoreCase("gzip")) {
		                entity = new GzipDecompressingEntity(entity);
		                break;
		            }
		        }
		    }

		    String output = EntityUtils.toString(entity, Charset.forName("UTF-8").name());
		    
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(
		    	    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    searchResponse = mapper.readValue(output, SearchResponse.class);
		    searchResponse.setStatus(response.getStatusLine().getStatusCode());
		 //   searchResponse.setMessage(response.getStatusLine().get);
		} catch (ClientProtocolException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		   	e.printStackTrace();
		} finally {
			
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return searchResponse;
	}

}
