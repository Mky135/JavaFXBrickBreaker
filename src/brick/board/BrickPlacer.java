package brick.board;

import brick.util.Brick;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class BrickPlacer {

    ArrayList<Brick> zone1;
    ArrayList<Brick> zone2;
    ArrayList<Brick> zone3;
    ArrayList<Brick> zone4;

    Document doc;
    NodeList nodeList;
    Board board;

    public BrickPlacer(String fileName, Board board) {
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            this.doc = db.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.board = board;
        setZone1();
        setZone2();
        setZone3();
        setZone4();
    }

    public void setZone1() {
        nodeList = doc.getElementsByTagName("zone1");
        zone1 = getZone();
    }

    public void setZone2() {
        nodeList = doc.getElementsByTagName("zone2");
        zone2 = getZone();
    }

    public void setZone3() {
        nodeList = doc.getElementsByTagName("zone3");
        zone3 = getZone();
    }

    public void setZone4() {
        nodeList = doc.getElementsByTagName("zone4");
        zone4 = getZone();
    }

    private ArrayList<Brick> getZone() {
        ArrayList<Brick> bricks = new ArrayList<>();
        NodeList brickNodes = (NodeList) nodeList.item(0);

        for (int i = 0; i < brickNodes.getLength(); i++) {
            Node node = brickNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                double x = Double.parseDouble(element.getElementsByTagName("x").item(0).getTextContent());
                double y = Double.parseDouble(element.getElementsByTagName("y").item(0).getTextContent());
                int startingLife = Integer.parseInt(element.getElementsByTagName("startingLife").item(0).getTextContent());
                bricks.add(new Brick(x, y, startingLife, board));
            }
        }
        return bricks;
    }

    public ArrayList<Brick> getZone1() {
        return zone1;
    }

    public ArrayList<Brick> getZone2() {
        return zone2;
    }

    public ArrayList<Brick> getZone3() {
        return zone3;
    }

    public ArrayList<Brick> getZone4() {
        return zone4;
    }

    public static void main(String[] args) {
        new BrickPlacer("brick/board/board1.xml", null);
    }
}
