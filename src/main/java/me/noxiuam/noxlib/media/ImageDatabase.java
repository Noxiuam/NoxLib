package me.noxiuam.noxlib.media;

import lombok.*;

@Setter @Getter
public class ImageDatabase {
    public ImageHelper helper = new ImageHelper();
    public String defaultImage = "https://i.imgur.com/3CWMDif.gif"/*https://officialpsds.com/imageview/7v/90/7v90vz_large.png"*/;
    public String toolImage = "https://www.freeiconspng.com/uploads/tool-icon-12.png";
    public String errorImage = "https://www.pinclipart.com/picdir/big/548-5481377_danger-warning-sign-png-clipart-high-voltage-sign.png";
    public String gameImage = "https://th.bing.com/th/id/R.b535968533571adfe1564f3fb05c8a0e?rik=dLH7i2GG2K5mWA&riu=http%3a%2f%2fwww.pngall.com%2fwp-content%2fuploads%2f5%2fBlack-Game-Controller-Transparent.png&ehk=CRjkAJyV6bt9zTOfHwpFTX9q7bhQyfwZLIfWU4Xy0Es%3d&risl=&pid=ImgRaw&r=0";

    public ImageDatabase()
    {
        System.out.println("[NoxLib] Created Image Database!");
    }

    public void setupImageValues(String defaultImage)
    {
        this.defaultImage = defaultImage;
    }

    public void setupImageValues(String defaultImage, String toolImage)
    {
        this.defaultImage = defaultImage;
        this.toolImage = toolImage;
    }
}
