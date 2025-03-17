package com.ms.CF_DD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Controller class created for Cogniflow workflow
 *
 * @author ZK4CVD2
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class ProcessController {

    private static final String EXCEPTION = "exception";

    @Autowired
    private ExternalApiService externalApiService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * Wrapper API to call external API
     *
     * @param loanRequestDto Input payload containing loanNumber
     * @return Response from external API
     */
    @PostMapping("/ms-wrapper")
    public ResponseEntity<Object> callExternalApi(@RequestBody LoanRequestDto loanRequestDto) {
        log.info("Request received to call external API with loanNumber: {}", loanRequestDto.);
        try {
            ResponseEntity<Object> responseEntity = externalApiService.callExternalApi(loanRequestDto);
            return responseEntity;
        } catch (Exception ex) {
            log.error("Exception occurred while calling external API", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error calling external API: " + ex.getMessage());
        }
    }

    /**
     * Simple endpoint to return a greeting message with the server port
     *
     * @return Greeting message
     */
    @GetMapping("/payment/say")
    public String getMethodName() {
        return "HII I am from CF-DD MS running on port: " + serverPort;
    }

    // Add other existing endpoints here
}
