package com.example.demo.Controllers;

import com.example.demo.DTO.PersonalDetailsDTO;
import com.example.demo.Models.PersonalDetails;
import com.example.demo.Models.Seminars;
import com.example.demo.Services.PersonalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Services.DataService;
import com.example.demo.Util.JwtUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/personalDetails")
public class PersonalDetailsController {
    private final PersonalDetailsService personalDetailsService;

    private final  DataService dataService;

    private final JwtUtil jwtUtil;

    public PersonalDetailsController(PersonalDetailsService personalDetailsService, DataService dataService, JwtUtil jwtUtil) {
        this.personalDetailsService = personalDetailsService;
        this.dataService = dataService;
        this.jwtUtil = jwtUtil;
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PersonalDetails> createPersonalDetails(@RequestBody PersonalDetails personalDetails, @RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        personalDetails.setUserId(userId);
        PersonalDetails savedPersonalDetails = personalDetailsService.savePersonalDetails(personalDetails);
        return new ResponseEntity<>(savedPersonalDetails, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PersonalDetails>> getPersonalDetails(){
        List<PersonalDetails> personalDetails = personalDetailsService.getPersonalDetails();
        return new ResponseEntity<>(personalDetails, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/{seminarId}")
    public ResponseEntity<PersonalDetailsDTO> getPersonalDetailsById(@PathVariable Long id,@PathVariable Long seminarId){
        PersonalDetailsDTO personalDetails = personalDetailsService.getPersonalDetailsById(id,seminarId);
        return new ResponseEntity<>(personalDetails, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/seminarcount")
    public Map<Integer,Long> getSeminarCounts(){
        return personalDetailsService.getSeminarCounts();
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/personalDetailsList/submit")
    public ResponseEntity<PersonalDetailsDTO> getPersonalDetailsById(@RequestHeader("Authorization") String token,@RequestParam Long seminarId){
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        PersonalDetailsDTO personalDetails = personalDetailsService.getPersonalDetailsById(userId,seminarId);
        return new ResponseEntity<>(personalDetails, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePersonalDetails(@RequestHeader("Authorization") String token,@RequestParam Long seminarId){
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        personalDetailsService.deletePersonalDetails(userId,seminarId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //get seminarId based on userId
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/seminarId")
    public ResponseEntity<List<Long>> getSeminarIdByUserId(@RequestHeader("Authorization") String token){
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        System.out.println(userId);
        List<Long> seminarId= personalDetailsService.getSeminarIdByUserId(userId);
//        output all seminars
        System.out.println("I amhere");
        for (Long id: seminarId){
            System.out.println("SEMINARS ARE: "+id);
        }
        return new ResponseEntity<>(seminarId, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update")
    public void updatePersonalDetails(@RequestHeader("Authorization") String token,@RequestParam long seminarId){
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
       PersonalDetails personalDetails =personalDetailsService.givePersonalDetails(userId,seminarId);
         personalDetailsService.updatePersonalDetails(personalDetails);




    }
}
