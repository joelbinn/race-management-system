package se.cag.labs.currentrace.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.cag.labs.currentrace.apicontroller.mapper.ModelMapper;
import se.cag.labs.currentrace.services.repository.datamodel.CurrentRaceStatus;

@Component
@Log
public class CallbackService {
    private RestTemplate restTemplate = new RestTemplate();

    public void reportStatus(CurrentRaceStatus status) {
        log.fine("Report status:" + status);
        try {
            restTemplate.postForLocation(status.getCallbackUrl(), ModelMapper.createStatusResponse(status));
        } catch (RestClientException e) {
            log.warning("Rest call failed: " + e.getLocalizedMessage());
        }
    }
}
