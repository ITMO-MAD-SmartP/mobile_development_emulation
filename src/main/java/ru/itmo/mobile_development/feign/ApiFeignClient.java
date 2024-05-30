package ru.itmo.mobile_development.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itmo.mobile_development.entity.GadgetEntity;
import ru.itmo.mobile_development.enums.Action;
import ru.itmo.mobile_development.enums.GadgetType;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@FeignClient(name = "api", url = "${api.url}")
public interface ApiFeignClient {

    @PostMapping(value = "/changeGadgetState", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> changeGadgetState(@RequestHeader(value = "session") String session,
                                           @RequestHeader(value = "homeId") Integer homeId,
                                           @RequestHeader(value = "gadgetId") Integer gadgetId,
                                           @RequestHeader(value = "gadgetType") GadgetType gadgetType,
                                           @RequestHeader(value = "action") Action action,
                                           @RequestHeader(value = "value", required = false) String value);

    @GetMapping(value = "/getGadgetState", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<GadgetEntity> getGadgetState(@RequestHeader(value = "session") String session,
                                                @RequestHeader(value = "gadgetId") Integer gadgetId);

}
