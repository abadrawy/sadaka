package sadaka.com.example.android.sadaka.activities;

/**
 * Created by aisha on 1/18/2017.
 */
public class HistoryListItem {
    String orgName;
    String date;
    int amount;

    public HistoryListItem() {

    }

    public HistoryListItem(String orgName, String date, int amount) {
        this.orgName = orgName;
        this.amount = amount;
        this.date = date;

    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
