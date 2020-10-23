package brick.util;

public class IllustratorToXMLText {
    public static void main(String[] args) {
        for (double i = 77.5; i <= 197.5; i+=40) {
            for (double j = 322.5   ; j <= 502.5; j+=20) {
                System.out.println(xmlFormat(i,j));
            }
        }
    }

    public static String xmlFormat(double x, double y)
    {
        return "<brick>\n" +
                "\t<x>" + x + "</x>\n" +
                "\t<y>" + y + "</y>\n" +
                "\t<startingLife>10</startingLife>\n" +
                "</brick>";
    }
}
