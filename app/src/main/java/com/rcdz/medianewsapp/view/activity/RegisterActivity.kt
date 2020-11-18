package com.rcdz.medianewsapp.view.activity

import android.os.Handler
import android.os.Message
import android.widget.*
import com.rcdz.medianewsapp.R
import com.rcdz.medianewsapp.model.bean.BaseBean
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter
import com.rcdz.medianewsapp.persenter.interfaces.GetPhoneCode
import com.rcdz.medianewsapp.persenter.interfaces.ShowRegister
import com.rcdz.medianewsapp.tools.GlobalToast

/**
 * 作用:注册页面
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/12 15:06
 */
public class RegisterActivity :BaseActivity(), GetPhoneCode , ShowRegister {
    lateinit var bt_register: Button
    lateinit var edit_user: EditText
    lateinit var edit_bindphone: EditText
    lateinit var edit_esecode: EditText
    lateinit var edit_pwd: EditText
    lateinit var edit_pwd_sure: EditText
    lateinit var go_login: TextView
    lateinit var getcode: TextView
    lateinit var toolbar_title:TextView;
    lateinit var img_back:ImageView;

     var username=""
     var phone=""
     var esecode=""
     var password=""
     var passwordsure=""
    var vc_time = 60
    lateinit var mHandler_vc:Handler

    override fun setNowActivityName(): String {
       return "注册页面"
    }

    override fun setLayout(): Int {
        return R.layout.activity_register
    }

    override fun inintView() {
        toolbar_title=findViewById(R.id.toolbar_title)
        toolbar_title.text=""
        img_back=findViewById(R.id.img_back)
        bt_register=findViewById(R.id.bt_register)
        getcode=findViewById(R.id.getcode)
        edit_user=findViewById(R.id.edit_user)
        edit_bindphone=findViewById(R.id.edit_bindphone)
        edit_esecode=findViewById(R.id.edit_esecode)
        edit_pwd=findViewById(R.id.edit_pwd)
        edit_pwd_sure=findViewById(R.id.edit_pwd_sure)
        go_login=findViewById(R.id.go_login)

        img_back.setOnClickListener {
            this.finish()
        }
        //获取验证码
        getcode.setOnClickListener {
            val net= NewNetWorkPersenter(this@RegisterActivity);
            phone= edit_bindphone.text.toString()
            if(phone.equals("")||phone==null){
                GlobalToast.show("请填写手机号码", 5000)
                return@setOnClickListener
            }else{
                net.GetPhoneCode(phone, this@RegisterActivity)
            }

        }
        bt_register.setOnClickListener {
            //判断用户名
            if(edit_user.text!=null&&edit_user.text.length>=4){
                username= edit_user.text.toString()
            }else{
                GlobalToast.show("用户名格式不正确", 5000)
                return@setOnClickListener
            }
            //判断手机号
            if(edit_bindphone.text!=null){
                phone= edit_bindphone.text.trim().toString()
            }else{
                GlobalToast.show("手机号码不正确", 5000)
                return@setOnClickListener
            }
            //判断验证码
            if(edit_esecode.text!=null){
                esecode= edit_esecode.text.toString()
            }else{
                GlobalToast.show("验证码有误！", 5000)
                return@setOnClickListener
            }
            //判断密码
            if(edit_pwd.text!=null){
                password= edit_pwd.text.toString()
            }else{
                GlobalToast.show("请输入正确的密码！", 5000)
                return@setOnClickListener
            }
            //判断二次密码
            if(edit_pwd_sure.text!=null){
                passwordsure= edit_pwd_sure.text.toString()
                if(!password.equals(passwordsure)){
                    GlobalToast.show("俩次密码输入不一致！", 5000)
                }
            }else{
                GlobalToast.show("请输入确认密码！", 5000)
                return@setOnClickListener
            }

            val net= NewNetWorkPersenter(this@RegisterActivity);
            net.AppRegister(username, phone, esecode, password, this@RegisterActivity)
        }

        //返回登录
        go_login.setOnClickListener {
            openActivityAndDestoryme(LoginActivity::class.java, null);
        }
    }

    override fun inintData() {
    }

    /**
     * 获取到验证码
     * @param value BaseBean
     */
    override fun getPhoneCode(value: BaseBean?) {
        if(value!!.code==200){
            GlobalToast.show(value.message, 5000)
            changeSmsCodeStyle()
        }else{
            //发送失败
            GlobalToast.show(value.message, 5000)
        }

}
    //倒计时
    open fun changeSmsCodeStyle() {
        vc_time = 60
        //转换获取验证码按钮样式（60s不可重新获取）
        mHandler_vc = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    0 -> {
                        // 完成主界面更新,拿到数据
                        vc_time--
                        getcode.setEnabled(false)
                        getcode.setText("重新获取(" + vc_time + "s)")
                        if (vc_time <= 0) {
                            getcode.setEnabled(true)
                            // getSmsCode.setBackgroundColor(0xffff983d);//0xFF626262   0xFFfc3c17
                            getcode.setBackgroundResource(R.drawable.yzm_button_bg)
                            getcode.setText("获取验证码")
                            getcode.setEnabled(true)
                        }
                    }
                }
            }
        }
        Thread {
            while (true) {
                try {
                    Thread.sleep(1000)
                    // 耗时操作，完成之后发送消息给Handler，完成UI更新；
                    mHandler_vc.sendEmptyMessage(0)
                    if (vc_time <= 0) {

                        break
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
    //注册成功 跳转到登录页面
    override fun showRegister(baseBean: BaseBean?) {
        if(baseBean!!.code==200){
            GlobalToast.show(baseBean.message, Toast.LENGTH_LONG);
            openActivityAndDestoryme(LoginActivity::class.java, null)
        }else{
            GlobalToast.show4(baseBean.message, Toast.LENGTH_LONG);
        }
    }
}