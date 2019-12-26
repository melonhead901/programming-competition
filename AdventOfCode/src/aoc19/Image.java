package aoc19;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Image {
    private static final String input = "211202121222222222221222212222202022222222222122221220222222222222222222222202222222222222122121022222212222222222212212222222210222202212222222012222200222021222222222220222222222202222222222222022220221222222222222222222222222222222222222022022022222222222222222222212222222221222222202220222202222220202021222222222221222202222212222222222222122222220222222222222222222222222222222222222122220022222212222222222202222222222220222202212221222102222211202020222222222221222222222212222222222222022220221222222222222222222222212222222222222122022122222202222222222212202222222212222202212222222202222220222022222222222221222212222202022222222222222221220222222222222222222222212222222222222022122222222222222222222202202222222220222222202222222212222210002122222222222221222212222222022222222222122221221222222222222222212222202222222222222022220222222202222222222212222222222221222222222220222112222211012121222222222222222222222222221222222222122220222222222222222222212222212222222222222022120222222222222222222212202222222221222212202221222122222212122121222222222222222212222122022222222222222221220222222222222222212222212222220222222122021022222222222222222212202222222221222222202220222102222200212020222022222222222212222012221222222222122221221222222222222222212222202222222222222222121122222212222222222212212222222221222212222222222111222212022021222122222220222202222022022222222222122220221222222222222222202222222222222222222122120222222222222222222202212222222210222212212221222020222201222222222022222222222222222122222222222222022221220222222222222222202222212222222222222222222022222222222222222202222222222221222212202221222002222220202020222122222220222202222112221222222222022220222222222222222222222222212222220222222022120022222212222022222202202222222200222202202221222210222220022122222222222222222212222022021222222222222220221222222222222222202222202222221222222222020122222212220122222202212222222201222202212221222112222220002222222022222220222222222122121222222222022222221222222222222222212222202222201222222222120222222222221222222222202222222212222202222220222101222200212021222022222221222202222122222222222222222221220222222222222222222222212222210222222022022122222222220022222202202222222222222222222222222220222221022022222122222220222222222012020222220222122220221222222222222222212222212222212222222222220022222212222022222212222222222212222212212221222011222222012022222122222220222202222220122222222222122220221222222222222222222222212222221222222122020022222222222222222222222222202220222202212220222022222212102221222122222222222202222112021222222222022222220222222222222222222222222222202222222022120020222212221022222202202222212210222212202220222211222210102220222222222220222222222120122222222222022220220222222222222222222222222222202222222222222120222222220122222222202222202200222202222222222220222200012022222122222220222202222220122222222222122220222222222222222222202222212222220220222122122022222212222022222222222222202201222212212221222221222212122221222022222220222200222112022222222222222220020222222222222222202222202222220221222222020222222202221122222212212222212212222222212221222212222211012221222022222222222220222201121222220222022222021222222222222222212222222222200221222122220221222202220122222222202222222211222222202220222022222212002120222222222220222220222100222222221222022222122222222202222222212222212222221222222022121122222202222022222202202222212200222222202220222010222212102222222002222221222221222011022222222222022222120222222202222222222222212222210221222022221021222212220222222202212222212202222222202220222001222220012020222112222222222221222121122222220222122220020222222212222222212222212222220222222122022221222202220022222222202222222210222202202220222010222211022120222212222221222220222202120222221222122221122222222220222222202222202222210221222222221122222222220022222202212222202201222202212220222012222200212022222122222222222222222212222222222222122220121222222210222222202222212222201220222122121122222222222022222222222222212210222212212220222200222200012122222112222221222202222201220222222222222221221222222201222222212222222222210220222222122121222222221022222222222222212220222202212220222202222202012220222212222220222201222010121222220222222220020222222201222222212222212222220220222222122221222222221022222202202222202202222212222220222211222201112021222012222220222221222101121222220222222221121222222201222222212222202222022220222022221122222222222122222212222222222201222222222222222220222201202122222102222222222201222102220222222222122220222222222220222222222222202222211222222122120221222212221122222212202222202200222222222222222020222222222121222222222222222212222012021202222222022220021222222222222222202222222222200220222022221020222212220122222202222222222221222222212220212220222210122220222222222222222212222110222212220222222220021222222212222222222222212222001221222122020021222212222022222202202222212212222222202222212102222210022121222102222121222210222021121202221222122221022222222210222222222222202222001221222222121120212222220022222202212222202220222222212222212012222200022221222022222020222200222222122222221222022222122222222201222222202222202222110222222122221121222202221222222222202222222222222222202222212220222220202121222222222121222201222111221212221222022220120222222210222222202222212222202222222020021020202212222122222202212222202211222222202221212002222212122222222022222121222202222102121212221222022220022222202200222222212220222222211222222121221021222222221022222202222222222212222222202222212010222202102121222022222120222220222201021202222222022222222222222220222222212221212222200221222122221120222212221122222222222222202201222222212222212222222221222120222002222121222201222100021222221222222222120222212211222222222222202222001221222022020122210222221022222222212222222212222202222222212120222202022221222122202122222221222002221212222222022222222222202200222222212220222222111221222122221220210212221022222222202222212202222212212221222010222222102222222002220020222210222212120222222222022220022222202212222222222222212222002222222220121221210202221122222212212222222222222202202222202122222200222111222122220122222210222000020202221222022222221222222222222222202222212222211220222022220020212222220222222212212222222211222212212220202200222222102112222222222222222221222112021222221222222221222222222200222222202221222222212221022020220022220202222122222222202222210222222212202222202122222220112100222002200020222212222010120202220222022221020222212210222222202220202222011220220121121022022222221222222202212222222222222202222220222221222211012110222102221021222222222200220002222222022220121222212201222222202222212222212222221120220020121202222222222222202222222220222222212222202210222200022210222212220122222221222012220102220222222022221222212222222222202221212222021222022122222121012212222022222222222222200222222212212222212202222221102110222012212122222220222001222202221222222120021222212201222222212222222222011221120020221220111202220022222222202222201221222222222220202110222202012021222122220020222202222121220002222222122122122222222222222222212221212222220221122120020221110202222122222222212222212210222212112222222010222200112110222022222121222221222212020002220222222222121222202211222222212220202222222222220122120121011222221222222202202222222220222212102221222101222210222002222110221222202211222001021022222222022121220222212221222222222220222222012221121121021020121202220221222202212222001220222212112222202210222220212101222110211020222202222000220022222222122020222222222221222222222220222222022221122121121222002222221221222222202222210210222212212222202101222211102022222100220120212220222010020102220222222020222222222211222222202220222222221220121001022021110222221221222222222222011222222222022221202000222221022201222200221221010200222011220202220222022220220222212202222222222221222222212221220221220021210202221120222202012222112221222222022220202111222222102112222200220121110202222011020112221222222122101222212220222222202222202222101222222121122220001212221121222222202222220222222202112221222200212212112110222201201020101220222000220002221222022220011222212211222222202222202222012222122001122022210222220222222222022222222220222212102221222120202221202010222011201220010212222001221212220222122121100222222222222222212220212221001220221001120220221212221120222212002222010212222212222220202200212212112022222020220022001201222000020222222222122121002222202220222222212222202221200222220112120122211222222122222222212222012210222202212221212121212201202110222002200220211201222210022222220222122222010222222222222222222220212222122220222222122122200222221122222222012222221201222222112221212002212211022212222122211121222210222212222112222222022222211222220210222222222221222222102221221211022121110222222020222222012222201212222212202221212110222202102021222210211222210221222010020212021222022221021222200212222222222221202200111221121110122021020202221222222222202222022121222212012220212212212202212122222021220020120222222202122102220222122021211222201111222222222221212220101222022200022120002222222220222222002222022110222202202221222202222212202221222120220021210202222020122102020222022020102222220112022222202220202210100222122221122022202202222221222212012222110001222212202221212202212222212100222220211121101220222210021002122222022222222222212102222222202221222200002221020021022022002212222121222212112222100100212212112222202212222212002220220100211220102211222020021012120222022221222222211222022222202220212220102221021111020021201202220120222202222222012200212202102221212212202211022221222121212020221210222210100202222222122220212222201102022222222220212202000221222202222120212212221122222212102222111010222212122222202210212212002120221202221222002200222212020022221222022120000222210211122222202221212220012222022212020121120212222222222202112222111011202202022220202220212212002201220002212021002200222122222112221222122121222222210211222222222220222200111221020122122022121202220121222222022222220210212222022222222020222202022102221222202120112212222200112212020222122122111222212020222222202220212211101221222211221220120202220120222212102222120222222202102222222021202221122212222010210021122210222102120022220222122222210222202220122222202220222220001222020211220220020212220221022212222122010102212212222221202112222220002220221101212221021211222221200202021222222222012222221011222222222220222220222221120112221221012212220122222212022122122101222202022221212020212210022112221000220221102221222100120022221222022022001222210212022222222220222202220222222120121022002202221220222212012122200201202222202222222210212201012011221020221222212222222200220202122222022220102222222011222222212222222202012221221100221121212212222222122212112122222002202202112222222002212202212212221112220221111211222201000122121222122022020222201010222222202222212202221220122120120120120212221021222202012122021021212222112222212112210200122222220002212220112210222110021012022222222021021222212011122222222220202220110221020202121022212202221022022212212222101020222202202222222000222201202110221200222222010201212200221222022222222020000222201112222222220221212222012221120111220022102212221122122222112222012122222222122221202200222212122101221210210022001221222112122222122222122221111222212002222222210221212201120211021110120021201222222021022202022022101000222200022222212120220201212210221210222220222220212011221202220222222122000222210120022222220221202222021210020120120020221222221221122222122222202222222201022221210112212222122122220110202121112202222210022112022222122020122021210212222222011221222210010221222221022222121222221120122222122022012022202202012222222210200201002012221012221020200220202120211102222222222022110022202001122222112220212211120221222200221021020212220222122222112122201111202212102221220010200211002101221111211120121222212120102222022222222022000222212220022222021222212221012212122120021021100202222022222212012222002221222000002221212121201220222010222012210122002211202202201202020222222121101212202112122222212221102211011222120012221221210222221222122202122122212122202022102222202010221201002010221121210020102211222210220112021222122020221200210202222222200222122221200201222121122221112202222121022202022122001102222022112221220120222201212200221011200221212220202200211102020222022122212101221022022221122220112210220212220112222121012222222222222212012022201202212122212200200122210211122100222002210021201200222212202012021222222120112001220220122221102220012221201220221120002022011202221220022202212122210002202001202220212110222222002201221111222221220200212122022222220222222020120010211200022222212221212202110210222110000221120222220220222222102022122211222010222211221202202220112001222010211201201211222112002222220212222220112202221122222220101020022002211202221112210022212022220021120212202122122220222122001202222012201202102100221112210110120210212222111222222212022020111000222122022221202220002111220212121222222221020102222222222212222222222001222020022211221221200221012212220222202212101211222120010222121200022020200002210210222222102221022200220222020210212201101202221121021202122122220110212101100220220101202210202201220121200102211210212201122022020201022020000221202200122221011222012001022221022002211101122122221222120202202222222101212212201210220202211212102222222200200012220011212121100022222201122021212200201001122222022020012120011002222021201010021122221121022202122122110122222211021220200212202222222220221020200001011010202122210002020202022021012002202201222220011022110210112121221222112102000122221220220220012222102002202101112221211222210222222020022210210120112200222211111122021220122022002000211020022222010020022201021221121000100000202212221022021212112222202112212222222200221021200222102100121121221221010212202102200012220212120022212102222011022220220220211200121211220211001122222102221221020202202222010121212200100221202012222212112011222020210010010221212122200102022220221020002000212022122221202021122000112210021200010000210222220120121201122222021102212102111222201121202201202002020120200201212110202101010102022200121222211012221121122221000122021100210212222122211220212102221020122210202222110002212200011201200021201220022012110200222202010110002212220010120010022120110012200120022222122021212000212022122022010021101012221222020212102122120000212202002202202222222210012200102200200111220022112012202102021122021120011121200020122220001221102112201201020102122212202112220122122212222022110111222010201211210002212202112020020222212100211112122120200002020201020021202021200200022222010120111011000012120121101212002212222122121200022022212122202211001220201122201201012022011111200001112022002112002200220221121202120002212010122222010222121211010100220111112122101222222021021211212122121120212202000200210112220002211221002010102121110201000000011200112100002201220112102201101001200002222202202212002201221110202000102001202000200100102121000220100101111222110";

    public static void main(String[] args) {
        int width = 6;
        int height = 25;
        int layers = input.length() / width / height;
        int[][][] map = new int[layers][][];
        for (int i = 0; i < layers; i++) {
            map[i] = new int[width][];
            for (int j = 0; j < width; j++) {
                map[i][j] = new int[height];
                for (int k = 0; k < height; k++) {
                    map[i][j][k] = input.charAt((i * (width * height)) + (j * height) + k);
                }
            }
        }
        Map<Integer, FreqMap> valCountPerLayer = new HashMap<>();
        for (int i = 0; i < layers; i++) {
            FreqMap freqMap = new FreqMap();
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    int val = map[i][j][k];
                    freqMap.addVal(val);
                }
            }
            valCountPerLayer.put(i, freqMap);
        }

        partOne(valCountPerLayer);

        int[][] finalMap = new int[width][];
        for (int i = 0; i < width; i++) {
            finalMap[i] = new int[height];
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < layers; k++) {
                    if (map[k][i][j] != '2') {
                        finalMap[i][j] = map[k][i][j];
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print((finalMap[i][j] == '0') ? " " : "1");
            }
            System.out.println();
        }
    }

    private static void partOne(Map<Integer, FreqMap> valCountPerLayer) {
        Comparator<FreqMap> zeroComparator = Comparator.<FreqMap>comparingInt(x -> x.countOf('0'));
        FreqMap mostZeros = valCountPerLayer.values().stream().min(zeroComparator).get();
        System.out.println(mostZeros.countOf('1') * mostZeros.countOf('2'));
    }
}

class FreqMap {
    private final Map<Integer, Integer> vals;

    public FreqMap() {
        this.vals = new HashMap<>();
    }

    public void addVal(int val) {
        int count = 0;
        if (vals.containsKey(val)) {
            count = vals.get(val);
        }
        vals.put(val, count + 1);
    }

    public int countOf(int val) {
        if (vals.containsKey(val)) {
            return vals.get(val);
        }
        return 0;
    }
}


