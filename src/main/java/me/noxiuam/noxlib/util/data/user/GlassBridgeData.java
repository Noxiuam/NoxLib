package me.noxiuam.noxlib.util.data.user;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class GlassBridgeData
{
    private long memberId;
    private int stagesLeft;
    private boolean willBreak;
    private String gameBoard;
}
