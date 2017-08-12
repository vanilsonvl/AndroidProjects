package br.com.vanilson.coursedevmedia;

import java.util.ArrayList;

public class InterpreterSign {
    private ArrayList<Sign> signs = new ArrayList<Sign>(){{
        add(new Sign(20, 1, 18, 2, "Aquário", "@drawable/aquarius"));
        add(new Sign(19, 2, 20, 3, "Peixe", "@drawable/pisces"));
        add(new Sign(21, 3, 19, 4, "Aries", "@drawable/aries"));
        add(new Sign(20, 4, 20, 5, "Touro", "@drawable/taurus"));
        add(new Sign(21, 5, 20, 6, "Gêmeos", "@drawable/gemini"));
        add(new Sign(21, 6, 22, 7, "Câncer", "@drawable/cancer"));
        add(new Sign(23, 7, 22, 8, "Leão", "@drawable/leo"));
        add(new Sign(23, 8, 22, 9, "Virgem", "@drawable/virgo"));
        add(new Sign(23, 9, 22, 10, "Libra", "@drawable/libra"));
        add(new Sign(23, 10, 21, 11, "Escorpião", "@drawable/scorpio"));
        add(new Sign(22, 11, 21, 12, "Sagitário", "@drawable/sagittarius"));
        add(new Sign(22, 12, 19, 1, "Capricórnio", "@drawable/capricorn"));
    }};

    public Sign interpreter(int date, int month) {
        Sign sign = null;

        for (Sign s : signs) {
            if (s.getIniMonth() == month && date >= s.getIniDate()) {
                sign = s;
                break;
            } else if (s.getEndMonth() == month && date <= s.getEndDate()) {
                sign = s;
                break;
            }
        }
        return sign;
    }
}
