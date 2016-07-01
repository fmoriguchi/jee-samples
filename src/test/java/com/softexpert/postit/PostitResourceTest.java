/**
 * 
 */
package com.softexpert.postit;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softexpert.postit.interfaces.rest.PostitResource;

/**
 * @author fabio.moriguchi
 *
 */
@RunWith(Arquillian.class)
public class PostitResourceTest {
	
	private static final String URL_API = "http://127.0.0.1:8080/jee-samples-0.0.1-SNAPSHOT/api/";
	
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(PostitResource.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	

	@Test
	public void listAllPostisTest() {
		
		Response response = ClientBuilder.newClient()
										 .target(URL_API)
										 .path("postits")
										 .request(MediaType.APPLICATION_JSON)
										 .buildGet()
										 .invoke();
		
		Assert.assertEquals(200, response.getStatus());
		
		response.close();
	}

}
