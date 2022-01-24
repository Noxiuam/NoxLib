package me.noxiuam.noxlib.services;

import lombok.*;

import java.net.URL;


@Getter @AllArgsConstructor
public class DoXProtocol {

    private String name;
    private URL address;
    private int port; // optional

}
