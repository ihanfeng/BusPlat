package com.zhiyin.router;

/**
 * Created by wangqinghui on 2016/12/5.
 */
public class BinaryValueAbstractAction implements BinaryValueAction {
    @Override
    public boolean match() {
        return true;
    }

    @Override
    public Object defaultAction() {
        return false;
    }
}
