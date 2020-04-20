package cn.jianchen.com.trade.api.sort.service;


import cn.jianchen.com.trade.api.product.qo.ProductQo;
import cn.jianchen.com.trade.api.sort.model.Sort;
import cn.jianchen.com.trade.api.sort.qo.SortQo;
import cn.jianchen.com.trade.api.sort.repository.ISortRepository;
import cn.jianchen.com.trade.common.cache.CacheOptions;
import cn.jianchen.com.trade.common.cache.KvCacheFactory;
import cn.jianchen.com.trade.common.cache.KvCacheWrap;
import cn.jianchen.com.trade.common.entity.Constants;
import cn.jianchen.com.trade.common.exception.ServiceException;
import cn.jianchen.com.trade.common.util.StringUtils;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.ListModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.jianchen.com.trade.common.entity.Constants.*;
import static cn.jianchen.com.trade.common.exception.ErrorCode.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-11-01 10:05
 **/

@Service
public class SortService implements ISortService {

    @Autowired
    private ISortRepository iSortRepository;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Byte, List<Sort>> sortCache;

    public Sort getById(Integer sortId) throws ServiceException {

        Sort sort = iSortRepository.findById(sortId).orElse(null);

        if (sort == null) {
            throw new ServiceException(ERR_DATA_NOT_FOUND);
        }

        return sort;
    }

    @Override
    public List<Sort> sorts(boolean adm) {
        return sortCache.findByKey(adm ? 0 : STATUS_OK);
    }

    @Override
    public void save(Sort sort) throws ServiceException {

        validateProductCategory(sort);

        Integer id = sort.getId();
        if (id == null || id == ZERO_INT) {
            iSortRepository.save(sort);
            clearExamQuestionTypes();
        } else {
            Sort of = sort(id);
            of.setSequence(sort.getSequence());
            of.setName(sort.getName());
            of.setParentId(sort.getParentId());
            of.setPriority(sort.getPriority());
            iSortRepository.save(of);
            clearExamQuestionTypes();
        }
    }

    @Override
    public void status(int id, byte status) throws ServiceException {
        if (!(status == STATUS_OK || status == STATUS_HALT)) {
            throw new ServiceException(ERR_PERMISSION_DENIED);
        }
        Sort sort = sort(id);
        if (sort != null) {
            String sequence = sort.getSequence();
            if (sequence.endsWith("0000")) {
                sequence = sequence.substring(0, 2);
            } else if (sequence.endsWith("00")) {
                sequence = sequence.substring(0, 4);
            }
            iSortRepository.status(status, sequence);
            clearExamQuestionTypes();
        }
    }

    @Override
    @Transactional
    public void remove(int id) throws ServiceException {
        iSortRepository.deleteById(id);
        clearExamQuestionTypes();
    }

    @Override
    public Sort sort(int id) throws ServiceException {
        Sort type = getById(id);
        if (type == null || type.getId() == null) {
            throw new ServiceException(ERR_PARENTID_VALID_DENIED);
        }
        return type;
    }

    private void clearExamQuestionTypes() {
        sortCache.remove(ZERO_BYTE);
        sortCache.remove(STATUS_OK);
    }

    private List<Sort> sortExamQuestionTypes(List<Sort> list) {

        List<Sort> ret = list.stream().filter((Sort t) -> t.getParentId() == ZERO_INT).collect(Collectors.toList());
        for (Sort t1 : ret) {
            List<Sort> list2 = list.stream().filter((Sort t) -> t.getParentId().intValue() == t1.getId().intValue()).collect(Collectors.toList());
            for (Sort t2 : list2) {
                List<Sort> list3 = list.stream().filter((Sort t) -> t.getParentId().intValue() == t2.getId().intValue()).collect(Collectors.toList());
                t2.setChildren(list3);
            }
            t1.setChildren(list2);
        }
        return ret;
    }

    private void validateProductCategory(Sort sort) throws ServiceException {

        int parentId = sort.getParentId();
        if (StringUtils.isEmpty(sort.getName())) {
            throw new ServiceException(ERR_NAME_VALID_DENIED);
        }
        String sequence = sort.getSequence();
        if (parentId > ZERO_INT) {
            Sort parent = sort(parentId);
            int _parentId = parent.getParentId();
            if (_parentId > ZERO_INT) {
                Sort grandPa = sort(_parentId);
                System.out.println(parent.getSequence());
                System.out.println(grandPa.getSequence());
                if (!(sequence.substring(0, 2).equals(grandPa.getSequence().substring(0, 2))) && (sequence.substring(0, 4).equals(parent.getSequence().substring(0, 4)))) {
                    throw new ServiceException(ERR_PERMISSION_DENIED);
                }
            } else {
                if (!sequence.substring(0, 2).equals(parent.getSequence().substring(0, 2))) {
                    throw new ServiceException(ERR_PERMISSION_DENIED);
                }
            }
        }
        if (sort.getStatus().equals(ZERO_BYTE)) {
            sort.setStatus(STATUS_HALT);
        }
        if (sort.getPriority() == ZERO_INT) {
            sort.setPriority(STATUS_OK_INT);
        }
    }

    @Override
    public void codes2Ids(ProductQo qo) {

        List<String> codes = qo.getCodes();

        if (codes == null) {
            return;
        }

        if (codes.size() == ZERO_BYTE) {
            return;
        }

        List<Sort> productCategories = sortCache.findByKey(ZERO_BYTE);

        List<Integer> ids = new ArrayList<>();

        for (Sort t1 : productCategories) {
            boolean with1 = codes.contains(t1.getSequence());
            if (with1 && !ids.contains(t1.getId())) {
                ids.add(t1.getId());
            }
            for (Sort t2 : t1.getChildren()) {
                boolean with2 = codes.contains(t2.getSequence());
                if ((with1 || with2) && !ids.contains(t2.getId())) {
                    ids.add(t2.getId());
                }
                for (Sort t3 : t2.getChildren()) {
                    boolean with3 = codes.contains(t3.getSequence());
                    if ((with1 || with2 || with3) && !ids.contains(t3.getId())) {
                        ids.add(t3.getId());
                    }
                }
            }
        }
        qo.setSortIds(ids);
    }

    @PostConstruct
    public void init() {

        sortCache = kvCacheFactory.create(new CacheOptions("product_category", 1, Constants.CACHE_REDIS_EXPIRE), new RepositoryProvider<Byte, List<Sort>>() {

            @Override
            public List<Sort> findByKey(Byte key) throws ServiceException {
                if (key == STATUS_OK) {
                    return sortExamQuestionTypes(iSortRepository.findAll(new SortQo()));
                } else {
                    return sortExamQuestionTypes(iSortRepository.findAll(new SortQo(ZERO_BYTE)));
                }
            }

            @Override
            public Map<Byte, List<Sort>> findByKeys(Collection<Byte> keys) throws ServiceException {
                throw new UnsupportedOperationException("keys");
            }

        }, new ListModelConverter<>(Sort.class));
    }

    @Override
    public void firstSortId2Ids(ProductQo qo) {

        List<Sort> productCategories = sortCache.findByKey(STATUS_OK);

        List<Integer> ids = new ArrayList<>();

        for (Sort t1 : productCategories) {
            if (t1.getId().intValue() == qo.getFirstSortId()) {
                for (Sort t2 : t1.getChildren()) {
                    for (Sort t3 : t2.getChildren()) {
                        ids.add(t3.getId());
                    }
                }
            }
        }
        qo.setSortIds(ids);
    }
}