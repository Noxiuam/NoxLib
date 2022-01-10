package me.noxiuam.noxlib.util;

import java.util.Base64;

public class CodecUtil
{
    public String decodeFromBase64(byte[] bytes)
    {
        return new String(Base64.getDecoder().decode(bytes));
    }
    
    public byte[] encodeToBase64(String msg)
    {
        return Base64.getEncoder().encode(msg.getBytes());
    }
}
