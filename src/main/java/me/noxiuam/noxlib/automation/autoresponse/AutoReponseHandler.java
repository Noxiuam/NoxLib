package me.noxiuam.noxlib.automation.autoresponse;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AutoReponseHandler
{
    public List<AutoReponseMessage> autoResponses = new ArrayList<>();

    public void register(String trigger, String response)
    {
        this.autoResponses.add(new AutoReponseMessage(trigger, response));
    }

    public void unregister(String trigger)
    {
        this.autoResponses.removeIf(msg -> msg.getTrigger().equals(trigger));
    }
}
