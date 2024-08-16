package com.apiexample_1.service;


import com.apiexample_1.entity.Registration;
import com.apiexample_1.payload.RegistrationDto;
import com.apiexample_1.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;

    }

    public RegistrationServiceImpl() {


    }

   @Override

    public RegistrationDto createRegistration( RegistrationDto registrationDto){
        Registration registration =mapToEntity(registrationDto);
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(savedEntity);
        dto.setMassage("Record Saved");
        return dto;
    }



    @Override
    public void deletedRegistrationById(long id) {
        registrationRepository.deleteById(id);

    }

    @Override
    public RegistrationDto updateRegistrationById(Long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration = opReg.get();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        Registration updatedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto( updatedEntity);
        dto.setMassage("Record Updated");
        return dto;
    }

    @Override
    public List<RegistrationDto> getAllRegistration(int pageNo, int pageSize) {
        //RegistrationServiceImpl r=new RegistrationServiceImpl();
        //List<Registration> registrations=registrationRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Registration> all=registrationRepository.findAll(pageable);
        List<Registration>registrations =all.getContent();
        //List<RegistrationDto>registrationDtos =registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        List<RegistrationDto>registrationDtos =registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return registrationDtos;
    }

    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration=new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        return registration;
    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto =new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;

    }

}


