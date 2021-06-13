package tr.yildiz.myapplication;

import java.util.Date;

enum PatternType {
    Düz,
    Kareli,
    Cizgili
}
enum Color {
    Siyah,
    Beyaz,
    Mavi
}
enum ClothesType {
    Pantolon,
    Şapka,
    Tshirt,
    Mont
}

public class Clothes {
    PatternType patternType;
    Color color;
    Date purchaseDate;
    Double price;
    ClothesType clothesType;
    private byte[] image;

    public Clothes(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
}
