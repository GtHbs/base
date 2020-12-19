package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 实现雪花算法
 *
 * @author lizhao
 * @date 2020/12/1 21:52
 */
public class SnowFlake {

    /**
     * 起始时间为2020-12-01 00:00:00
     */
    private static final Long START_TIMESTAMP = 1606752000000L;

    /**
     * 前41位(不包括第一位)表示当前时间的时间戳
     */
    private static final Long TIMESTAMP_BIT = 41L;
    /**
     * 中间10位表示主机位,可以自定义扩展
     */
    private static final Long MACHINE_BIT = 10L;

    /**
     * 最后12位表示1秒内生成的序号列,每秒可以生成2^12 - 1 = 4096个id(针对于只部署一台机器)
     */
    private static final Long SEQUENCE_BIT = 12L;

    /**
     * 机器id
     */
    private static Long machineId = 0L;

    /**
     * 序列号
     */
    private static Long sequence = 0L;

    /**
     * 上一次生成的时间
     */
    private static Long lastTimestamp = 0L;

    /**
     * 序列号的最大值
     */
    private static final Long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 时间戳需要右移的位数
     */
    private static final Long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 机器码需要左移的位数
     */
    private static final Long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * 根据实际机器配置调整机器码
     */
    static {
        try {
            //获取本机IP
            String s = InetAddress.getLocalHost().getHostAddress();
            String[] strings = s.split("\\.");
            machineId = Long.parseLong(strings[strings.length - 1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static long id() {
        synchronized (SnowFlake.class) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < lastTimestamp) {
                throw new RuntimeException("系统时间回退,请调整系统时间");
            }
            if (currentTimeMillis == lastTimestamp) {
                sequence = (sequence + 1) & MAX_SEQUENCE;
                if (sequence == 0L) {
                    currentTimeMillis = nextMills();
                }
            } else {
                lastTimestamp = 0L;
            }
            lastTimestamp = currentTimeMillis;
            return (currentTimeMillis - START_TIMESTAMP) << TIMESTAMP_LEFT
                    | machineId << MACHINE_LEFT
                    | sequence;
        }
    }

    /**
     * 获取下一秒
     */
    private static long nextMills() {
        long millis = System.currentTimeMillis();
        while (millis <= lastTimestamp) {
            millis = System.currentTimeMillis();
        }
        return millis;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(MAX_SEQUENCE);
        System.out.println(~-1);
    }
}
