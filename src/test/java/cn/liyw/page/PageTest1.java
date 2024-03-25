package cn.liyw.page;

import cn.liyw.Application;
import cn.liyw.service.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageTest1 {
    public int lengthOfLongestSubstring(String s) {
        if(s==null ||s.equals("")){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        char[] cs = s.toCharArray();
        int length = 0;
        Set<Character> set = new HashSet();
        for(int i = 0;i<cs.length-1;i++){
            set.clear();
            set.add(cs[i]);
            for(int j = i+1;j<cs.length;j++){
                if(set.contains(cs[j]) ){
                    break;
                }else{
                    set.add(cs[j]);
                }
            }
            if(set.size()>length) {
                length = set.size();
            }
        }
        return length;
    }
    // 获取图像的RGB值；
    public class GetRgb {
        BufferedImage bufferedImage;
        int r,g,b; // 分别用来存放获取的RGB值
        int heigth,width;
        int id = 0;

        public GetRgb(){
            find();
        }

        public void find(){
            // 读取要操作的图片，这里的图片路径请改成自己要处理的图片
            try {
                bufferedImage = ImageIO.read(new File("/Users/liyanwen/Desktop/150.webp"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 获取图片的宽和高；
            heigth = bufferedImage.getHeight();
            width = bufferedImage.getHeight();
            System.out.println("heigth   = "+heigth +", width   ="+width);
            // 采用行优先遍历，先遍历宽
            for (int y = 0; y < heigth; y++) {
                for (int x = 0; x < width; x++) {
                    id++;
                    Color color = new Color(bufferedImage.getRGB(x,y));
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                    System.out.println("此时的id为： "+ id+ "  R =  "+ r + ",  G = "+g+", B ="+b);
                }
            }
        }

    }
    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        assert 0 <= rgbR && rgbR <= 255;
        assert 0 <= rgbG && rgbG <= 255;
        assert 0 <= rgbB && rgbB <= 255;
        int[] rgb = new int[] { rgbR, rgbG, rgbB };
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[] { hsbH, hsbS, hsbB };
    }

    public int[] hsb2rgb(float h, float s, float v) {
        assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;
        assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;
        assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;

        float r = 0, g = 0, b = 0;
        int i = (int) ((h / 60) % 6);
        float f = (h / 60) - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        switch (i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
                r = v;
                g = p;
                b = q;
                break;
            default:
                break;
        }
        return new int[] { (int) (r * 255.0), (int) (g * 255.0),
                (int) (b * 255.0) };
    }

    @Test
    public void T_add() throws Exception {

        float[] result = rgb2hsb(184,182,140);
        System.out.println(GsonUtil.toJson(result));
        int[] ret = hsb2rgb(57.272728f,0.4f,0.6f);

        System.out.println(GsonUtil.toJson(ret));
        final StringBuilder builder = new StringBuilder();
        builder.append("#");
        builder.append(Integer.toHexString(ret[0])); // Real computation here
        builder.append(Integer.toHexString(ret[1])); // Rea// Real computation here
        builder.append(Integer.toHexString(ret[2])); // Reacomputation here
        String str = (builder.toString());
        System.out.println(str);

        int[] rgb = new int[3];
        rgb[0] = Integer.valueOf(str.substring( 1, 3 ), 16);
        rgb[1] = Integer.valueOf(str.substring( 3, 5 ), 16);
        rgb[2] = Integer.valueOf(str.substring( 5, 7 ), 16);
        System.out.println(GsonUtil.toJson(rgb));

    }

}