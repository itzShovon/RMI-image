import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import javax.imageio.ImageIO;

public class Main{
  public static void main(String args[])throws IOException{
	  int freeArray[] = new int[4];
	  //Free server checking Job
	  Picture1 call1 = null;
	  Picture2 call2 = null;
	  Picture3 call3 = null;
	  Picture4 call4 = null;
	  int countX = 0;
	  
	  try {
		  call1 = (Picture1)Naming.lookup("rmi://localhost:5000/shovon");
//		  System.out.println("Add result\t" + stub.isFree());
		  freeArray[0] = call1.isFree();
		  
		  call2 = (Picture2)Naming.lookup("rmi://localhost:5001/shovon");
		  freeArray[1] = call2.isFree();
		  
		  call3 = (Picture3)Naming.lookup("rmi://localhost:5002/shovon");
		  freeArray[2] = call3.isFree();
		  
		  call4 = (Picture4)Naming.lookup("rmi://localhost:5003/shovon");
		  freeArray[3] = call4.isFree();
	  } catch (NotBoundException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  
	  for(int i=0; i<freeArray.length; i++) {
		  System.out.println(freeArray[i]);
		  if(freeArray[i] == 1)
			  countX++;
	  }
	  System.out.println("Count size\t" + countX);
	  
	  //Spliting Job
	  File file = new File("../bin/Demo.jpg"); // I have image in my working directory
      FileInputStream fis = new FileInputStream(file);
      BufferedImage image = ImageIO.read(fis); //reading the image file

      int rows = countX; //You should decide the values for rows and cols variables
      int cols = 1;
      int chunks = rows * cols;

      int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
      int chunkHeight = image.getHeight() / rows;
      int count = 0;
      BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
      for (int x = 0; x < rows; x++) {
          for (int y = 0; y < cols; y++) {
              //Initialize the image array with image chunks
              imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

              // draws the image chunk
              Graphics2D gr = imgs[count++].createGraphics();
              gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
              gr.dispose();
          }
      }
      System.out.println("Splitting done");

      //writing mini images into image files
//      for (int i = 0; i < imgs.length; i++) {
      int temp = 0;
      for (int i = 0; i < freeArray.length; i++) {
    	  if(freeArray[i] == 1)
    		  ImageIO.write(imgs[temp++], "jpg", new File("..\\bin\\img" + i + ".jpg"));
      }
      System.out.println("Mini images created");
      
      
      
      
      //Sending and receiving Job
//      ImageView pic = (ImageView)Naming.lookup("rmi://localhost:5003/shovon");
      byte[] RecByteImage = null;
      BufferedImage buffImage;
      InputStream in;
      BufferedImage originalImage;
      byte[] imageInByte;
      ByteArrayOutputStream baos;
		for(int i=0; i<4; i++) {
			if(freeArray[i] == 1) {
		      	
//				originalImage = ImageIO.read(new File("../bin/img" + i + ".jpg"));
//				baos = new ByteArrayOutputStream();
//				ImageIO.write(originalImage, "JPEG", baos);
//				baos.flush();
//				imageInByte=baos.toByteArray();
//				baos.close();
				
		//		byte[] RecByteImage= sampOB.ProcessIT(imageInByte);
				switch (i) {
				case 0:
					originalImage = ImageIO.read(new File("../bin/img" + i + ".jpg"));
					baos = new ByteArrayOutputStream();
					ImageIO.write(originalImage, "JPEG", baos);
					baos.flush();
					imageInByte=baos.toByteArray();
					baos.close();
					
					RecByteImage = call1.process(imageInByte);
					in = new ByteArrayInputStream(RecByteImage);
					buffImage=ImageIO.read(in);
					
					ImageIO.write(buffImage, "JPEG", new File("../bin/imgX" + i + ".jpg"));
					System.out.println("\nImage Saved.");
					break;
				case 1:
					originalImage = ImageIO.read(new File("../bin/img" + i + ".jpg"));
					baos = new ByteArrayOutputStream();
					ImageIO.write(originalImage, "JPEG", baos);
					baos.flush();
					imageInByte=baos.toByteArray();
					baos.close();
					
					RecByteImage = call2.process(imageInByte);
					in = new ByteArrayInputStream(RecByteImage);
					buffImage=ImageIO.read(in);
					
					ImageIO.write(buffImage, "JPEG", new File("../bin/imgX" + i + ".jpg"));
					System.out.println("\nImage Saved.");
					break;
				case 2:
					originalImage = ImageIO.read(new File("../bin/img" + i + ".jpg"));
					baos = new ByteArrayOutputStream();
					ImageIO.write(originalImage, "JPEG", baos);
					baos.flush();
					imageInByte=baos.toByteArray();
					baos.close();
					
					RecByteImage = call3.process(imageInByte);
					in = new ByteArrayInputStream(RecByteImage);
					buffImage=ImageIO.read(in);
					
					ImageIO.write(buffImage, "JPEG", new File("../bin/imgX" + i + ".jpg"));
					System.out.println("\nImage Saved.");
					break;
				case 3:
					originalImage = ImageIO.read(new File("../bin/img" + i + ".jpg"));
					baos = new ByteArrayOutputStream();
					ImageIO.write(originalImage, "JPEG", baos);
					baos.flush();
					imageInByte=baos.toByteArray();
					baos.close();
					
					RecByteImage = call4.process(imageInByte);
					in = new ByteArrayInputStream(RecByteImage);
					buffImage=ImageIO.read(in);
					
					ImageIO.write(buffImage, "JPEG", new File("../bin/imgX" + i + ".jpg"));
					System.out.println("\nImage Saved.");
					break;
				

				default:
					break;
				}
				
				
//				InputStream in = new ByteArrayInputStream(RecByteImage);
//				BufferedImage buffImage=ImageIO.read(in);
//				
//				ImageIO.write(buffImage, "JPEG", new File("../bin/imgX" + i + ".jpg"));
//				System.out.println("\nImage Saved.");
			}
		}
//      BufferedImage img[] = null;
//      for(int i=0; i<freeArray.length; i++) {
//    	  if(freeArray[i] == 1)
//	      try {
//	    	  switch (i) {
//			case 0:
//				call1.process(img[i]);
//				break;
//
//			default:
//				break;
//			}
//	          img[i] = ImageIO.read(new File("../bin/img" + i + ".jpg"));
//	      } catch (IOException e) {
//	      }
//      }

		
		
      
		
		
      
      //Marging Job
      rows = countX;   //we assume the no. of rows and cols are known and each chunk has equal width and height
      cols = 1;
      chunks = rows * cols;
      
      int type;
      //fetching image files
      temp = 0;
      File[] imgFiles = new File[chunks];
//      for (int i = 0; i < chunks; i++) {
    	 for (int i = 0; i < freeArray.length; i++) {
    		 if(freeArray[i] == 1)
    			 imgFiles[temp++] = new File("..\\bin\\img" + i + ".jpg");
      }

     //creating a bufferd image array from image files
      BufferedImage[] buffImages = new BufferedImage[chunks];
      for (int i = 0; i < chunks; i++) {
          buffImages[i] = ImageIO.read(imgFiles[i]);
      }
      type = buffImages[0].getType();
      chunkWidth = buffImages[0].getWidth();
      chunkHeight = buffImages[0].getHeight();

      //Initializing the final image
      BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);

      int num = 0;
      for (int i = 0; i < rows; i++) {
          for (int j = 0; j < cols; j++) {
              finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
              num++;
          }
      }
      System.out.println("Image concatenated.....");
      ImageIO.write(finalImg, "jpeg", new File("..\\bin\\finalImg.jpg"));
  }
}