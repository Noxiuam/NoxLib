package me.noxiuam.noxlib.fun.games.listener;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.fun.games.Game;
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
        Game game = NoxLib.getInstance().getGameFramework().runningGames.get(event.getMember().getIdLong());
        Message msg = NoxLib.getInstance().getMessageCache().get(event.getMessageIdLong()).getMessage();

        if (msg.getContentRaw().equalsIgnoreCase("Want to play again?"))
        {
            if (event.getReaction().getReactionEmote().getAsReactionCode().equalsIgnoreCase("✅"))
            {
                game.init(game.ctx);
            }
            else
            {
                NoxLib.getInstance().getGameFramework().getRunningGames().remove(event.getMember().getIdLong());
                msg.delete().queue();
            }
            return;
        }

        if (!Objects.requireNonNull(event.getMember()).getUser().isBot())
        {
            event.getReaction().removeReaction(Objects.requireNonNull(event.getUser())).queue();
        }

        if (NoxLib.getInstance().getGameFramework().runningGames.containsKey(event.getMember().getIdLong()))
        {
            if (game.getId() == 1)
            {
                NoxLib.getInstance().getGameFramework().getGameById(1).handleGameInput(event.getReactionEmote().getAsReactionCode());
            }
        }
    }
}
