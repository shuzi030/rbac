package com.huike.service.impl;

import com.huike.entity.SystemDictionaryItem;
import com.huike.mapper.SystemDictionaryItemMapper;
import com.huike.service.SystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典项业务逻辑实现类
 */
@Service
@Transactional
public class SystemDictionaryItemServiceImpl implements SystemDictionaryItemService {
    
    @Autowired
    private SystemDictionaryItemMapper dictionaryItemMapper;
    
    @Override
    public List<SystemDictionaryItem> listByParentId(Long parentId) {
        return dictionaryItemMapper.listByParentId(parentId);
    }
    
    @Override
    public SystemDictionaryItem getById(Long id) {
        return dictionaryItemMapper.getById(id);
    }
    
    @Override
    public List<SystemDictionaryItem> listBySn(String sn) {
        return dictionaryItemMapper.listBySn(sn);
    }
}
