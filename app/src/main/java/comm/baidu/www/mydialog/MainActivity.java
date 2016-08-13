package comm.baidu.www.mydialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<Map<String,Object>> list;
    private List<Map<String, Object>> adapteRlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

    }
    public void click(View view){
        switch (view.getId()){
            case R.id.button1://--------普通的dialog--------------
                //1,获取对话框对象
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                //4,设置图标
                dialog.setIcon(R.mipmap.ic_launcher);
                //2,设置标题
                dialog.setTitle("标题--写好了吗？");
                //3,设置内容
                dialog.setMessage("这里是dialog的正文内容");
                //5,进行创建
                dialog.create();
                //6,进行展示
                dialog.show();
                break;
            case R.id.button2://------有选择按钮的dialog
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                dialog2.setIcon(R.mipmap.ic_launcher);
                dialog2.setTitle("有选择按钮的dialog");
                dialog2.setMessage("韩雄伟在学习有选择按钮的dialog");
                //设置取消按钮
                dialog2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                });
                //设置中立按钮
                dialog2.setNeutralButton("一会再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"一会再说",Toast.LENGTH_SHORT).show();
                    }
                });
                //设置确定按钮
                dialog2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog2.create();
                dialog2.show();

                break;
            case R.id.button3://带列表的dialog
                AlertDialog.Builder dialog3 = new AlertDialog.Builder(MainActivity.this);
                dialog3.setIcon(R.mipmap.ic_launcher).setTitle("带列表的dialog");

                //有了列表就不要设置正文信息，否则列表出不来
                final String []item = getResources().getStringArray(R.array.liebiao);
                dialog3.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"即将跳转到"+item[i]+"界面",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog3.create().show();

                break;
            case R.id.button4://带adapter的dialog
                //1,获取对话框对象
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setTitle("标题").setIcon(R.drawable.ic_launcher).setMessage(message)
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("标题");

                list = new ArrayList<Map<String,Object>>();
                int[] imgId= {R.mipmap.xianhua,R.mipmap.xianhua,
                        R.mipmap.xianhua,R.mipmap.xianhua,
                        R.mipmap.xianhua};
                for(int i=0;i<imgId.length;i++){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("img", imgId[i]);
                    map.put("items", "item"+i);
                    list.add(map);

                }

                SimpleAdapter simpleAdapter = new SimpleAdapter
                        (MainActivity.this, list, R.layout.item,
                                new String[]{"img","items"},
                                new int[]{R.id.image_item,R.id.text_item});
                builder.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, list.get(i).get("items")+"", Toast.LENGTH_SHORT).show();
                    }
                });

                // 添加正能量按钮 积极按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                //5,进行创建
                builder.create();
                //6,进行展示
                builder.show();
                break;
            case R.id.button4_1://------带adapter的dialog
                AlertDialog.Builder dialog4 = new AlertDialog.Builder(MainActivity.this);
                dialog4.setIcon(R.mipmap.ic_launcher);
                dialog4.setTitle("带adapter的dialog");
                adapteRlist = new LinkedList<>();
                int []imageid = new int[]{R.mipmap.xianhua,R.mipmap.ic_launcher,R.mipmap.xianhua,
                        R.mipmap.ic_launcher,R.mipmap.xianhua,R.mipmap.ic_launcher};
                for(int i = 0;i<imageid.length;i++){
                    Map<String,Object> map = new HashMap<>();
                    map.put("imageid",imageid[i]);
                    map.put("textid","hanxiongwei"+i);
                    adapteRlist.add(map);
                }
                SimpleAdapter adapter = new SimpleAdapter(this,adapteRlist,R.layout.item,
                        new String[]{"imageid","textid"},new int[]{R.id.image_item,R.id.text_item});
                dialog4.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, adapteRlist.get(i).get("textid")+"", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog4.create();
                dialog4.show();

                break;
            case R.id.button5://带单选item的dialog
                AlertDialog.Builder dialog5 = new AlertDialog.Builder(MainActivity.this);
                dialog5.setIcon(R.mipmap.xianhua).setTitle("带单选item的dialog");

                ////参数1:数据源  参数2: 默认选中 那个item   参数3 :点击事件
                dialog5.setSingleChoiceItems(R.array.liebiao, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,i+"",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog5.create();
                dialog5.show();

                break;
            case R.id.button6://带多选item的dialog
                AlertDialog.Builder dialog6 = new AlertDialog.Builder(MainActivity.this);
                dialog6.setIcon(R.mipmap.xianhua).setTitle("带多选item的dialog");

                //1,数据源  2,多选的item  被没被选中   3,监听事件
                dialog6.setMultiChoiceItems(R.array.liebiao, new boolean[]{true,false,false,false,false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        Toast.makeText(MainActivity.this,i+"",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog6.create();
                dialog6.show();

                break;

            case R.id.button7://
                AlertDialog.Builder dialog7 = new AlertDialog.Builder(MainActivity.this);
                dialog7.setIcon(R.mipmap.xianhua).setTitle("自定义的dialog");

                View view2 = LayoutInflater.from(this).inflate(R.layout.item2,null);
                final EditText editText1 = (EditText) view2.findViewById(R.id.editText1);
                final EditText editText2 = (EditText) view2.findViewById(R.id.editText2);
                dialog7.setView(view2);
                dialog7.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"账号："+editText1.getText().toString().trim()+
                                ",密码："+editText2.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                    }
                });

                dialog7.create();
                dialog7.show();

                break;

            case R.id.button8:
                ////参数1:上下文  2:回调   3,年 4,月 5,日
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                Toast.makeText(MainActivity.this,i+"年"+i1+"月"+i2+"日",Toast.LENGTH_SHORT).show();
                            }
                        },2016,7,14);

                datePickerDialog.create();
                datePickerDialog.show();

                break;
            case R.id.button9:
                //1:上下文  2,回调 3:小时  4:分钟  5是否是24小时制
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Toast.makeText(MainActivity.this,"时间："+i+"时"+i1+"分",Toast.LENGTH_SHORT).show();
                    }
                },21,38,true);
              //  timePickerDialog.create();
                timePickerDialog.show();
                break;



        }

    }
}
