package me.noxiuam.noxlib.feature.games;

import lombok.Getter;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.media.ImageDatabase;
import me.noxiuam.noxlib.util.custom.MessageUtil;
import net.dv8tion.jda.api.entities.Member;

@Getter
public abstract class Game {
    private final String name;
    private final int id;
    private final String[] acceptedPieces;

    public CommandContext ctx;

    public MessageUtil msg = new MessageUtil();
    public ImageDatabase imageDatabase = new ImageDatabase();

    public Game(String name, int id, String... acceptedPieces) {
        this.name = name;
        this.id = id;
        this.acceptedPieces = acceptedPieces;
    }

    public abstract void init(CommandContext ctx, Member member);

    public abstract void run(CommandContext ctx, Member member);

    public abstract void handleGameInput(String unicode, Member member);

    public void endGame(Member member) {
        NoxLib.getInstance().getGameFramework().getGameProfiles().remove(NoxLib.getInstance().getGameFramework().getGameProfiles().stream().filter(game -> game.getMemberId() == member.getIdLong()).findFirst().orElse(null));
        NoxLib.getInstance().getGameFramework().getBridgeData().remove(NoxLib.getInstance().getGameFramework().getBridgeData().stream().filter(game -> game.getMemberId() == member.getIdLong()).findFirst().orElse(null));
        NoxLib.getInstance().getGameFramework().getRunningGames().remove(member);
        NoxLib.getInstance().getGameFramework().getPlayerList().remove(member);
    }
}
