package ru.itmo.mobile_development.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetGadgetStatusRequest {


    @JsonProperty("SESSION")
    private String session;

    @JsonProperty("GADGET_ID")
    private Integer gadgetId;


}
