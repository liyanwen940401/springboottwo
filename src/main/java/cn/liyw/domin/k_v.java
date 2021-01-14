package cn.liyw.domin;


public class k_v {
    private String name;
    private String value;

    public k_v(String s,String v){
        this.name=s;
        this.value=v;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
