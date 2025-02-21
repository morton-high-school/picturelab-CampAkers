import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

  public void negate(){
    Pixel[][] pixels=this.getPixels2D();
    for(Pixel[] rowArray:pixels){
      for(Pixel pixelObj:rowArray){
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  public void fixUnderwater(){
    Pixel[][] pixels=this.getPixels2D();
    for(Pixel[] rowArray:pixels){
      for(Pixel pixelObj:rowArray){
        if(155<pixelObj.getBlue()&&pixelObj.getBlue()<190&&150<pixelObj.getGreen()&&pixelObj.getGreen()<170&&10<pixelObj.getRed()&&pixelObj.getRed()<20){
          pixelObj.setBlue(pixelObj.getBlue()+50);
          pixelObj.setRed(0);
          pixelObj.setGreen(pixelObj.getGreen()+30);
        }
      }
    }
  }

  public void grayscale(){
    int sum;
    Pixel[][] pixels=this.getPixels2D();
    for(Pixel[] rowArray:pixels){
      for(Pixel pixelObj:rowArray){
        sum=(pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
        pixelObj.setRed(sum);
        pixelObj.setGreen(sum);
        pixelObj.setBlue(sum);
      }
    }
  }
  
  public  void keepOnlyBlue(){
    Pixel[][] pixels=this.getPixels2D();
    for(Pixel[] rowArray:pixels){
      for(Pixel pixelObj:rowArray){
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }

  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    int count=0;
    for (int row = 0; row < 479; row++)
    {
      for (int col = 0; col <479; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[479-col][479-row];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }

  public void mirrorDiagonal1()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count=0;
    int k=1;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length&&row<k; row++)
    {
      for (int col = 0; col < count && count<width; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
      count++;
      k++;
    } 
  }

  public void mirrorGull()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 232; row < 325; row++)
    {
      for (int col = 237; col <350; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width +40 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for(int i =0; i<height/2;i++){
      for(int j =0; j<pixels[0].length;j++){
        topPixel=pixels[i][j];
        bottomPixel=pixels[(pixels.length-1)-i][j];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }

  public void mirrorSeagull()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for(int i =0; i<height/2;i++){
      for(int j =0; j<pixels[0].length;j++){
        topPixel=pixels[i][j];
        bottomPixel=pixels[(pixels.length-1)-i][j];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }

  public void mirrorArms()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for(int i =156; i<190;i++){
      for(int j =100; j<172;j++){
        topPixel=pixels[i][j];
        bottomPixel=pixels[(pixels.length+100)-i][j];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
    for(int i =170; i<195;i++){
      for(int j =239; j<295;j++){
        topPixel=pixels[i][j];
        bottomPixel=pixels[(pixels.length+100)-i][j];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }

  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for(int i =0; i<height/2;i++){
      for(int j =0; j<pixels[0].length;j++){
        bottomPixel=pixels[i][j];
        topPixel=pixels[(pixels.length-1)-i][j];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void copyParameter(Picture fromPic, int pasteRow, int pasteCol,int startRow,int startCol, int endRow, int endCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for(int i=startRow;i<endRow;i++,pasteRow++){
      for(int j=startCol;j<endCol;j++,pasteCol++){
        fromPixel=fromPixels[i][j];
        toPixel=toPixels[pasteRow][pasteCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }


    for (int fromRow = startRow, toRow = endRow; fromRow < toRow; fromRow++){
      for (int fromCol = startCol, toCol = endCol;fromCol < toCol;  fromCol++){
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("butterfly1.jpg");
    Picture flower2 = new Picture("koala.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color Colortop = null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        
          topPixel = pixels[row][col];
        bottomPixel = pixels[row+1][col];
        Colortop = bottomPixel.getColor();
      
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist||topPixel.colorDistance(Colortop) > 
            edgeDist){
          leftPixel.setColor(Color.BLACK);
            }else{
          leftPixel.setColor(Color.WHITE);
            }
        
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
