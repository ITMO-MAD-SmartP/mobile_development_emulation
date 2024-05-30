package ru.itmo.mobile_development.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.mobile_development.enums.Action;
import ru.itmo.mobile_development.enums.GadgetType;

@Data
@AllArgsConstructor
public class ChangeGadgetStateRequest {

    @JsonProperty("SESSION")
    private String session;
    @JsonProperty("HOME_ID")
    private Integer homeId;
    @JsonProperty("GADGET_ID")
    private Integer gadgetId;
    @JsonProperty("GADGET_TYPE")
    private GadgetType gadgetType;
    @JsonProperty("ACTION")
    private Action action;
    @JsonProperty("VALUE")
    private String value;



}
