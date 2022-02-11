package me.noxiuam.noxlib.flow.games.data;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class FlagGuesserData
{
    private long memberId;
    private int stagesLeft;
    private String gameBoard;
}
