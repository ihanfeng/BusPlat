package com.zhiyin.frame.idgen;

import org.predictor.idgenerator.EntityIdGenerator;
import org.predictor.idgenerator.GetHardwareIdFailedException;
import org.predictor.idgenerator.InvalidSystemClockException;

/**
 * BasicEntityIdGenerator
 *
 * @author Maxim Khodanovich
 * @version 21.01.13 17:16
 *          <p/>
 *          id is composed of:
 *          time - 41 bits (millisecond precision w/ a custom epoch gives us 69 years)
 *          configured machine id - 10 bits - gives us up to 1024 machines
 *          sequence number - 12 bits - rolls over every 4096 per machine (with protection to avoid rollover in the same ms)
 *
 *
 */

public class TableIdGen implements EntityIdGenerator {

//   id format  =>
//   timestamp |datacenter | sequence
//   41        |5         |  7
    private final long sequenceBits = 7;
    private final long datacenterIdBits = 5;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private final long datacenterIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + datacenterIdBits;

//  private final long twepoch = 1288834974657L;
    private final long twepoch = 1459310400000L;//"2016-03-30 12:00:00" 领证日期

    private final long datacenterId;
    private final long sequenceMax = 128;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private static TableIdGen basicEntityIdGenerator;

    public static TableIdGen getInstance() throws GetHardwareIdFailedException {
        if (basicEntityIdGenerator == null) basicEntityIdGenerator = new TableIdGen();
        return basicEntityIdGenerator;
    }

    private TableIdGen() throws GetHardwareIdFailedException {
        datacenterId = getDatacenterId();
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new GetHardwareIdFailedException("datacenterId > maxDatacenterId");
        }
    }

    @Override
    public synchronized String generateLongId() throws InvalidSystemClockException {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new InvalidSystemClockException("Clock moved backwards.  Refusing to generate id for " + (
                    lastTimestamp - timestamp) + " milliseconds.");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                sequence;
        return id.toString();
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    protected long getDatacenterId() throws GetHardwareIdFailedException {

        return 0L;
//        try {
//            InetAddress ip = InetAddress.getLocalHost();
//            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//            long id;
//            if (network == null) {
//                id = 1;
//            } else {
//                byte[] mac = network.getHardwareAddress();
//                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
//            }
//            return id;
//        } catch (SocketException e) {
//            throw new GetHardwareIdFailedException(e);
//        } catch (UnknownHostException e) {
//            throw new GetHardwareIdFailedException(e);
//        }
    }
}
