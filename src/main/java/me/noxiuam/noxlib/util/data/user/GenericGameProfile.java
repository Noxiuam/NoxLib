package me.noxiuam.noxlib.util.data.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericGameProfile {
    private long memberId;
    private int gameId;
    private int stagesLeft;
    private boolean condition;
    private String gameBoard;
}
