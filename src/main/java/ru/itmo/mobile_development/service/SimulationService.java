package ru.itmo.mobile_development.service;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itmo.mobile_development.enums.Action;
import ru.itmo.mobile_development.enums.GadgetType;
import ru.itmo.mobile_development.feign.ApiFeignClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulationService {


    private final ApiFeignClient feignClient;

    @Value("${simulation.amount-of-active-users}")
    private Integer amountOfActiveUsers;

    @Scheduled(fixedRateString = "${simulation.send-post-request-every-n-milliseconds}")
    public void simulateChangeStateRequest() {
        var gadgetType = generateRandomGadgetType();
        var actionType = generateRandomActionTypeForGadget(gadgetType);
        var gadgetId = generateRandomNonNegativeNumberUpTo(amountOfActiveUsers * 4);
        var homeId = generateRandomNonNegativeNumberUpTo(amountOfActiveUsers);
        var session = "session_for_home_" + homeId;
        String lightLevel = null;

        if (gadgetType.equals(GadgetType.LAMP) && actionType.equals(Action.CHANGE_LIGHT_LEVEL))
            lightLevel = generateRandomNonNegativeNumberUpTo(100).toString();


        log.info("sending change state request with params session {}, gadgetId {}, gadgetType {}, action {}, lightLevel {}",
                session, gadgetId, gadgetType, actionType, lightLevel);
        feignClient.changeGadgetState(session, homeId, gadgetId, gadgetType, actionType, lightLevel);
    }

    @Scheduled(fixedRateString = "${simulation.send-get-request-every-n-milliseconds}")
    public void simulateGetStateRequest() {
        var gadgetId = generateRandomNonNegativeNumberUpTo(amountOfActiveUsers * 4);
        var homeId = generateRandomNonNegativeNumberUpTo(amountOfActiveUsers);
        var session = "session_for_home_" + homeId;


        log.info("sending get state request with params session {}, gadgetId {}",
                session, gadgetId);
        var state = feignClient.getGadgetState(session, gadgetId);
        log.info("got response {}", state.getBody());
    }


    private String generateRandomRgb(){
        var red = generateRandomNonNegativeNumberUpTo(256);
        var green = generateRandomNonNegativeNumberUpTo(256);
        var blue = generateRandomNonNegativeNumberUpTo(256);
        return "#" + Integer.toHexString(red)
                + Integer.toHexString(green) + Integer.toHexString(blue);
    }

    private Integer generateRandomNonNegativeNumberUpTo(Integer maxValue){
        var random = new Random();
        return random.nextInt(maxValue);
    }

    private GadgetType generateRandomGadgetType(){
        var values = GadgetType.values();
        var index = generateRandomNonNegativeNumberUpTo(values.length);
        return values[index];
    }

    private Action generateRandomActionTypeForGadget(GadgetType gadgetType){
        if (!gadgetType.equals(GadgetType.LAMP)){
            var randomValue = generateRandomNonNegativeNumberUpTo(2);
            if (randomValue == 0)
                return Action.SWITCH_OFF;
            return Action.SWITCH_ON;
        }

        var values = Action.values();
        var index = generateRandomNonNegativeNumberUpTo(values.length);
        return values[index];
    }





    private static Long calculateRateForSettings(Float amountOfActiveUsers,
                                                    Float requestsPerDayPerUser){
        float requestsPerMillisecondPerUser = requestsPerDayPerUser / 24 / 60 / 60 / 1000;
        float requestsPerMillisecondForAllUsers = (requestsPerMillisecondPerUser * amountOfActiveUsers);
        float oneRequestEveryNMilliseconds = 1/requestsPerMillisecondForAllUsers;
        return  ((long) oneRequestEveryNMilliseconds);
    }
// ((long) (1 / (requestsPerDayPerUser / 24 / 60 / 60 / 1000) * amountOfActiveUsers)))
    //for values of 10000 users and 20 per day one request will be sent every 500 milliseconds
    //

}
