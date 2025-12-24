package com.huike.service;

import com.huike.entity.SystemDictionaryItem;

import java.util.List;

/**
 * 数据字典项业务逻辑接口
 */
public interface SystemDictionaryItemService {
    
    /**
     * 根据父ID查询字典项列表
     * @param parentId 父ID
     * @return 字典项列表
     */
    List<SystemDictionaryItem> listByParentId(Long parentId);
    
    /**
     * 根据ID查询字典项
     * @param id 字典项ID
     * @return 字典项对象
     */
    SystemDictionaryItem getById(Long id);
    
    /**
     * 根据字典SN查询字典项列表
     * @param sn 字典SN (如: job, source等)
     * @return 字典项列表
     */
    List<SystemDictionaryItem> listBySn(String sn);
}
