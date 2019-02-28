package Utils;

import java.util.regex.Pattern;

public class StringOperations {
    /**
     * Scapa de id daca are
     * @param t Numele studentului cu id-ul in paranteze
     */
    public static String prelucreazaSir(String t){
        String[] rez = t.split(Pattern.quote("("));
        return rez[0];
    }
}
