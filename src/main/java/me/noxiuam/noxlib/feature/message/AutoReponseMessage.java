package me.noxiuam.noxlib.feature.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutoReponseMessage {
    private String trigger;
    private String response;
}
