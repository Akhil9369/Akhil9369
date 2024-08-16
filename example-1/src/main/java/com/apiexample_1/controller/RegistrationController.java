package com.apiexample_1.controller;


import com.apiexample_1.entity.Registration;
import com.apiexample_1.payload.RegistrationDto;
import com.apiexample_1.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")
public  class RegistrationController {


    private RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<RegistrationDto> addRegistration(@RequestBody RegistrationDto registationDto) {


        RegistrationDto regDto = registrationService.createRegistration(registationDto);

        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public  ResponseEntity<String> deleteRegistrationById(@RequestParam long id){
        registrationService.deletedRegistrationById(id);
        return new ResponseEntity<>("Registration deleted successfully", HttpStatus.OK);

    }
    //http://localhost:8080/api/v1/registration?id=
    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistration(Long id,@RequestBody RegistrationDto registrationDto){

        RegistrationDto regDto=registrationService.updateRegistrationById(id,registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.OK);

    }

    //http://localhost:8080/api/v1/registration
    @GetMapping
    public <name, defaultValue> ResponseEntity<List<RegistrationDto>> getAllRegistration(
            @RequestParam (name="pageNo", defaultValue="0",required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue="5",required = false) int pageSize
    )
    {
          List<RegistrationDto> dtos=registrationService.getAllRegistration(pageNo,pageSize);
          return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

}
