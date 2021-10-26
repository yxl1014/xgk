package com.easy.archiecture.service;

import com.easy.archiecture.dao.CityDao;
import com.easy.archiecture.dao.DevDao;
import com.easy.archiecture.entity.City;
import com.easy.archiecture.entity.Dev;
import com.easy.archiecture.entity.Wendu;
import com.easy.archiecture.tools.GsonUtil;
import com.easy.archiecture.tools.Wendus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class DevServiceImpl {

    @Autowired
    private DevDao devDao;

    @Autowired
    private CityDao cityDao;

    public int addservice(Dev dev) {
        if (devDao.selectByName(dev.getName()) == 1)
            return -2;
        if (!devDao.insert(dev))
            return -1;
        return devDao.findIdByName(dev.getName());
    }

    public void delete(int id) {
        devDao.deletebyid(id);
    }

    public void replace(int id, float temp) {
        devDao.replace(id, temp);
    }

    public void flush() {
        cityDao.flushCitys();
        String path = "/data/book.txt";
        long start = System.currentTimeMillis();//开始时间
        File file = new File(path);
        if (file.isFile()) {
            /*使用Reader家族，表示我要读字符数据了，
             *使用该家族中的BufferedReader，表示我要建立缓冲区读字符数据了。
             * */
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);
                //嵌套使用，装饰者模式，老生常谈。装饰者模式的使用，可以读前面小砖写的《从熏肉大饼到装饰者模式》
                bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                //一行一行读
                while (line != null) { //按行读数据
                    //System.out.println(line);
                    String[] s1 = line.split(",");
                    String citycode = s1[0].split("=")[1];
                    String cityname = s1[1].split("=")[1];
                    cityDao.insertCity(cityname, citycode);
                    line = bufferedReader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //最后一定要关闭
                try {
                    fileReader.close();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long end = System.currentTimeMillis();//结束时间
                System.out.println("传统IO读取数据，不指定缓冲区大小，总共耗时：" + (end - start) + "ms");
            }

        }
        System.out.println("ok");

    }

    public void updateAuto(int id) {
        Dev dev = devDao.findById(id);
        System.out.println(GsonUtil.toJson(dev));
        if (dev.getCity_code() == null) {
            List<City> city = null;
            try {
                city = cityDao.findCodeByName(dev.getCity_name());
            } catch (Exception exception) {
                exception.printStackTrace();
                return;
            }
            dev.setCity_code(city.get(0).getCity_code());
            devDao.setCitycodeByid(dev.getCity_code(), dev.getId());
        }
        Wendu wd = Wendus.getWendu(dev.getCity_code());
        String wd1 = wd.getData().getWendu();
        System.out.println(wd);
    }

    public Dev getDev(int id) {
        return devDao.getById(id);
    }

    public List<String> getAll() {
        return devDao.getAll();
    }
}
