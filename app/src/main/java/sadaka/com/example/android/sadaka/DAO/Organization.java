package sadaka.com.example.android.sadaka.DAO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aisha on 8/17/2016.
 */
public class Organization implements Parcelable {

    String name;
    String nameEng;
    String desc;
    String descEng;
    String smsNum;
    String smsText;
    String website;
    String facebook;
    String img;
    String number;

    public Organization(String name,String nameEng,String desc,String descEng,String smsNum,String smsText,String website,String facebook,String img,String number){
        this.name=name;
        this.nameEng=nameEng;
        this.desc=desc;
        this.descEng=descEng;
        this.smsNum=smsNum;
        this.smsText=smsText;
        this.website=website;
        this.facebook=facebook;
        this.img=img;
        this.number=number;
    }
    public Organization(Parcel in){
        this.name=in.readString();
        this.nameEng=in.readString();
        this.desc=in.readString();
        this.descEng=in.readString();
        this.smsNum=in.readString();
        this.smsText=in.readString();
        this.website=in.readString();
        this.facebook=in.readString();
        this.img=in.readString();
        this.number=in.readString();

    }
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.name);
        dest.writeString(this.nameEng);
        dest.writeString(this.desc);
        dest.writeString(this.descEng);
        dest.writeString(this.smsNum);
        dest.writeString(this.smsText);
        dest.writeString(this.website);
        dest.writeString(this.facebook);
        dest.writeString(this.img);
        dest.writeString(this.number);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Organization createFromParcel(Parcel in) {
            return new Organization(in);
        }

        public Organization[] newArray(int size) {
            return new Organization[size];
        }
    };
    public String getName() {
        return name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescEng() {
        return descEng;
    }

    public String getSmsNum() {
        return smsNum;
    }

    public String getSmsText() {
        return smsText;
    }

    public String getWebsite() {
        return website;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getImg() {
        return img;
    }

    public String getNumber() {
        return number;
    }




}
