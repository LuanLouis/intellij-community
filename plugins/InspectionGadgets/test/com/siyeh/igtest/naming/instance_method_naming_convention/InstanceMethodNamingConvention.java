package com.siyeh.igtest.naming.instance_method_naming_convention;

public class InstanceMethodNamingConvention implements Runnable
{
    public void <warning descr="Instance method name 'UpperaseMethod' doesn't match regex '[a-z][A-Za-z\d]*'">UpperaseMethod</warning>()
    {

    }

    public void methodNameEndingIn2()
    {

    }

    public void <warning descr="Instance method name 'foo' is too short">foo</warning>()
    {

    }

    public void <warning descr="Instance method name 'methodNameTooLoooooooooooooooooooooooooooooooooooooooooooooong' is too long">methodNameTooLoooooooooooooooooooooooooooooooooooooooooooooong</warning>()
    {

    }

    public void run(){
    }

    private native void a();
}
