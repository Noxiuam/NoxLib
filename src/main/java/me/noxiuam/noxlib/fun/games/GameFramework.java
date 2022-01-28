package me.noxiuam.noxlib.fun.games;

import lombok.Getter;
import me.noxiuam.noxlib.command.CommandContext;
import me.noxiuam.noxlib.fun.games.impl.GlassBridge;
import net.dv8tion.jda.api.entities.Member;

import java.util.*;

@Getter
public class GameFramework
{
    public final List<Game> games = new ArrayList<>();
    public final Map<Long, Game> runningGames = new HashMap<>();

    public GameFramework()
    {
        this.games.add(new GlassBridge());
    }

    public void createMultiplayerGame(int gameId, Member... members)
    {
        // Connect them to our server and handle gameplay there.
    }

    public void startGame(CommandContext ctx, int id)
    {
        this.runningGames.put(ctx.getMember().getIdLong(), this.getGameById(id));
        this.getGameById(id).init(ctx);
    }

    public Game getGameById(int id)
    {
        return this.games.stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }
}
