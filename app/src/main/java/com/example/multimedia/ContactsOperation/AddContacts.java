package com.example.multimedia.ContactsOperation;


import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.multimedia.R;

public class AddContacts extends Dialog {
    EditText newname;
    EditText newpho;
    String name;
    String number;
    Context context;
    public AddContacts(@NonNull Context context) {
        super(context);
        this.context=context;
    }
    protected void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_contacts);
        newname=(EditText) findViewById(R.id.newname);
        newpho=(EditText) findViewById(R.id.newPho);
        Button comf=(Button) findViewById(R.id.comf);
        comf.setOnClickListener(this::comf);
        Button cancel=(Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this::cancel);
    }
    public void comf(View v){
        name= String.valueOf(newname.getText());
        number= String.valueOf(newpho.getText());
        Insert(name,number);
        dismiss();
        Toast.makeText(context, "新建成功", Toast.LENGTH_SHORT).show();
    }
    public void cancel(View v){
        dismiss();
    }
    public void Insert(String name,String number){
        ContentValues values = new ContentValues();
        //首先向 RawContacts.CONTENT_URI 执行一个空值插入，目的是获取系统返回的rawContactId
        Uri rawContactUri = this.getContext().getContentResolver()
                .insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        //往 data 表入姓名数据
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        this.getContext().getContentResolver()
                .insert( ContactsContract.Data.CONTENT_URI, values);
        //往 data 表入电话数据
        values.clear();

        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone
                .CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, number);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE
                , ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        this.getContext().getContentResolver().insert(
                ContactsContract.Data.CONTENT_URI, values);
    }
}