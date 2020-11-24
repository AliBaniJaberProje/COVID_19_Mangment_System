package sample;

public class RecordForCity {
    private int id;
    private int active_cases;
    private int healed_cases;
    private String date_created;
    private String update_date;

    public RecordForCity(int id, int active_cases, int healed_cases, String date_created, String update_date) {
        this.id = id;
        this.active_cases = active_cases;
        this.healed_cases = healed_cases;
        this.date_created = date_created;
        this.update_date = update_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActive_cases() {
        return active_cases;
    }

    public void setActive_cases(int active_cases) {
        this.active_cases = active_cases;
    }

    public int getHealed_cases() {
        return healed_cases;
    }

    public void setHealed_cases(int healed_cases) {
        this.healed_cases = healed_cases;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
}
