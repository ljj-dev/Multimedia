package com.example.multimedia;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.multimedia.Adapter.ContactsAdapter;
import com.example.multimedia.ContactsOperation.AddContacts;
import com.example.multimedia.ContactsOperation.EditContacts;
import com.example.multimedia.Message.MessageInterface;

import java.util.ArrayList;
import java.util.List;
public  class MainActivity extends Activity implements AdapterView.OnItemLongClickListener {
    private ListView lv;
    private ContactsAdapter adapter;
    private GetNum getPhoneNumberFromMobile;
    private List<PhoneInfo> list = new ArrayList<PhoneInfo>();
    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        permission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.contents_view);
        getPhoneNumberFromMobile = new GetNum();
        list = getPhoneNumberFromMobile.getPhoneNumberFromMobile(this);
        adapter = new ContactsAdapter(list, this);
        lv.setAdapter(adapter);
        lv.setOnItemLongClickListener(this);
    }

    //添加联系人
    public void add(View view) {
        AddContacts addContacts = new AddContacts(this);
        addContacts.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhoneInfo phoneInfo = list.get(position);
                Intent intent = new Intent(MainActivity.this, MessageInterface.class);
                intent.putExtra("name", phoneInfo.getName());
                startActivity(intent);
            }
        });
    }


    //长按对联系人进行编辑、删除
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();
        EditContacts editcontacts=new EditContacts(this, list.get(position).getName(),list.get(position).getNumber());
        editcontacts.show();
        return true;
    }
    @Override
    public void onBackPressed() {   //清除缓存
        list.clear();
        super.onBackPressed();
        return;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void permission(){
        //1.先请求判断是否具有对应权限
        if(ContextCompat.checkSelfPermission
                (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        //根据返回的结果，判断对应的权限是否有。
        {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0);
        }
        if(ContextCompat.checkSelfPermission
                (this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        //根据返回的结果，判断对应的权限是否有。
        {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            0);
        }
        if(ContextCompat.checkSelfPermission
                (this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        //根据返回的结果，判断对应的权限是否有。
        {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.WRITE_CONTACTS},
                            0);
        }
        if(ContextCompat.checkSelfPermission
                (this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        //根据返回的结果，判断对应的权限是否有。
        {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.SEND_SMS},
                            0);
        }
    }

}
