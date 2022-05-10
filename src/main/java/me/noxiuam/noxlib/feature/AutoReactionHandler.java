package me.noxiuam.noxlib.feature;

import lombok.Getter;
import me.noxiuam.noxlib.feature.data.AutoReaction;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AutoReactionHandler {
    public List<AutoReaction> autoReactions = new ArrayList<>();

    public void register(String target, String... emotes) {
        this.autoReactions.add(new AutoReaction(target, emotes));
    }

    public AutoReaction getAutoReaction(String trigger) {
        return this.autoReactions.stream().filter(autoResponse -> autoResponse.getTrigger().equalsIgnoreCase(trigger)).findFirst().orElse(null);
    }
}
