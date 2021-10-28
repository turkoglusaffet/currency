package com.openpayd.test.accessor;

import com.openpayd.test.accessor.dto.FixerDto;
import com.openpayd.test.exception.Error;
import com.openpayd.test.exception.RestClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FixerAccessor {

    @Value("${fixer.url}")
    private String url;
    private final RestTemplate restTemplate;

    public FixerDto getAllData(){
        try {
            return restTemplate.getForEntity(url,FixerDto.class).getBody();
        }catch (Exception ex){
            throw new RestClientException(Error.REST_CLIENT_EXCEPTION.getErrorCode(),Error.REST_CLIENT_EXCEPTION.getErrorMessage());
        }

    }

}
