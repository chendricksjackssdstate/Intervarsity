/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class Convert {
    Convert(){
        dec = new int[character.length];
        int n = 492;
        for(int i = 0; i < dec.length; i++){
            dec[i] = n;
            n++;
        }
    }
    public String hash(String oneline){
        if(oneline.equals(""))
            return "\n";
        String code = "";
        boolean notintable = true;
        for(int i = 0; i < oneline.length(); i++){
            int a = 0;
            while(a < character.length){
                if(oneline.charAt(i) == character[a]){
                    code = code + String.valueOf(dec[a] + hashnum) + "/";
                    notintable = false;
                }
                a++;
            }
            if(notintable){
                code = code + oneline.charAt(i) + "/";
            }
        }
        return code;
    }
    public String unhash(String oneline){
        if(oneline == null)
            return null;
        if(oneline.equals("")){
            return "";
        }
        String uncode = "";
        String [] letters = oneline.split("/");
        for(int i = 0; i < letters.length; i++){
            if(Character.isDigit(letters[i].charAt(0))){
                int a = 0;
                while(a < dec.length){
                    int num = Integer.parseInt(letters[i]);
                    num = num - hashnum;
                    if(num == dec[a]){
                        uncode = uncode + character[a];
                    }
                    a++;
                }
            }
            else{
                uncode = uncode + letters[i];
            }
        }
        return uncode;
    }
    private int hashnum = 53;
    private char[] character = {' ', '!', '"', '#', '$', 
                                '%', '&', '(', ')', '*', 
                                '+', ',', '-', '.', '/', 
                                '0', '1', '2', '3', '4', 
                                '5', '6', '7', '8', '9', 
                                ':', ';', '<', '=', '>', 
                                '?', '@', 'A', 'B', 'C', 
                                'D', 'E', 'F', 'G', 'H', 
                                'I', 'J', 'K', 'L', 'M', 
                                'N', 'O', 'P', 'Q', 'R', 
                                'S', 'T', 'U', 'V', 'W', 
                                'X', 'Y', 'Z', '[', ']', 
                                '^', '_', ',', '`', 'a', 
                                'b', 'c', 'd', 'e', 'f', 
                                'g', 'h', 'i', 'j', 'k', 
                                'l', 'm', 'n', 'o', 'p', 
                                'q', 'r', 's', 't', 'u',
                                'v', 'w', 'x', 'y', 'z', 
                                '{', '|', '}', '~'};
    private int[] dec; 
    
}
