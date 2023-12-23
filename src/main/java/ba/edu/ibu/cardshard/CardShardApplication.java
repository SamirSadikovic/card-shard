package ba.edu.ibu.cardshard;

import ba.edu.ibu.cardshard.core.model.enums.UserType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.sql.SQLOutput;

/*
	TODO
	 Ask:
	   Is 'Optional' mandatory?
	   How to implement privacy for decks and other entities?
	   How to build dynamic queries?
	 Do:
	   Create 'shop window' for vendors
 */

@SpringBootApplication
public class CardShardApplication {
	public static void main(String[] args) { SpringApplication.run(CardShardApplication.class, args); }

//	public static void main(String[] args) {
//		System.out.println(compareImage(new File("C:/Users/samir/Downloads/card_image_2.jpg"),
//										new File("C:/Users/samir/OneDrive/Desktop/projects/card_images/large/35269904.jpg")));
//	}
//public static float compareImage(File fileA, File fileB) {
//
//	float percentage = 0;
//	try {
//		// take buffer data from both image files //
//		BufferedImage picture = ImageIO.read(fileA);
//		DataBuffer pictureBuffer = picture.getData().getDataBuffer();
//		int pictureBufferSize = pictureBuffer.getSize();
//
//		BufferedImage image = ImageIO.read(fileB);
//		DataBuffer imageBuffer = image.getData().getDataBuffer();
//		int imageBufferSize = imageBuffer.getSize();
//
//		int count = 0;
//		// compare data-buffer objects //
//		if (pictureBufferSize == imageBufferSize) {
//
//			for (int i = 0; i < pictureBufferSize; i++) {
//
//				if (pictureBuffer.getElem(i) == imageBuffer.getElem(i)) {
//					count = count + 1;
//				}
//
//			}
//			percentage = (count * 100) / pictureBufferSize;
//		} else {
//			System.out.println("Both the images are not of same size");
//		}
//
//	} catch (Exception e) {
//		System.out.println(e.getMessage());
//	}
//	return percentage;
//}
//	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
//		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//
//		Graphics2D g2d = dimg.createGraphics();
//		g2d.drawImage(tmp, 0, 0, null);
//		g2d.dispose();
//
//		return dimg;
//	}
}
