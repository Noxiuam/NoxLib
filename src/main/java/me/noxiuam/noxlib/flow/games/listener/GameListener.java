package me.noxiuam.noxlib.flow.games.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.flow.games.Game;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameListener extends ListenerAdapter
{
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event)
    {
        Message gameMessage = NoxLib.getInstance().getGameFramework().getRunningGames().get(event.getMember());
        Game game = NoxLib.getInstance().getGameFramework().getGameById(1);

        if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
        {
            event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
        }

        if (NoxLib.getInstance().getGameFramework().getRunningGames().containsKey(event.getMember()))
        {
            if (gameMessage != null)
            {
                game.handleGameInput(event.getReactionEmote().getAsReactionCode(), event.getMember());
            }
        }
    }
}
