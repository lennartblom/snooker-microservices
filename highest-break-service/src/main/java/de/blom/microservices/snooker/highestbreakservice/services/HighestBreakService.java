package de.blom.microservices.snooker.highestbreakservice.services;

import de.blom.microservices.snooker.highestbreakservice.domain.events.BreakFinishedEvent;
import de.blom.microservices.snooker.highestbreakservice.domain.models.Break;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HighestBreakService {
    private List<Break> breaks;

    public HighestBreakService(){
        this.breaks = new ArrayList<>();
    }

    public void addNewBreak(BreakFinishedEvent breakFinishedEvent){
        this.breaks.add(new Break(breakFinishedEvent.getPlayerId(), breakFinishedEvent.getBreakValue(), breakFinishedEvent.getPottedBalls()));
    }

    public List<Break> getBreaks() {
        return breaks;
    }

    public Break getHighestBreak() {
        return this.breaks.stream().max(Comparator.comparing(Break::getBreakValue)).get();
    }

    public List<Break> getTop5Breaks() {
        // Todo does not work yet... to be implemented
        List<Break> breaks = this.breaks.stream().sorted(Comparator.comparing(Break::getBreakValue)).collect(Collectors.toList());

        List<Break> top5Breaks = breaks.subList(0, 4);
        return top5Breaks;
    }
}
