package dev.SOAPwebservices;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Random;

@WebService
public class QuoteSoapService {
    private ArrayList<String> spreuken = new ArrayList<>();
    private int low = 0;
    private int high = 3;

    public QuoteSoapService() {
        spreuken.add("Java is to JavaScript what car is to Carpet.");
        spreuken.add("Sometimes it pays to stay in bed on Monday, rather than spending the rest of the week debugging Monday’s code.");
        spreuken.add("Code is like humor. When you have to explain it, it’s bad.");
    }

    public String getSpreuk (){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return spreuken.get(result);
    }
}
