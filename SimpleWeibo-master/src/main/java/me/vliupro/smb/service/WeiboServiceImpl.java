package me.vliupro.smb.service;

import me.vliupro.smb.dao.WeiboDao;
import me.vliupro.smb.dao.WeiboDaoImpl;
import me.vliupro.smb.po.Weibo;
import me.vliupro.smb.utils.Page;

import java.util.*;

/**
 * Created by vliupro on 16-5-31.
 */
public class WeiboServiceImpl implements WeiboService {

    private WeiboDao wd = new WeiboDaoImpl();

    @Override
    public boolean publish(Weibo weibo) {
        if (weibo.isOriginal()) {
            return wd.addWeibo(weibo);
        } else {
            return wd.addForwardWeibo(weibo);
        }
    }

    @Override
    public boolean deleteById(int weiboId) {
        return wd.deleteWeibo(weiboId);
    }

    @Override
    public Weibo getWeiboById(int weiboId) {
        return wd.getWeiboById(weiboId);
    }

    @Override
    public Page<Weibo> getWeibosByPage(int pageNum, int total) {
        Page<Weibo> weiboPage = new Page<>();
        weiboPage.setCurrentPage(pageNum);
        weiboPage.setEveryPage(total);
        weiboPage.setTotalCount(wd.getTotalNum());
        weiboPage.setTotalPage(weiboPage.getTotalCount() % total == 0 ?
                weiboPage.getTotalCount() / total : weiboPage.getTotalCount() / total + 1);
        weiboPage.setHasNextPage(weiboPage.getCurrentPage() < weiboPage.getTotalPage());
        weiboPage.setHasPrePage(weiboPage.getCurrentPage() > 1);
        List<Weibo> weibos = wd.getWeibosLimited((pageNum - 1) * total, total);
        Collections.sort(weibos);
        weiboPage.setItems(weibos);
        return weiboPage;
    }

    @Override
    public List<Weibo> getWeibosByUserId(int userId) {
        return wd.getWeibosByUserId(userId);
    }

    @Override
    public Page<Weibo> getWeibosByListUserIds(List<Integer> userIds, int pageNum, int total) {
        Page<Weibo> weiboPage = new Page<>();
        Set<Weibo> weibos = new HashSet<>();
        weiboPage.setCurrentPage(pageNum);
        weiboPage.setEveryPage(total);
        for (int i = 0 ; i < userIds.size() ; i ++) {
            weibos.addAll(this.getWeibosByUserId(userIds.get(i)));
        }
        List<Weibo> weibosList = new ArrayList<>(weibos);
        //倒序排序
        Collections.sort(weibosList);
        weiboPage.setTotalCount(weibosList.size());
        weiboPage.setTotalPage(weiboPage.getTotalCount() % total == 0 ?
                weiboPage.getTotalCount() / total : weiboPage.getTotalCount() / total + 1);
        weiboPage.setHasNextPage(weiboPage.getCurrentPage() < weiboPage.getTotalPage());
        weiboPage.setHasPrePage(weiboPage.getCurrentPage() > 1);
        //如果当前页在1～end-1之间，
        if (weiboPage.getCurrentPage() < weiboPage.getTotalPage() && weiboPage.getCurrentPage() >= 1) {
            weiboPage.setItems(weibosList.subList((pageNum - 1) * total, (pageNum) * total));
        } else {
            weiboPage.setItems(weibosList.subList((pageNum - 1) * total, weiboPage.getTotalCount()));
        }
        return weiboPage;
    }

    @Override
    public int getNumIfUserWeibo(int userId) {
        return wd.getUserWeiboNum(userId);
    }

    @Override
    public Map<String, Integer> getNumOfForwardWeibo(List<Integer> weiboIds) {
        Map<String, Integer> numMap = new HashMap<>();
        for (int weiboId : weiboIds) {
            numMap.put(String.valueOf(weiboId), wd.getForwardWeiboNum(weiboId));
        }
        return numMap;
    }

    @Override
    public Page<Weibo> searchWeibos(String content, int pageNum, int total) {
        Page<Weibo> weiboPage = new Page<>();
        weiboPage.setCurrentPage(pageNum);
        weiboPage.setEveryPage(total);

        List<Weibo> weibos = wd.searchWeibos(content, pageNum, total);
        weiboPage.setTotalCount(weibos.size());
        weiboPage.setTotalPage(weiboPage.getTotalCount() % total == 0 ?
                weiboPage.getTotalCount() / total : weiboPage.getTotalCount() / total + 1);
        weiboPage.setHasNextPage(weiboPage.getCurrentPage() < weiboPage.getTotalPage());
        weiboPage.setHasPrePage(weiboPage.getCurrentPage() > 1);
        Collections.sort(weibos);
        weiboPage.setItems(weibos);
        return weiboPage;
    }

    public static void main(String[] args) {
        WeiboServiceImpl ws = new WeiboServiceImpl();
        Page<Weibo> weiboPage = ws.getWeibosByPage(1, 10);
        for (Weibo weibo : weiboPage.getItems()) {
            System.out.println(weibo);
        }
    }
}
