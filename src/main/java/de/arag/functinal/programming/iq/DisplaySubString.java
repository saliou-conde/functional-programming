package de.arag.functinal.programming.iq;

public class DisplaySubString {

    private static DisplaySubString displaySubString;
    private DisplaySubString(){
    }

    public static DisplaySubString getDisplaySubString() {
        return displaySubString == null ? new DisplaySubString() : displaySubString;
    }

    public String[] displaySubString(String txt, int n) {
        //Given for example txt ="ab cd ef ij kl"
        //Result shall be "abc", "def", "ijk", "l"
        var tmpTxt = txt.replace(" ", "");
        int length = tmpTxt.length();
        int i = 0;
        int j = 0;
        int rest = length%n;
        int divide = length/n;
        String[] result = new String[rest+divide];
        while (i < length) {
            int lastIndex = i+n;
            if(lastIndex > length) {
                lastIndex = length;
            }
            result[j] = tmpTxt.substring(i, lastIndex);
            i+=n;
            j++;
        }
        return result;
    }

}
