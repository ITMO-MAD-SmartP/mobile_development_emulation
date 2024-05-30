package ru.itmo.mobile_development.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.mobile_development.enums.Action;
import ru.itmo.mobile_development.enums.GadgetType;

@RestController
@Slf4j
@RequestMapping("")
@RequiredArgsConstructor
public class ApiController {


    //@PostMapping(value = "/mobile")
    public ResponseEntity<Void> runningIssueList(@RequestHeader(value = "session") String session,
                                                 @RequestHeader(value = "gadgetId") String gadgetId,
                                                 @RequestHeader(value = "gadgetType") GadgetType gadgetType,
                                                 @RequestHeader(value = "action") Action action,
                                                 @RequestHeader(value = "rgbValue", required = false) String rgbValue) {
        log.info("got new request with params session {}, gadgetId {}, gadgetType {}, action {}, rgbValue {}",
                session, gadgetId, gadgetType, action, rgbValue);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
