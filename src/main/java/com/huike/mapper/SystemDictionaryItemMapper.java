package com.huike.mapper;

import com.huike.entity.SystemDictionaryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据字典项数据访问接口
 */
public interface SystemDictionaryItemMapper {
    
    /**
     * 根据父ID查询字典项列表
     * @param parentId 父ID
     * @return 字典项列表
     */
    List<SystemDictionaryItem> listByParentId(@Param("parentId") Long parentId);
    
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
    List<SystemDictionaryItem> listBySn(@Param("sn") String sn);
}
