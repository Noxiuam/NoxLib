package me.noxiuam.noxlib.feature.games;

import lombok.Getter;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.feature.games.impl.GlassBridge;
import me.noxiuam.noxlib.util.data.user.GenericGameProfile;
import me.noxiuam.noxlib.util.data.user.GlassBridgeData;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GameFramework {
    public final List<Game> registeredGames = new ArrayList<>();
    public final List<String> gamePieces = new ArrayList<>();
    public final List<GenericGameProfile> gameProfiles = new ArrayList<>();
    public final List<GlassBridgeData> bridgeData = new ArrayList<>(); // Had to do a custom data Object for this to work.

    public final Map<Member, Integer> playerList = new HashMap<>(); // Will be used to collect player's data later for commands for users.
    public final Map<Member, Message> runningGames = new HashMap<>();

    public GameFramework() {
        this.registeredGames.add(new GlassBridge());
        this.gamePieces.add("➡");
        this.gamePieces.add("⬅");
    }

    public void startGame(CommandContext ctx, int id) {
        this.getPlayerList().put(ctx.getMember(), id);
        this.getGameById(id).init(ctx, ctx.getMember());
    }

    public Game getGameById(int id) {
        return this.registeredGames.stream().filter(game -> game.getId() == id).findFirst().orElse(null);
    }
}
