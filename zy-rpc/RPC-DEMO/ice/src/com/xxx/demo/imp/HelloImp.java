package com.xxx.demo.imp;

import Ice.Communicator;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import IceBox.Service;
import com.xxx.demo.demo.Order;
import com.xxx.demo.demo._MyServiceDisp;

/**
 * Created by magicdoom on 2015/7/11.
 */
public class HelloImp extends _MyServiceDisp   implements Service
{
    private Logger log;
    private ObjectAdapter _adapter;


    @Override
    public void start(String name, Communicator communicator, String[] args) {
        this.log = communicator.getLogger().cloneWithPrefix(name);
        _adapter = communicator.createObjectAdapter(name);
        Ice.Object object = this;
        _adapter.add(object, communicator.stringToIdentity(name));
        _adapter.activate();
        log.trace("control", " started ");
    }

    @Override
    public void stop() {
        log.trace("control", " stoped ");
        _adapter.destroy();

    }

    @Override
    public Order hello(Order in, Current __current)
    {
        return in;
    }
}
