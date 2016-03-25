package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Влад on 10.07.2015.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes types)
    {
            ImageReader reader;
            if (types == ImageTypes.JPG) reader = new JpgReader();
            else if (types == ImageTypes.PNG) reader = new PngReader();
            else if (types == ImageTypes.BMP) reader = new BmpReader();
        else throw new IllegalArgumentException();
        return reader;
    }
}
