package com.cm.bbuserapi.client;

import com.cm.bbuserapi.dto.randomData.RandomUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "random-api", url ="https://randomuser.me/api")
public interface RandomUserClient {
    @GetMapping()
    RandomUserResponse getUser();
}
