package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Math.round;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            Thread.sleep((long) (1200 + Math.random() * 1000));
            float a = curse("https://www.bestchange.ru/sberbank-to-usd-coin-trc20.html");
            float b = curse("https://www.bestchange.ru/usd-coin-trc20-to-sberbank.html");
            System.out.println(round((a - b) * 100 / a) + " %");
        }
    }

    static float curse(String urlCurseBestchange) throws IOException {
        URL url = new URL(urlCurseBestchange);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        String stringfFind = "<input type=\"text\" id=\"notify_rate\" name=\"notify_rate\" value=\"";
        float curse = 0;
        Pattern pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains(stringfFind)) {
                Matcher matcher = pat.matcher(inputLine);
                while (matcher.find()) {
                    curse = Float.parseFloat(matcher.group());
                }
                System.out.println("Курс обмена = " + curse);
            }
        }
        return curse;
    }
}
