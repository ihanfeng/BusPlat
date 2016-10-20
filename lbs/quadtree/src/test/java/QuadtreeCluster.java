import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.quadtree.clustering.GeoPointInternal;
import com.quadtree.clustering.IGeoPoint;
import com.quadtree.clustering.QTNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by hg on 2016/10/16.
 */
@Slf4j
public class QuadtreeCluster {
    static  int ex = 1000000;
    public static void main(String[] args) throws IOException {

        List<String> lines = FileUtils.readLines(new File(QuadtreeCluster.class.getResource("/").getPath() + "points.txt"));


        List<GeoPointInternal> points = Lists.newArrayList();
        for(String line : lines){
            String[] ll = line.split(",");
            int lon = (int) (Double.valueOf(ll[2]) * ex);
            int lat = (int) (Double.valueOf(ll[1]) * ex);

            GeoPointInternal point = new GeoPointInternal(lon,lat);
            points.add(point);
        }

        toStr(points,"http://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png");


        QTNode root = new QTNode(points);


        Collection<? extends IGeoPoint> queryResult = root.query(QTNode.WHOLE_WORLD);
        log.info("size" + queryResult.size());

        for(IGeoPoint tmpP : queryResult){
            log.info(tmpP.getLat() + "" + tmpP.getLng() + " " + tmpP.getSize() );
        }

        toStr(queryResult,"http://webapi.amap.com/theme/v1.3/markers/n/mark_b3.png");
    }



    public static void toStr( Collection<? extends IGeoPoint>  points,String icon ){

        List<GaodeMarker> markers = Lists.newArrayList();
        for (IGeoPoint point : points){
            GaodeMarker marker = new GaodeMarker(point.getLng()*1.0 / ex,point.getLat()*1.0/ex,icon) ;
            markers.add(marker);
        }

        log.info(JSON.toJSONString(markers));
    }
}
