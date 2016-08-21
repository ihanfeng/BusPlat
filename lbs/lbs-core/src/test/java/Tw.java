import com.zhiyin.lbs.polygon.Point;
import com.zhiyin.lbs.polygon.Polygon;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/8/21.
 */
@Slf4j
public class Tw {
    public static void main(String[] args) {

        String str = "[116.32681187129933,39.99258564107374],[116.32923026376905,39.99306600287305],[116.3339834734788,39.993098034106424],[116.3341445718679,39.98947388577702],[116.32849240835903,39.98947555963108],[116.32832069963266,39.99028185347632],[116.32690794634397,39.990251555242395";
        str = str.replace("[","");
       str = str.replace("],",";");

        String[] ps = str.split(";");
//        ],[116.32671226421688,39.98937877078128],[116.33424411265072,39.99319773434252],[116.330475,39.991287
        Polygon.Builder build = Polygon.Builder();
        for(String pStr : ps){
            build.addVertex(bp(pStr));
        }
        boolean con = build.build().contains(bp("116.330475,39.991287"));
        log.info(str);
        log.info(con +"");
    }

    public static Point bp(String str){
        String[] info = str.split(",");
       return new Point( Float.valueOf(info[0])*1000000,  Float.valueOf(info[1])*1000000);
    }


}
