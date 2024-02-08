package com.example.demo.Controllers;
import com.example.demo.DTO.EducationDTO;
import com.example.demo.Services.DataService;
import com.example.demo.Models.Education;
import com.example.demo.Services.EducationService;
import com.example.demo.Util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/education")
public class EducationController {
    private final EducationService educationService;

    private final DataService dataService;

    private final JwtUtil jwtUtil;

    public EducationController(EducationService educationService, DataService dataService, JwtUtil jwtUtil) {
        this.educationService = educationService;
        this.dataService = dataService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education,@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        education.setUserId(userId);
        Education savedEducation = educationService.saveEducation(education);
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/{seminarId}")
    public ResponseEntity <List<EducationDTO>> getEducationById(@PathVariable Long id, @PathVariable Long seminarId){
        List<EducationDTO> education = educationService.getEducationListById(id,seminarId);
        System.out.println(new ResponseEntity<>(education, HttpStatus.OK));
        return new ResponseEntity<>(education, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/educationcount")
    public Map<String, Map<String, Integer>>getEducationCountsPerSeminar(){
        return educationService.getEducationCountsPerSeminar();
    }
}
