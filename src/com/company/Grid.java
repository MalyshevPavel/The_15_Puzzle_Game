package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grid {
    private  BufferedImage bufferedImage;
    private  int number;

    public Grid(String file, int num) {
        try {
            bufferedImage = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        number = num;
    }

    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public  int getNumber() {
        return this.number;
    }

    public  void setNumber(int number) {
        this.number = number;
    }
}
