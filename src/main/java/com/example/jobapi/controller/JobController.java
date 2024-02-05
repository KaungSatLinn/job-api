package com.example.jobapi.controller;

import com.example.jobapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JobController extends BaseController{

    @GetMapping("/jobData")
    public ResponseEntity<ApiResponse<AddressResponse>> getJobData(@PathVariable(name = "userId") Long userId,
                                                                   @PathVariable(name = "addressId") Long addressId) {
        logRequestData("Request addresses with userId < " + userId + " > with addressId < " + addressId + " >");

        return okResponse(messages.get("address.retrieve.success"), addressService.getAddress(userId, addressId));
    }
}
