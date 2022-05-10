package me.noxiuam.noxlib.util.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlassBridgeData {
    public int currentRound;
    private long memberId;
    private int stagesLeft;
    private boolean goingToBreak;
    private String gameBoard;
}
