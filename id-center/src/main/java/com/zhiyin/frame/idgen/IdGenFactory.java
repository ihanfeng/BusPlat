package com.zhiyin.frame.idgen;

import com.zhiyin.frame.idgen.util.IdGenFailException;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.predictor.idgenerator.BasicEntityIdGenerator;
import org.predictor.idgenerator.GetHardwareIdFailedException;
import org.predictor.idgenerator.InvalidSystemClockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class IdGenFactory {

    public static Long last=0L;

	public synchronized static long gen() {
		long id = 0L;
		try {
//			BasicEntityIdGenerator.getInstance().generateLongId();
//			BasicEntityIdGenerator generator = new BasicEntityIdGenerator();

			id = Long.valueOf( BasicEntityIdGenerator.getInstance().generateLongId() )/100 * 100;
            // 不会跟上一个重复
            while(last == id){
                id = Long.valueOf( BasicEntityIdGenerator.getInstance().generateLongId() )/100 * 100;
            }
            last = id;
			return id;
		} catch (Exception e) {

			e.printStackTrace();
		}

		throw new IdGenFailException("获取id失败");

	}

    /**
     * 生成string类型主键
     * @return
     */
    public static String genUuid() {
        try {
           return BasicEntityIdGenerator.getInstance().generateLongId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IdGenFailException("获取id失败");
    }

    /**
     * 生成数据库主键
     */
	public static long genTableId() {
        try {
            String str = TableIdGen.getInstance().generateLongId();
            return  Long.valueOf(str);
        } catch (InvalidSystemClockException e) {
            e.printStackTrace();
        } catch (GetHardwareIdFailedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("");
    }

    public static void main(String[] args){
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime dateTime = DateTime.parse("2016-03-30 12:00:00",format);

        log.info(dateTime.getMillis() + "");
        System.out.println( DateTime.now().getMillis() - dateTime.getMillis());
        for(int i=0; i<10;i++)
        System.out.println( IdGenFactory.genTableId() );
    }
}
