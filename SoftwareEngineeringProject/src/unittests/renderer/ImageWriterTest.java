/**
 * 
 */

package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

<<<<<<< Upstream, based on origin/main
=======
import org.junit.jupiter.api.Test;

/**
 * 
 */
import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

>>>>>>> 3c77f3f Changes made 1/2/24 10:39 pm
class ImageWriterTest {

    @Test
    void testWriteToImage() {
<<<<<<< Upstream, based on origin/main
        ImageWriter imageWriter = new ImageWriter("testgreen",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.PINK);
                }
                // 500/10 = 50
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.PINK);
                } else {
                    imageWriter.writePixel(i, j, Color.GREEN);
=======
        ImageWriter imageWriter = new ImageWriter("testblue",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                // 500/10 = 50
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                } else {
                    imageWriter.writePixel(i, j, Color.BLUE);
>>>>>>> 3c77f3f Changes made 1/2/24 10:39 pm
                }
            }
        }
        imageWriter.writeToImage();
    }

}