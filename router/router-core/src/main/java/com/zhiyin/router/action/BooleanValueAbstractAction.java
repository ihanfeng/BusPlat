package com.zhiyin.router.action;

/**
 * Created by wangqinghui on 2016/12/5.
 */
public class BooleanValueAbstractAction implements BooleanValueAction {
    @Override
    public boolean match() {
        return true;
    }

    @Override
    public Object defaultAction() {
        return false;
    }
}
