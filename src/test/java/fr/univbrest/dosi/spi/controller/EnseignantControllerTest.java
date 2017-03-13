package fr.univbrest.dosi.spi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.Application;
import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.service.EnseignantService;
import fr.univbrest.dosi.spi.service.EvaluationService;

/**
 * @author DOSI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EnseignantControllerTest {

	@Autowired
	EnseignantService business;
	
	@Test
	public void addserviceTest() throws ClientProtocolException, IOException {

		final Enseignant enseignant = new Enseignant();
		enseignant.setNoEnseignant(7);
		enseignant.setNom("LAHNAKI");
		enseignant.setPrenom("Chakib");
		enseignant.setType("INT");
		enseignant.setSexe("H");
		enseignant.setAdresse("cite universitaire de kergoat");
		enseignant.setCodePostal("29200");
		enseignant.setVille("Brest");
		enseignant.setPays("FR");
		enseignant.setMobile("06.67.58.23.68");
		enseignant.setTelephone("06.67.58.23.00");
		enseignant.setEmailPerso("chakib.lahnaki@gmail.com");
		enseignant.setEmailUbo("chakib.lah@gmail.com");
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/enseignant/");
		final ObjectMapper mapper = new ObjectMapper();
		final com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		final String jsonInString = ow.writeValueAsString(enseignant);
		mockRequestPost.addHeader("Content-type", "application/json");
		mockRequestPost.setEntity(new StringEntity(jsonInString));


		final HttpResponse mockResponse = client.execute(mockRequestPost);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
		
		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mappe = new ObjectMapper();
		final Enseignant ens = mappe.readValue(rd, Enseignant.class);
		
		business.deleteEnseignant(ens.getNoEnseignant());
	}

	
	@Test
	public final void getEnseignantTest() throws ClientProtocolException, IOException {
		
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/enseignant/getens/1");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		// Enseignant ens = mapper.readValue(rd, Enseignant.class);

		// Assert.assertNotNull(ens);

	}

	@Test
	public final void listEnseignantTest() throws ClientProtocolException, IOException {
		
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/enseignant/");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<Enseignant> ens = mapper.readValue(rd, Iterable.class);

		Assert.assertNotNull(ens);

	}

	
	
	@Test
	public final void deleteEnseignantTest() throws ClientProtocolException, IOException {
	
		final Enseignant enseignant = new Enseignant();
		enseignant.setNoEnseignant(7);
		enseignant.setNom("LAHNAKI");
		enseignant.setPrenom("Chakib");
		enseignant.setType("INT");
		enseignant.setSexe("H");
		enseignant.setAdresse("cite universitaire de kergoat");
		enseignant.setCodePostal("29200");
		enseignant.setVille("Brest");
		enseignant.setPays("FR");
		enseignant.setMobile("06.67.58.23.68");
		enseignant.setTelephone("06.67.58.23.00");
		enseignant.setEmailPerso("chakib.lahnaki@gmail.com");
		enseignant.setEmailUbo("chakib.lah@gmail.com");
		
		Enseignant temp= business.addEnseignant(enseignant);
		
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpDelete mockRequest = new HttpDelete("http://localhost:8090/enseignant/"+temp.getNoEnseignant());
		final HttpResponse mockResponse = client.execute(mockRequest);
	
		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());
	
	
	}
}
