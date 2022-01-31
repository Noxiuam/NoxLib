package me.noxiuam.noxlib.flow.games.data;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class GlassBridgeData
{
    private long memberId;
    private int stagesLeft;
    private boolean willBreak;
    private String gameBoard;
}
