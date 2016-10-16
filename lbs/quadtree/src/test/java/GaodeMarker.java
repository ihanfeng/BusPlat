import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/10/16.
 */
@Slf4j
@Data
public class GaodeMarker {

    @JSONField(serialize = false)
    protected Double lat;
    @JSONField(serialize=false)
    private Double lon;

    private String icon;

    Double[] position;

    public GaodeMarker(Double lon,Double lat,String icon){
        Double[] pos = new Double[2];
        pos[0] = lon;
        pos[1] = lat;
        this.position = pos;
        this.icon = icon;

    }


    public static void main(String[] args) {

        GaodeMarker marker = new GaodeMarker(22D,23D,"ss.png") ;

        log.info(JSON.toJSONString(marker));

    }

}
