package com.example.demo.Services;

import com.example.demo.DTO.PersonalDetailsDTO;
import com.example.demo.DTO.SeminarCountDTO;
import com.example.demo.Models.PersonalDetails;
import com.example.demo.Repository.PersonalDetailsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalDetailsService {

    private final PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    public PersonalDetailsService(PersonalDetailsRepository personalDetailsRepository) {
        this.personalDetailsRepository = personalDetailsRepository;
    }

    public PersonalDetails savePersonalDetails(PersonalDetails personalDetails) {
        return personalDetailsRepository.save(personalDetails);
    }
    public List<PersonalDetails> getPersonalDetails(){
        return personalDetailsRepository.findAll();
    }
    public PersonalDetailsDTO getPersonalDetailsById(Long id, Long seminarId) {
        ModelMapper modelMapper = new ModelMapper();
        PersonalDetails personalDetails = personalDetailsRepository.findByUserIdAndSeminarId(id,seminarId).orElseThrow(() -> new RuntimeException("PersonalDetails not found"));
        return modelMapper.map(personalDetails, PersonalDetailsDTO.class);
    }
    public List<PersonalDetailsDTO> getPersonalDetailsListById(Long id, Long seminarId)
    {
        ModelMapper modelMapper = new ModelMapper();
        List<PersonalDetails> personalDetails = personalDetailsRepository.findAllByUserIdAndSeminarId(id,seminarId);
        Type listType = new TypeToken<List<PersonalDetailsDTO>>(){}.getType();
        return modelMapper.map(personalDetails, listType);
    }
    public Map<Integer, Long> getSeminarCounts() {
        Map<Integer, Long> seminarCounts = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            seminarCounts.put(i, personalDetailsRepository.countBySeminarId((long)i));
        }
        return seminarCounts;
    }




}
