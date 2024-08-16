package com.apiexample_1.service;

import com.apiexample_1.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    RegistrationDto createRegistration( RegistrationDto registrationDto);


    void deletedRegistrationById(long id);

    RegistrationDto updateRegistrationById(Long id, RegistrationDto registrationDto);

    List<RegistrationDto> getAllRegistration(int pageNo, int pageSize);
}
