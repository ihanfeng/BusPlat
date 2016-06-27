package com.zhiyin.search.es.module.content.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import com.zhiyin.search.es.config.HttpUrlConfig;
import com.zhiyin.search.es.module.C2sSearchParm;
import com.zhiyin.search.es.module.ContentCommonC2s;
import com.zhiyin.search.es.module.S2cContentInfo;
import com.zhiyin.search.es.module.S2cContentSearchResult;
import com.zhiyin.search.es.module.content.entity.ContentInfoMapping;
import com.zhiyin.search.es.module.content.model.DeleteAllContentIndexC2s;
import com.zhiyin.search.es.module.content.service.IContentInfoService;


import com.zhiyin.search.es.util.BeanMapper;
import com.zhiyin.search.es.web.BasicContentS2c;
import com.zhiyin.search.es.web.IdC2s;
import com.zhiyin.search.es.web.S2cObj;
import com.zhiyin.search.es.web.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/contents")
@Api(value = "ContentAPI", description = "内容接口")
public class ContentController extends BaseController {

	@Resource
	private IContentInfoService contentInfoService;

    @Autowired
    private HttpUrlConfig httpUrlConfig;

    @ApiOperation( value = "根据Id查询内容", notes = "根据Id查询内容", response = BasicContentS2c.class )
    @RequestMapping(value = "/selById", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WebResponse<S2cObj> selById(@RequestBody IdC2s c2s) {

        ContentInfoMapping result = contentInfoService.findOne(c2s.getId());

        BasicContentS2c s2c = BeanMapper.map(result, BasicContentS2c.class);

        return succRet(s2c);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
	public WebResponse<S2cContentSearchResult> useRequestParam(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "page", required = false,defaultValue="1") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		log.info("/contents/search -> pageNumber = {},{}", page,query);

		C2sSearchParm req = new C2sSearchParm();
		req.setP(page);
		req.setS(size);
		req.setQuery(query);

		return getProcess(req);
	}

	@ApiOperation( value = "内容搜索", notes = "POST请求内容搜索", response = S2cContentSearchResult.class )
	@RequestMapping(value = "/search", method = RequestMethod.POST ,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public WebResponse<S2cContentSearchResult> search(@RequestBody C2sSearchParm searchParm) {
		log.info("search content: {}", JSON.toJSONString(searchParm));
		return getProcess(searchParm);
	}

    /*
    @ApiOperation( value = "搜索内容", notes = "搜索内容", response = S2cContentSearchResult.class )
    @RequestMapping(value = "/searchnew", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  WebResponse<S2cContentSearchResult> searchnew(@RequestBody C2sSearchParm searchParm) {

		if(searchParm.getP() < 1){
			searchParm.setP(1);
		}

		int page = searchParm.getP() - 1;
		if(page<0){
			page = 0;
		}
		if(searchParm.getS()<=0){
			searchParm.setS(2);
		}

		Pageable pageable = new PageRequest(page, searchParm.getS());
		Page<ContentInfoMapping> searchResult = contentInfoService.findByDescription(searchParm.getQuery(), pageable);

		S2cContentSearchResult result = new S2cContentSearchResult();

		result.setCurrentPage(searchParm.getP());

		result.setPageSize(searchParm.getS());
		result.setTotalPage(searchResult.getTotalPages());

		List<ContentInfoMapping> contentList = searchResult.getContent();
		List<S2cContentInfo> contentConvList = BeanMapper.mapList(contentList, S2cContentInfo.class);

		result.setContents(contentConvList);

		return new WebResponse<S2cContentSearchResult>(HttpStatus.OK.value(),"",result);
	}
    */

	public WebResponse<S2cContentSearchResult> getProcess(C2sSearchParm searchParm){
		//对于客户端来说，默认的页数是第一页;对于es，默认页数是0
		int page = searchParm.getP() - 1;
		if(page<0){
			page = 0;
			searchParm.setP(page);
		}
		if(searchParm.getS()<=0){
			searchParm.setS(10);
		}

		Pageable pageable = new PageRequest(page, searchParm.getS());
		Page<ContentInfoMapping> searchResult = contentInfoService.search( searchParm.getRoleId(), searchParm.getQuery(), pageable);

		S2cContentSearchResult result = new S2cContentSearchResult();

		result.setCurrentPage(searchParm.getP());

		result.setPageSize(searchParm.getS());
		result.setTotalPage(searchResult.getTotalPages());

		List<ContentInfoMapping> contentList = searchResult.getContent();

		List<S2cContentInfo> contentConvList = BeanMapper.mapList(contentList, S2cContentInfo.class);

//		List<S2cContentInfo> newContentConvList = Lists.newArrayList();
//		for(S2cContentInfo tmp : contentConvList){
//			if(StringUtils.isEmpty(tmp.getSavePath())){
//				log.error("search result's savepath not exist, result info:{}",JSON.toJSON(tmp));
//			}else{
//				tmp.setSavePath(tmp.getSavePath());
//				newContentConvList.add(tmp);
//			}
//		}

		result.setContents(contentConvList);
		log.info("search result size:{}",contentConvList.size());

		return new WebResponse<S2cContentSearchResult>(HttpStatus.OK.value(),"",result);
	}

	/**
	 * 添加内容索引,需要内容全部信息，一般不用。参考addOrUpdateById
	 * @param
	 * @return
	 */
    @ApiOperation( value = "添加内容", notes = "添加内容，尽量不要使用", response = String.class )
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	public  WebResponse<String> addOrUpdate(@RequestBody ContentCommonC2s c2s) {
		log.info("add content request body:{}",JSON.toJSONString(c2s));

		ContentInfoMapping content = new  ContentInfoMapping();
		BeanMapper.copy(c2s,content );

		ContentInfoMapping ret = contentInfoService.save(content);
		log.info("add content ret:{}",JSON.toJSONString(ret));
		return new WebResponse<String>(HttpStatus.OK.value(),"",null);
	}

	/**
	 * 添加或者更新索引
	 * @param reqContent
	 * @return
	 */
    @ApiOperation(value = "添加或者更新索引", nickname = "", response = BasicContentS2c.class)
    @RequestMapping(value = "/addOrUpdateById", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public  WebResponse<S2cObj> addOrUpdateById(@RequestBody ContentCommonC2s reqContent) {
		log.info("add content request content id:{}", reqContent.getId());

        BasicContentS2c basicContent = null;
        try {
            Map<String,Long> map = Maps.newHashMap();
            map.put("id",reqContent.getId());

//            basicContent = HttpRequestFactory.post(httpUrlConfig.getContentsSelById(), map, BasicContentS2c.class);

            if(basicContent == null){
				log.error("index content is null.");
                return failReqRet();
            }
        } catch (Exception e) {
			log.error("get content info null," ,e);
			return failRet( e.getMessage() );
		}

		ContentInfoMapping mapping = contentInfoService.save( BeanMapper.map( basicContent , ContentInfoMapping.class ) );

        if( mapping == null ){
            log.error("content add es index error.");
            return failReqRet();
        }

        BasicContentS2c s2c = BeanMapper.map(basicContent, BasicContentS2c.class);
		return succRet(s2c);
	}

	/**
	 * 删除内容索引
	 */
	@RequestMapping(value = "/delById", method = RequestMethod.POST)
	public  WebResponse<String> delById(@RequestBody ContentCommonC2s content) {
		log.debug("del book request body:{}",JSON.toJSONString(content));

		if(content == null || content.getId() == 0 ){
			return new WebResponse<String>(HttpStatus.BAD_REQUEST.value(),"",null);
		}

		contentInfoService.delete( content.getId() );

		return new WebResponse<String>(HttpStatus.OK.value(),"",null);
	}

    /**
     * 清空所有内容索引，非常危险
     */
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    public  WebResponse<String> deleteAll(@RequestBody DeleteAllContentIndexC2s c2s) {
        if(!"HG".equals(c2s.getAccCode())){
            return new WebResponse<String>(HttpStatus.BAD_REQUEST.value(),"",null);
        }

        log.error("delete all content index.");
        contentInfoService.deleteAll();

        return new WebResponse<String>(HttpStatus.OK.value(),"",null);
    }

}
