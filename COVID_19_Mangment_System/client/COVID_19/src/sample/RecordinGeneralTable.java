package sample;

public class RecordinGeneralTable {

   private String city;
   private int id;
   private int active_cases;
   private int healed_cases;
   private int total_cases;

    public RecordinGeneralTable( int id,String city, int active_cases, int healed_cases) {
        this.city = city;
        this.id = id;
        this.active_cases = active_cases;
        this.healed_cases = healed_cases;
        this.total_cases = this.active_cases+this.healed_cases;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getTotal_cases() {
        return total_cases;
    }

    public void setTotal_cases(int total_cases) {
        this.total_cases = total_cases;
    }

    @Override
    public String toString() {
        return this.city;
    }
}
