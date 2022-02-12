package me.noxiuam.noxlib.event;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.feature.games.Game;
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
        if (!NoxLib.getInstance().getGameFramework().getPlayerList().containsKey(event.getMember()) || !NoxLib.getInstance().getGameFramework().getGamePieces().contains(event.getReactionEmote().getAsReactionCode())) return;

        int gameId = NoxLib.getInstance().getGameFramework().getPlayerList().get(event.getMember());
        Message gameMessage = NoxLib.getInstance().getGameFramework().getRunningGames().get(event.getMember());
        Game game = NoxLib.getInstance().getGameFramework().getGameById(gameId);

        if (!Objects.requireNonNull(event.getMember()).getUser().isBot() && gameMessage != null)
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
