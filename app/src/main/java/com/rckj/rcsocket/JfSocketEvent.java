package com.rckj.rcsocket;

public interface JfSocketEvent {


    public boolean OnConnect(long dwConnID);
    public boolean OnHandShake(long dwConnID);
    public boolean OnPrepareConnect(long dwConnID);
    public boolean OnReceive(long dwConnID, byte[] buf, int len);
    public boolean OnSend(long dwConnID, byte[] buf, int len);
    public boolean OnClose(long dwConnID, int enOperation, int iErrorCode);
}
