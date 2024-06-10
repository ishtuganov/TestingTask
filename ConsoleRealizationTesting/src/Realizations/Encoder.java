package Realizations;

import Intefaces.Coder;

import java.util.HashMap;

public class Encoder implements Coder {
    private final HashMap<Character, String> encryptKey;
    private final HashMap<String, Character> decryptKey;

    public Encoder(){
        decryptKey = new HashMap<>();
        encryptKey = new HashMap<>();
        for (int i = 65; i < 123; i++){         //латиница (заглавные и строчные)
            if (i > 90 && i < 97) continue;     //прочие символы
            String stringCode = Integer.toString(i);
            encryptKey.put((char)i, stringCode);
            decryptKey.put(stringCode, (char)i);
        }
        for (int i = 1040; i < 1104; i++){      //кириллица (заглавные и строчные)
            String stringCode = Integer.toString(i);
            encryptKey.put((char)i, stringCode);
            decryptKey.put(stringCode, (char)i);
        }
    }
    @Override
    public String encrypt(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++){
            char symbol = line.charAt(i);
            if (isCyrillicOrLatin(symbol)){
                sb.append('^');
                sb.append(encryptKey.get(symbol));
                sb.append('^');
            } else {
                sb.append(symbol);
            }
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String line) {
        StringBuilder sb = new StringBuilder();
        String[] strings = line.split("\\^");
        for (String str: strings){
            if (decryptKey.containsKey(str)){
                sb.append(decryptKey.get(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    private boolean isCyrillicOrLatin(char symbol){
        int code = symbol;
        if (code >= 65 && code <= 90 || code >= 97 && code <= 122) //проверка на латиницу
            return true;
        return code >= 1040 && code <= 1103; //проверка на кириллицу
    }
}
