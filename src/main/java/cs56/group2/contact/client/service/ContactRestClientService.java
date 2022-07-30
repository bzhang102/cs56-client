package cs56.group2.contact.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs56.group2.contact.client.fx.ContactFx;
import cs56.group2.contact.client.model.SimpleContact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactRestClientService {

    private static Logger LOGGER = LoggerFactory.getLogger(ContactRestClientService.class);

    private ObjectMapper mapper = new ObjectMapper();


    private CloseableHttpClient httpClient;
    public static String BASE_URL = "http://127.0.0.1:8080/";

    public ContactRestClientService() {
        httpClient = HttpClientBuilder.create().build();
    }

    public List<ContactFx> getAllContacts() throws IOException {
        LOGGER.info("getAllContacts...");
        HttpGet request = new HttpGet(BASE_URL + "contact");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                LOGGER.error("Error getting response from Contact Server, response = {}", response.getStatusLine());

                throw new IOException("Error getting response from Contact Server, response = " + response.getStatusLine());
            }

            List<SimpleContact> simpleContacts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<SimpleContact>>() {
            });

            LOGGER.info("Successfully GET {} contacts from server: {}", simpleContacts.size(), simpleContacts);

            List<ContactFx> contactFxes = new ArrayList<>(simpleContacts.size());
            for (SimpleContact simpleContact : simpleContacts) {
                ContactFx contactFx = new ContactFx(simpleContact);

                contactFxes.add(contactFx);
            }

            return contactFxes;
        }
    }

    public void updateAllContacts(List<ContactFx> contactFxes) throws IOException {
        List<SimpleContact> simpleContacts = new ArrayList<>(contactFxes.size());
        for (ContactFx contactFx : contactFxes) {
            simpleContacts.add(contactFx.getContact());
        }

        String body = mapper.writeValueAsString(simpleContacts);

        LOGGER.info("body to be POST to server: {}", body);

        HttpPost httpPost = new HttpPost(BASE_URL + "contact");
        HttpEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                LOGGER.info("successfully updated server with {} contacts", contactFxes.size());
            } else {
                LOGGER.error("error updating server: result from post: {}", response.getStatusLine());

                throw new IOException("error updating server, error is " + response.getStatusLine());

            }
        }

    }
}
