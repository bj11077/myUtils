package accel.csv;


import java.sql.Timestamp;

public class CsvExDto {

    private String name;
    private String type;
    private boolean useYn;
    private int cnt;
    private Timestamp date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUseYn() {
        return useYn;
    }

    public void setUseYn(boolean useYn) {
        this.useYn = useYn;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public CsvExDto(String name, String type, boolean useYn, int cnt, Timestamp date) {
        this.name = name;
        this.type = type;
        this.useYn = useYn;
        this.cnt = cnt;
        this.date = date;
    }
}
