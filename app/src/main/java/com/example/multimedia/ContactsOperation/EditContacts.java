package com.example.multimedia.ContactsOperation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.multimedia.R;

public class EditContacts extends Dialog {
    private String name;
    private Context context;
    private String number;
    public EditContacts(@NonNull Context context, String name, String number) {
        super(context);
        this.context=context;
        this.name=name;
        this.number=number;
    }
    protected void onCreate(Bundle onSaveInstanceState){
        super.onCreate(onSaveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_contacts);
        Button edit=(Button) findViewById(R.id.edit);
        edit.setOnClickListener(this::edit);
        Button delete=(Button) findViewById(R.id.delete);
        delete.setOnClickListener(this::delete);
        TextView editname=(TextView) findViewById(R.id.editname);
        editname.setText((CharSequence) name);
    }
    public void edit(View v){
        Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
        dismiss();
        EditContacts2 editContacts2=new EditContacts2(context, name, number);
        editContacts2.show();
    }
    public void delete(View v){
        getContext().getContentResolver().delete(ContactsContract.Data.CONTENT_URI,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"=?",
                new String[]{name});
        Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
        dismiss();
    }
}
