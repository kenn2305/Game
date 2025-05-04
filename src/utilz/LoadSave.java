package utilz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class LoadSave {
    public static final String TEST_LEVEL_DATA = "Test_level.tmx";
    public static final String TILE_MAP_DATA = "Assets.png";
    public static final String Background_1 = "Background_1.png";
    public static final String Background_2 = "Background_2.png";
    public static final String PLAYER = "Fbk.png";
    public static final String BLUEGOLEM = "BlueGolem.png";
    public static final String GOLDGOLEM = "GoldGolem.png";
    public static final String BRINGER_OF_DEATH = "BringerOfDeath.png";
    public static final String ITEMS = "items.png";
    public static final String FBK_ICON = "FbkIcon.png";
    public static final String MENU_BUTTON = "MenuButtons.png";
    public static final String MENU_BUTTON_BACKGROUND = "menu_background.png";
    public static final String MENU_BACKGROUND = "background.png";
    public static final String PAUSE_BACKGROUND = "pause_background.png";
    public static final String SOUND_BUTTON = "sound_button.png";
    public static final String URM_BUTTOM = "urm_button.png";
    public static final String VOLUME = "volumn_num.png";
    public static final String SLIDER = "slider.png";
    public static BufferedImage loadImage(String filename) {
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/"+filename);
        try{
            image = ImageIO.read(is);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return image;
    }

    public static Map<String,int[][]> getLevelData(String filename){
        Map<String,int[][]> map_data = new LinkedHashMap<>();
        try{
           InputStream file = LoadSave.class.getResourceAsStream("/res/"+filename);
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document doc = builder.parse(file);
           doc.getDocumentElement().normalize();

           NodeList layers = doc.getElementsByTagName("layer");

           for ( int i = 0; i < layers.getLength(); i++ ){
               Element layer = (Element) layers.item(i);
               String name = layer.getAttribute("name");
               int width = Integer.parseInt(layer.getAttribute("width").trim());
               int height = Integer.parseInt(layer.getAttribute("height").trim());
               Element data = (Element) layer.getElementsByTagName("data").item(0);
               String encoding = data.getAttribute("encoding");
               String rawData = data.getTextContent().trim();

               String token[] = rawData.split(",");
               int[][] tileMap = new int[height][width];

               for ( int row = 0; row < height; row++ ){
                   for ( int col = 0; col < width; col++ ){
                       int index = row * width + col;
                       int value = Integer.parseInt(token[index].trim());
                       if (value > 0){
                           value = value - 1;
                       }
                       tileMap[row][col] = value;
                   }
               }

               map_data.put(name, tileMap);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map_data;
    }

    public static List<Rectangle> loadTileBound(String filename){
        List<Rectangle> bounds = new ArrayList<>();
        try{
            InputStream file = LoadSave.class.getResourceAsStream("/res/"+filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList objectgroup = doc.getElementsByTagName("objectgroup");

            for ( int i = 0 ; i < objectgroup.getLength(); i++ ){
                Element grp = (Element) objectgroup.item(i);
                String name = grp.getAttribute("name");

                if (name.equals("Collision")){
                    NodeList objects = grp.getElementsByTagName("object");

                    for ( int j = 0 ; j < objects.getLength(); j++ ){
                        Element obj = (Element) objects.item(j);
                        if (obj.hasAttribute("gid")){
                            int x = (int)Float.parseFloat(obj.getAttribute("x").trim());
                            int y = (int)Float.parseFloat(obj.getAttribute("y").trim());
                            int width = (int)Float.parseFloat(obj.getAttribute("width").trim());
                            int height = (int)Float.parseFloat(obj.getAttribute("height").trim());
                            bounds.add(new Rectangle(x, y - height, width, height));
                        } else {
                            int x = (int)Float.parseFloat(obj.getAttribute("x").trim());
                            int y = (int)Float.parseFloat(obj.getAttribute("y").trim());
                            int width = (int)Float.parseFloat(obj.getAttribute("width").trim());
                            int height = (int)Float.parseFloat(obj.getAttribute("height").trim());
                            bounds.add(new Rectangle(x, y, width, height));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bounds;
    }
}
