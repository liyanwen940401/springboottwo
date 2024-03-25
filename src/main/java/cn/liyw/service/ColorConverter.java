package cn.liyw.service;

import java.util.Arrays;

public class ColorConverter {
    public static void main(String[] args) {
        int[] rgb = ColorConverter.getRbg("0x000020");
        float[] hsb = ColorConverter.rgb2hsb(rgb[0], rgb[1], rgb[2]);
        int[] rgbResult = ColorConverter.hsb2rgb(hsb[0], 0.4f, 0.6f);
        String backColor = ColorConverter.getRbgToHex(rgbResult);
        System.out.println(backColor);
    }

    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        assert 0 <= rgbR && rgbR <= 255;
        assert 0 <= rgbG && rgbG <= 255;
        assert 0 <= rgbB && rgbB <= 255;
        int[] rgb = new int[]{rgbR, rgbG, rgbB};
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == min) {
            hsbH = 0;
        } else if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[]{hsbH, hsbS, hsbB};
    }

    public static int[] hsb2rgb(float h, float s, float v) {
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
        return new int[]{(int) (r * 255.0), (int) (g * 255.0),
                (int) (b * 255.0)};
    }

    public static String getRbgToHex(int[] rbg) {
        final StringBuilder builder = new StringBuilder();
        builder.append(Integer.toHexString(rbg[0])); // Real computation here
        builder.append(Integer.toHexString(rbg[1])); // Rea// Real computation here
        builder.append(Integer.toHexString(rbg[2])); // Reacomputation here
        return builder.toString();
    }


    public static int[] getRbg(String hexStr) {
        if (hexStr != null && !"".equals(hexStr) && hexStr.length() == 8) {
            int[] rgb = new int[3];
            rgb[0] = Integer.valueOf(hexStr.substring(2, 4), 16);
            rgb[1] = Integer.valueOf(hexStr.substring(4, 6), 16);
            rgb[2] = Integer.valueOf(hexStr.substring(6, 8), 16);
            return rgb;
        }
        return null;
    }
}
