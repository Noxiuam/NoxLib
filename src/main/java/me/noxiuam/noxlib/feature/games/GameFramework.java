package me.noxiuam.noxlib.feature.games;

import lombok.Getter;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.util.data.user.GenericGameProfile;
import me.noxiuam.noxlib.util.data.user.GlassBridgeData;
import me.noxiuam.noxlib.feature.games.impl.GlassBridge;
import net.dv8tion.jda.api.entities.*;

import java.util.*;

@Getter
public class GameFramework
{
    public final List<Game> registeredGames = new ArrayList<>();
    public final List<String> gamePieces = new ArrayList<>();
    public final List<GenericGameProfile> gameProfiles = new ArrayList<>();
    public final List<GlassBridgeData> bridgeData = new ArrayList<>(); // Had to do a custom data class for this to work

    public final Map<Member, Integer> playerList = new HashMap<>(); // Will be used to collect playerdata later for commands for users.
    public final Map<Member, Message> runningGames = new HashMap<>();

    public GameFramework()
    {
        this.registeredGames.add(new GlassBridge());
        this.gamePieces.add("➡");
        this.gamePieces.add("⬅");
        System.out.println("[NoxLib] Created Game Framework!");
    }

    public void startGame(CommandContext ctx, int id)
    {
        this.getPlayerList().put(ctx.getMember(), id);
        this.getGameById(id).init(ctx, ctx.getMember());
    }

    public Game getGameById(int id)
    {
        return this.registeredGames.stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }
}
