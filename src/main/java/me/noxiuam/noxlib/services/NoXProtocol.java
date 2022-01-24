package me.noxiuam.noxlib.services;

import lombok.*;

/*
 * This class will be used later on for the pinging command, I need to fix it.
 */
@Getter @AllArgsConstructor
public abstract class NoXProtocol {

    private String name;
    private String address;
    private int port; // optional

}
