package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VolunteeringDTO {
    @JsonProperty("Volunteering: ")
    private String volunteering;
    @JsonProperty("Start date: ")
    private String startDate;
    @JsonProperty("End date: ")
    private String endDate;

}
