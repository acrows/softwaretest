package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//读取properties文件的工具类
public class PropertiesUtil {
    /**读取文件的静态方法
     * @param path 文件路径(properties所在路径)
     */
    public static Map<String,String> getProperties(String path){
        Properties prop = new Properties();
        Map<String,String> result = new HashMap<>();
        try {
            //读取jdbc.properties文件内容到prop中，将文件中的键值对以键值的方式存储到prop对象中
            prop.load(new FileInputStream(path));
            Set<Map.Entry<Object,Object>> set = prop.entrySet();
            for (Map.Entry<Object,Object> e:set){
                //将键值对存放到Map中
                result.put(String.valueOf(e.getKey()),String.valueOf(e.getValue()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //返回Map,Map中保存的就是dirver,url,user，password四个键值对
        return result;
    }
}
