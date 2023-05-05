import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class VolumeCalculator {
    public static void main(String[] args) throws Exception {
        // Decide card band based on income
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Key in height: ");
        String w1 = br.readLine();
        System.out.print("Key in width: ");
        String w2 = br.readLine();
        System.out.print("Key in length: ");
        String w3 = br.readLine();
        Map<String, String> params = new HashMap<>();
        params.put("height", w1);
        params.put("width", w2);
        params.put("length", w3);

        long volume = new VolumeCalculator().calculateRectangularPrismVolume(params);
        System.out.println("The volume is : " + volume);
    }

    public int calculateRectangularPrismVolume(final Map<String, String> params) {
        int height = getHeight(params);
        int width = getWidth(params);
        int length = getLength(params);

        return height * width * length;
    }

    public int getHeight(Map<String, String> params) {
        return Integer.valueOf(params.get("height"));
    }

    public int getWidth(Map<String, String> params) {
        return Integer.valueOf(params.get("width"));
    }

    public int getLength(Map<String, String> params) {
        return Integer.valueOf(params.get("height"));
    }
}