package com.example.demo.Controllers;

import com.example.demo.DTO.VolunteeringDTO;
import com.example.demo.Models.Volunteering;
import com.example.demo.Services.VolunteeringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Services.DataService;
import com.example.demo.Util.JwtUtil;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/volunteering")
public class VolunteeringController {
    private final VolunteeringService volunteeringService;

    private final DataService dataService;

    private final JwtUtil jwtUtil;

    public VolunteeringController(VolunteeringService volunteeringService, DataService dataService, JwtUtil jwtUtil) {
        this.volunteeringService = volunteeringService;
        this.dataService = dataService;
        this.jwtUtil = jwtUtil;
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Volunteering> createVolunteering(@RequestBody Volunteering volunteer, @RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.replace("Bearer ", ""));
        long userId = dataService.getUserId(username).getId();
        volunteer.setUserId(userId);
        System.out.println(" i was here ");
        Volunteering savedVolunteering = volunteeringService.saveVolunteering(volunteer);
        System.out.println(" I saved here ");
        return new ResponseEntity<>(savedVolunteering, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/{seminarId}")
    public ResponseEntity<List<VolunteeringDTO>> getVolunteeringById(@PathVariable Long id, @PathVariable Long seminarId){
        List<VolunteeringDTO> volunteerings = volunteeringService.getVolunteeringById(id,seminarId);
        return new ResponseEntity<>(volunteerings, HttpStatus.OK);
    }
}
