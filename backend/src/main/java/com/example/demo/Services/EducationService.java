package com.example.demo.Services;

import com.example.demo.DTO.EducationDTO;
import com.example.demo.DTO.HobbiesDTO;
import com.example.demo.Models.Education;
import com.example.demo.Repository.EducationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }
    public EducationDTO getEducationById(Long id, Long seminarId) {
        ModelMapper modelMapper = new ModelMapper();
        Education education = educationRepository.findByUserIdAndSeminarId(id,seminarId).orElseThrow(() -> new RuntimeException("Education not found with id: " + id));
        return modelMapper.map(education, EducationDTO.class);

    }
    public List<EducationDTO> getEducationListById(Long id,Long seminarId)
    {
        ModelMapper modelMapper = new ModelMapper();
        List<Education> education = educationRepository.findAllByUserIdAndSeminarId(id,seminarId);
        Type listType = new TypeToken<List<EducationDTO>>(){}.getType();
        return modelMapper.map(education, listType);

    }

}
