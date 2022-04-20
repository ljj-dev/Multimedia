package com.example.multimedia;

import static android.content.ContentValues.TAG;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class GetNum {
    private List<PhoneInfo> list;
    private int[] ID=new int[100];
    private int max=0;
    private  int i;
    public List<PhoneInfo> getPhoneNumberFromMobile(Context context) {
        // TODO Auto-generated constructor stub
        list = new ArrayList<PhoneInfo>();
        Cursor cursor = context.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[] { "display_name", "sort_key", "contact_id",
                                "data1" }, null, null, null);
//        moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {

            //读取通讯录的姓名
            @SuppressLint("Range") String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            Log.e(TAG, "getPhoneNumberFromMobile: "+name );
            //读取通讯录的号码
            @SuppressLint("Range") String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.e(TAG, "getPhoneNumberFromMobile: "+number );
            @SuppressLint("Range") int Id = cursor.getInt(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            Log.e(TAG, "getPhoneNumberFromMobile: "+Id );
            for( i=0;i<max;i++){
                if(ID[i]==Id)
                {
                    break;
                }
            }
            if(i==max)
            {
                ID[i]=Id;
                String Sortkey = getSortkey(cursor.getString(1));
                PhoneInfo phoneInfo = new PhoneInfo(name, number,Sortkey,Id);
                list.add(phoneInfo);
            }
        }
        cursor.close();
        return list;
    }

    private static String getSortkey(String sortKeyString) {
        String key =sortKeyString.substring(0,1).toUpperCase();
        if (key.matches("[A-Z]")){
            return key;
        }else
            return "#";
    }
}

