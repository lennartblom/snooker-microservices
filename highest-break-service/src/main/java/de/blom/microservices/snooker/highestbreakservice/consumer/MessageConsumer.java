package de.blom.microservices.snooker.highestbreakservice.consumer;

import de.blom.microservices.snooker.highestbreakservice.domain.events.BreakFinishedEvent;
import de.blom.microservices.snooker.highestbreakservice.services.HighestBreakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageConsumer {

    private HighestBreakService highestBreakService;

    @Autowired
    public MessageConsumer(HighestBreakService highestBreakService){
        this.highestBreakService = highestBreakService;
    }

    @RabbitListener(queues = "snooker.finished-breaks.highestbreakservice")
    public void consumeNewFinishedBreak(BreakFinishedEvent breakFinishedEvent){
        log.info("New finished Break with " + breakFinishedEvent.getBreakValue() + " points");

        this.highestBreakService.addNewBreak(breakFinishedEvent);
    }
}
