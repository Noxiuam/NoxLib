package me.noxiuam.noxlib.feature.message;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AutoResponseHandler {
    private final List<AutoReponseMessage> autoResponses = new ArrayList<>();

    public void register(String trigger, String response) {
        AutoReponseMessage msg = new AutoReponseMessage(trigger, response);
        this.autoResponses.add(msg);
    }

    public void unregister(String trigger) {
        this.autoResponses.removeIf(msg -> msg.getTrigger().equals(trigger));
    }

    public AutoReponseMessage getAutoResponse(String trigger) {
        return this.autoResponses.stream().filter(autoResponse -> autoResponse.getTrigger().equalsIgnoreCase(trigger)).findFirst().orElse(null);
    }
}
