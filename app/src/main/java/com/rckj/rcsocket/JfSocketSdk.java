package com.rckj.rcsocket;
public class JfSocketSdk {
    JfSocketEvent socketEvent=null;
    public  JfSocketSdk(JfSocketEvent event)
    {
        socketEvent=event;
    }
    //启动通迅连接
    public native boolean start(String remotoIp, int port, boolean bAsync);
    //发送数据
    public native boolean send(byte[] buf);
    //关闭通迅
    public native boolean stop();
    //暂停/恢复接收
    public native boolean PauseReceive(boolean bPause);
    //获取该组件对象的连接 ID
    public native long GetConnectionID();
    public native boolean IsConnected();
    //检查通信组件是否已启动
    public native boolean HasStarted();
    //查看通信组件当前状态
    public native int GetState();
    //获取最近一次失败操作的错误代码
    public native int GetLastError();
    //获取最近一次失败操作的错误代码
    public native String GetLastErrorDesc();
    //设置数据报文最大长度（建议在局域网环境下不超过 1472 字节，在广域网环境下不超过 548 字节）
    public native void  SetMaxDatagramSize(int dwMaxDatagramSize);
    //获取数据报文最大长度
    public native int  GetMaxDatagramSize();
    //设置监测包尝试次数（0 则不发送监测跳包，如果超过最大尝试次数则认为已断线）
    public native void   SetDetectAttempts(int dwDetectAttempts);
    //设置监测包发送间隔（毫秒，0 不发送监测包）
    public native void   SetDetectInterval(int dwDetectInterval);
    //获取心跳检查次数
    public native int   GetDetectAttempts();
    //获取心跳检查间隔
    public native int   GetDetectInterval();
    //设置是否开启 nodelay 模式（默认：FALSE，不开启）
    public native void   SetNoDelay(boolean bNoDelay);
    //设置是否关闭拥塞控制（默认：FALSE，不关闭）
    public native void   SetTurnoffCongestCtrl(boolean bTurnOff);
    //设置数据刷新间隔（毫秒，默认：60）
    public native void   SetFlushInterval(int dwFlushInterval);
    //设置快速重传 ACK 跨越次数（默认：0，关闭快速重传） */
    public native void   SetResendByAcks(int dwResendByAcks);
    //设置发送窗口大小（数据包数量，默认：128）
    public native void   SetSendWndSize(int dwSendWndSize);
    //设置接收窗口大小（数据包数量，默认：512）
    public native void   SetRecvWndSize(int dwRecvWndSize);
    //设置最小重传超时时间（毫秒，默认：30）
    public native void   SetMinRto(int dwMinRto);
    //设置最大传输单元（默认：0，与 SetMaxDatagramSize() 一致）
    public native void   SetMaxTransUnit(int dwMaxTransUnit);
    //设置最大数据包大小（默认：4096） ）
    public native void   SetMaxMessageSize(int dwMaxMessageSize);
    //设置握手超时时间（毫秒，默认：5000）
    public native void   SetHandShakeTimeout(int dwHandShakeTimeout);
    //检测是否开启 nodelay 模式
    public native boolean   IsNoDelay();
    //检测是否关闭拥塞控制
    public native boolean   IsTurnoffCongestCtrl();
    //获取数据刷新间隔
    public native int   GetFlushInterval();
    //获取快速重传 ACK 跨越次数
    public native int   GetResendByAcks();
    //获取发送窗口大小
    public native int   GetSendWndSize();
    //获取接收窗口大小
    public native int   GetRecvWndSize();
    //获取最小重传超时时间
    public native int   GetMinRto();
    //获取最大传输单元
    public native int   GetMaxTransUnit();
    //获取最大数据包大小
    public native int   GetMaxMessageSize();
    //获取握手超时时间
    public native int   GetHandShakeTimeout();
    //获取等待发送包数量
    public native boolean   GetWaitingSendMessageCount(int iCount);
   //重新打包注册事件
    public void RegEvents(JfSocketEvent event)
    {
        socketEvent=event;
    }
    //连接事件
    public boolean OnConnect(long dwConnID)
    {
        socketEvent.OnConnect(dwConnID);
        return true;
    }
    //握手事件
    public boolean OnHandShake(long dwConnID)
    {
        socketEvent.OnHandShake(dwConnID);
        return true;
    }
    //连接前事件
    public boolean OnPrepareConnect(long dwConnID)
    {
        socketEvent.OnPrepareConnect(dwConnID);
        return true;
    }
    //接收数据事件
    public boolean OnReceive(long dwConnID,byte[] buf,int len)
    {
        socketEvent.OnReceive(dwConnID,buf,len);
        return true;
    }
    //发送数据事件
    public boolean OnSend(long dwConnID,byte[] buf,int len)
    {
        socketEvent.OnSend(dwConnID,buf,len);
        return false;
    }
    //关闭通迅事件
    public boolean OnClose(long dwConnID,int enOperation,int iErrorCode)
    {
        socketEvent.OnClose(dwConnID,enOperation,iErrorCode);
        return true;
    }
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("gjSocket");
    }
}
