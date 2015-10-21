package com.zhy.blogcodes.mvp.biz;

/**
 * Created by zhy on 15/6/19.
 */
public interface IUserBiz
{
    public void login(String username, String password, OnLoginListener loginListener);
}
